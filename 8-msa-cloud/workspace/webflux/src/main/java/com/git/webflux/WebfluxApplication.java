package com.git.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxApplication {

//	@Autowired
//	private static LoginReposiory repo;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);

//		repo.save(Login.builder().build());

//		R2dbcEntityTemplate template = new R2dbcEntityTemplate(
//				new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
//						.host("database-1.cgp5lxuznsxk.ap-northeast-2.rds.amazonaws.com").port(5432)
//						.database("postgres").schema("auth").username("postgres").password("password123!").build()));
//
//		template.getDatabaseClient().sql("insert into login(user_id, passwd, session_id) values('kdkcom', '1234', '1')")
//				.fetch().rowsUpdated();
	}

}
