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

			ObjectMapper mapper = new ObjectMapper();

//			System.out.println("--insert--");
//			Mono<Login> login = template.insert(Login.class)
//					.using(Login.builder().userId("kdkcom").password("1234").sessionId("1").build());
//
//			Mono<Profile> profile = template.insert(Profile.class)
//					.using(Profile.builder().email("kdkcom1234@gmail.com").build());

//			byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
//			DataBuffer buffer = res.bufferFactory().wrap(bytes);
			res.setStatusCode(HttpStatus.CREATED);

//			Mono<Login> login = template.insert(Login.class)
//					.using(Login.builder().userId("kdkcom").password("1234").sessionId("1").build());
//
//			Mono<Profile> profile = template.insert(Profile.class)
//					.using(Profile.builder().email("kdkcom1234@gmail.com").build());

			return req.getBody().map(body -> {
				SignUpRequest signup = new SignUpRequest();
				try {
					signup = mapper.readValue(body.toString(StandardCharsets.UTF_8), SignUpRequest.class);
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}
				return signup;
			}).concatMap(signup -> template.insert(Login.class)
					.using(Login.builder().userId(signup.getUserId()).password(signup.getPassword()).build())
					.then(template.insert(Profile.class).using(Profile.builder().email(signup.getEmail()).build())))
					.then();

//			return req.getBody().doOnNext(body -> {
//				System.out.println(body.toString(StandardCharsets.UTF_8));
//
//				try {
//					SignUpRequest signup = mapper.readValue(body.toString(StandardCharsets.UTF_8), SignUpRequest.class);
//				} catch (JsonProcessingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}).then(login).then(profile).then();

//			return res.writeWith(Flux.just(buffer));
		}

		return filterChain.filter(exchange);
	}
}
