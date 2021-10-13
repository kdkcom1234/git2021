package com.git.gateway.auth;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SignUpFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {
		// 요청 및 응답객체
		ServerHttpRequest req = exchange.getRequest();
		ServerHttpResponse res = exchange.getResponse();

		// 요청 경로 ex) /auth/signup
		String rootPath = req.getPath().subPath(1, 2).toString();
		String subPath = req.getPath().subPath(3, 4).toString();
//		System.out.println(rootPath + ">" + subPath);

		// /auth/signup 일때 login 및 profile 정보 생성
		if (rootPath.equals("auth") && subPath.equals("signup")) {

			byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
			DataBuffer buffer = res.bufferFactory().wrap(bytes);
			res.setStatusCode(HttpStatus.CREATED);
			return res.writeWith(Flux.just(buffer));
		}

		return filterChain.filter(exchange);
	}
}
