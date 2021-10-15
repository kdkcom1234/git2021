package com.git.gateway.auth.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class SessionFilter implements WebFilter {
	
	@Autowired
	private ReactiveStringRedisTemplate redis;	

	@Override
	public Mono<Void> filter(ServerWebExchange ex, WebFilterChain ch) {
		ServerHttpRequest req = ex.getRequest();
		
		List<String> authHeader = req.getHeaders().get("authorization");
		
		if(authHeader != null && authHeader.get(0) != null) {
			String sessionId = authHeader.get(0).replace("Bearer: ", "");

			ReactiveValueOperations<String, String> record = redis.opsForValue();
			
			Mono<Void> result = record.get(sessionId)
			.flatMap(profileJSON -> {
				req.mutate().header("session-profile", profileJSON);
				return ch.filter(ex);
			});
			
			return result;
		}
		return ch.filter(ex);
	}
}
