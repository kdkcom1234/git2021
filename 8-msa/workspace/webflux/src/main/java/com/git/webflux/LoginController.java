package com.git.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import reactor.core.publisher.Mono;

@RestController
public class LoginController {

	private static LoginReposiory repo;

	@Autowired
	public LoginController(LoginReposiory repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/test")
	public Mono<Login> test() {
		R2dbcEntityTemplate template = new R2dbcEntityTemplate(
				new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
						.host("database-1.cgp5lxuznsxk.ap-northeast-2.rds.amazonaws.com").port(5432)
						.database("postgres").schema("auth").username("postgres").password("password123!").build()));

		return template.insert(Login.class)
				.using(Login.builder().userId("kdkcom").passwd("1234").sessionId("1").build());
	}
}
