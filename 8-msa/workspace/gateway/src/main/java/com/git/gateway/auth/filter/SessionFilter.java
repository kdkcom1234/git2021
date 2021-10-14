package com.git.gateway.auth.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class SessionFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
		ServerHttpRequest request = serverWebExchange.getRequest();

//		System.out.println(request.getPath().subPath(1, 2).toString());

		request.mutate().header("session-profile", "{\"userId\":\"kdkcom\"}");
		return webFilterChain.filter(serverWebExchange);
	}
}
