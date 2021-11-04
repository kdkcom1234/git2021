package com.git.gateway.auth.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.git.gateway.auth.util.WebFilterCors;

import reactor.core.publisher.Mono;

@Component
public class SessionFilter implements WebFilter {
	
	@Autowired
	private ReactiveStringRedisTemplate redis;	

	@Override
	public Mono<Void> filter(ServerWebExchange ex, WebFilterChain ch) {
		// 요청 및 응답객체
		ServerHttpRequest req = ex.getRequest();	
		ServerHttpResponse res = ex.getResponse();
		
		// cors 정책 처리
		if (CorsUtils.isCorsRequest(req)) {
//			System.out.println(req.getMethod());
			// OPTIONS (pre-flight)일 때 Cors header 처리
			if (req.getMethod() == org.springframework.http.HttpMethod.OPTIONS) {
				WebFilterCors.setCorsHeader(req, res);			
			    res.setStatusCode(org.springframework.http.HttpStatus.OK);
			    return Mono.empty();
			}
		}				
		
		// 1. 인증 토큰 헤더 정보 가져오기
		List<String> authHeader = req.getHeaders().get("APP_SESSION_ID");
		
		// 2. 인증 토큰 헤더 정보가 있으면
		if(authHeader != null && authHeader.get(0) != null) {
			// 3. 인증 세션에서 토큰값 얻기
			String sessionId = authHeader.get(0);

			// 4. 레디스 <String, String> 처리객체 생성
			ReactiveValueOperations<String, String> record = redis.opsForValue();
			
			// 5. 토큰 값으로 레디스에 프로필 정보 조회
			Mono<Void> result = record.get(sessionId)
			.flatMap(profileJSON -> {
				System.out.println("-----profileJSON-----");
				System.out.println(profileJSON);
				// 6. 요청 헤더에 session-profile을 넣어줌
				req.mutate().header("session-profile", profileJSON);
				return ch.filter(ex);
			});
			
			return result;
		}
		return ch.filter(ex);
	}
}
