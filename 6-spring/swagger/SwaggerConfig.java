package com.git.myworkspace.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


//Swagger 2 UI 기본 경로
//http://localhost:8080/swagger-ui/
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.title("Myworkspace")
					.version("1.0")
					.description("API List of Myworkspace Application")
					.license("포트폴리오 프로젝트")
					.build();
	}
}