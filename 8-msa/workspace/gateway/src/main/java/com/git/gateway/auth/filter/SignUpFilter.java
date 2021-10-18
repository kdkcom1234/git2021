package com.git.gateway.auth.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
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
import com.git.gateway.auth.request.SignUpRequest;
import com.git.gateway.auth.util.Hash;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SignUpFilter implements WebFilter {

	@Autowired
	private R2dbcEntityTemplate db;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {
		// 요청 및 응답객체
		ServerHttpRequest req = exchange.getRequest();
		ServerHttpResponse res = exchange.getResponse();

		// 요청 경로 ex) /auth/signup
		String rootPath = req.getPath().subPath(1, 2).toString(); // auth
		String subPath = req.getPath().subPath(3, 4).toString(); // signup
//		System.out.println(rootPath + ">" + subPath);

		// /auth/signup 일때 login 및 profile 정보 생성
		if (rootPath.equals("auth") && subPath.equals("signup")) {
			
			Flux<DataBuffer> result = req.getBody()
			.map(body -> {
				// 1. JSON 데이터를 객체화를
				return unmashal(body);				
			})
			.flatMap(signUpReq -> {
				// 2. login 및 profile 저장
				return db
				.insert(Login.class)
				.using(Login.builder()
					.userId(signUpReq.getUserId())
					// 패스워드는 단방향 암호화로 만듦
					.password(Hash.getSha512Hex(signUpReq.getPassword()))
					.build())
				.then(db
						.insert(Profile.class)
						.using(Profile.builder()
							.userId(signUpReq.getUserId())							
							.username(signUpReq.getUsername())
							.email(signUpReq.getEmail())
							.role(signUpReq.getRole())
							.img(signUpReq.getImg())
							.build()));	
			})
			.flatMap(profile -> {
				// 3. 응답 처리
				res.setStatusCode(HttpStatus.CREATED);
				DataBuffer buffer = res.bufferFactory().wrap("success".getBytes());	
				
				return Flux.just(buffer);
			});
			
			// return getBody(loginInsert()).profileInsert().response().write()
			// 현재코드(필터)에서 Mono 객체가 리턴하고 끝남
			return res.writeWith(result);	
		}

		return filterChain.filter(exchange);
	}
	
	public SignUpRequest unmashal(DataBuffer body) {
		ObjectMapper mapper = new ObjectMapper();
		SignUpRequest req = new SignUpRequest();
		try {
			req = mapper.readValue(body.toString(StandardCharsets.UTF_8), SignUpRequest.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return req;
	}
}
