package com.git.gateway.auth.filter;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;
import static org.springframework.data.relational.core.query.Update.update;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.gateway.auth.entity.Login;
import com.git.gateway.auth.entity.Profile;
import com.git.gateway.auth.request.SignInRequest;
import com.git.gateway.auth.util.Hash;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SignInFilter implements WebFilter {

	@Autowired
	private R2dbcEntityTemplate db;
	
	@Autowired
	private ReactiveStringRedisTemplate redis;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {
		
		// 요청 및 응답객체
		ServerHttpRequest req = exchange.getRequest();
		ServerHttpResponse res = exchange.getResponse();

		// 요청 경로 ex) /auth/signin
		String rootPath = req.getPath().subPath(1, 2).toString(); // auth
		String subPath = req.getPath().subPath(3, 4).toString(); // signin

		// /auth/signin 일때 인증 처리 및 세션 생성
		if (rootPath.equals("auth") && subPath.equals("signin")) {
			System.out.println("--signin--");
			
			Flux<DataBuffer> result = req.getBody()
			.map(body -> unmashal(body))
			.flatMap(signInReq -> {	
				return db.selectOne(query(
						where("userId").is(signInReq.getUserId())
						.and("password").is(Hash.getSha512Hex(signInReq.getPassword())))
						.limit(1)
						, Login.class);
			})
			.flatMap(login -> {
				return db.selectOne(query(
						where("userId").is(login.getUserId()))
						.limit(1)
						, Profile.class);
			})
			.flatMap(profile -> {
				
				// 기존 세션이 있으면 삭제한다.
				if(profile.getSessionId() != null) {
					// Flux나 Mono 객체는 subscribe를 해야 실행된다.
					redis.delete(profile.getSessionId()).subscribe();
				}
				
				// SessionId 생성
				String sessionId = Hash.getSessionId(profile.getUserId());
				
				// 현재 프로필 정보를 생성한 sessionid로 변경
				profile.setSessionId(sessionId);
				
				// 세션(세션Id+프로필) 생성
				ReactiveValueOperations<String, String> record = redis.opsForValue();
				record.set(sessionId, mashal(profile), Duration.ofHours(24)).subscribe();				
				
				// 데이터베이스 프로필에 현재 세션Id 추가
				db.update(Profile.class)
					.matching(query(where("userId").is(profile.getUserId())))
					.apply(update("sessionId", sessionId))
				.subscribe();
				
				// 응답처리, sessionId 반환
				res.setStatusCode(HttpStatus.OK);
				DataBuffer buffer = res.bufferFactory().wrap(sessionId.getBytes());	
				
				return Flux.just(buffer);
			});
			
			return res.writeWith(result);
		}
		
		return filterChain.filter(exchange);
	}
	
	public SignInRequest unmashal(DataBuffer body) {
		ObjectMapper mapper = new ObjectMapper();
		SignInRequest signInReq = new SignInRequest();
		try {
			signInReq = mapper.readValue(body.toString(StandardCharsets.UTF_8), SignInRequest.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return signInReq;
	}
	
	public String mashal(Profile profile) {
		ObjectMapper mapper = new ObjectMapper();
		String str = "";
		
		try {
			str = mapper.writeValueAsString(profile);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public String getNonNullValue(String str) {
		return str == null ? "" : str;	
	}
}
