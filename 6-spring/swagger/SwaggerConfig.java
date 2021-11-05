package com.git.myworkspace.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

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
		
//		// Gateway에서 SessionId -> session-profile로 오는 사용자 정보 헤더 매개변수
//		RequestParameter sessionProfile = new RequestParameterBuilder()
//				.name("session-profile")
//				.description("{\"userId\":\"kdkcom\", \"role\":\"USER\"}")
//				.required(false)
//				.in("header")
//				.build();
//		
//		List<RequestParameter> params = new ArrayList<RequestParameter>();
//		params.add(sessionProfile);
		
		return new Docket(DocumentationType.SWAGGER_2)
//					.globalRequestParameters(params)	// 매개변수 추가
					.apiInfo(apiInfo())
					.select()
					// 전체, actuator, error 등 포함 됨
//					.apis(RequestHandlerSelectors.any()) 
					// REST Controller만
					.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					// 전체 경로
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