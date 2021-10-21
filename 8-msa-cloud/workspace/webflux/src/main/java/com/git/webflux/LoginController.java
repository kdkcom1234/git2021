package com.git.webflux;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class LoginController {

	R2dbcEntityTemplate db;

	public LoginController(R2dbcEntityTemplate db) {
		this.db = db;
	}
	
	
	@GetMapping(value = "/test")
	public Mono<Login> test() {
		return db.insert(Login.class)
				.using(Login.builder()
						.userId("kdkcom")
						.passwd("1234")
						.sessionId("1")
						.build());
	}
}
