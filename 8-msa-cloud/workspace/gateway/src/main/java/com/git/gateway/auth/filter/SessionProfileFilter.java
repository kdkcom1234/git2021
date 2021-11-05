package com.git.gateway.auth.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.git.gateway.auth.util.WebFilterCors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SessionProfileFilter implements WebFilter {
	
	@Autowired
	private ReactiveStringRedisTemplate redis;	
	
	@Override
	public Mono<Void> filter(ServerWebExchange ex, WebFilterChain ch) {
		// 요청 및 응답객체
		ServerHttpRequest req = ex.getRequest();
		ServerHttpResponse res = ex.getResponse();

		// 요청 경로 ex) /auth/signin
		String rootPath = req.getPath().subPath(1, 2).toString(); // auth
		String subPath = req.getPath().subPath(3, 4).toString(); // signin
		
		if (rootPath.equals("auth") && subPath.equals("session-profile") 
				&& (req.getMethod() == HttpMethod.GET || req.getMethod() == HttpMethod.OPTIONS || req.getMethod() == HttpMethod.DELETE) ) {
			
			// cors 정책 처리
			if (CorsUtils.isCorsRequest(req)) {
				WebFilterCors.setCorsHeader(req, res);
				if (req.getMethod() == HttpMethod.OPTIONS) {
				    res.setStatusCode(HttpStatus.OK);
				    return Mono.empty();
				}
			}					
			
			// 세션 프로필 조회 요청
			if(req.getMethod() == HttpMethod.GET)
			{
				List<String> authHeader = req.getHeaders().get("APP_SESSION_ID");
				
				if(authHeader != null && authHeader.get(0) != null) {
					String sessionId = authHeader.get(0);

					System.out.println(sessionId);
					
					ReactiveValueOperations<String, String> record = redis.opsForValue();
					
					Mono<Void> result = record.get(sessionId)
					.flatMap(profileJSON -> {
						// 응답처리, sessionId 반환
						res.getHeaders().add("Content-Type", "application/json");
						res.setStatusCode(HttpStatus.OK);
						DataBuffer buffer = res.bufferFactory().wrap(profileJSON.getBytes());	
						
						return res.writeWith(Flux.just(buffer));
					});
					
					return result;
				}
			}
			
		    // 세션 프로필 삭제
			if(req.getMethod() == HttpMethod.DELETE)
			{
				List<String> authHeader = req.getHeaders().get("APP_SESSION_ID");
				
				if(authHeader != null && authHeader.get(0) != null) {
					String sessionId = authHeader.get(0);

					System.out.println(sessionId);
					
					ReactiveValueOperations<String, String> record = redis.opsForValue();
					
					Mono<Void> result = record.delete(sessionId)
					.flatMap(ok -> {
						// 응답처리
						res.setStatusCode(HttpStatus.OK);
						DataBuffer buffer = res.bufferFactory().wrap(String.valueOf(ok).getBytes());	
						
						return res.writeWith(Flux.just(buffer));
					});
					
					return result;
				}			
			}
		}
		

		return ch.filter(ex);
	}

}
