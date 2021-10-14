package com.git.gateway.auth.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
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

import reactor.core.publisher.Mono;

@Component
public class SignUpFilter implements WebFilter {

	@Autowired
	private R2dbcEntityTemplate template;

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
			
			// 201 상태코드 처리
			res.setStatusCode(HttpStatus.CREATED);
			
			// Mono객체 리턴
			return req.getBody()
			.map(body -> {
				SignUpRequest signUpReq = new SignUpRequest();
				
				try {
					ObjectMapper mapper = new ObjectMapper();
					signUpReq = mapper.readValue(body.toString(StandardCharsets.UTF_8), SignUpRequest.class);
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}
				return signUpReq;				
			})
			.concatMap(signUpReq -> 
				template
					.insert(Login.class)
					.using(Login.builder()
						.userId(signUpReq.getUserId())
						.password(signUpReq.getPassword()).build())
			.then(
				template
					.insert(Profile.class)
					.using(Profile.builder()
						.username(signUpReq.getUsername())
						.email(signUpReq.getEmail()).build())))
			.then();
		}

		return filterChain.filter(exchange);
	}
}
