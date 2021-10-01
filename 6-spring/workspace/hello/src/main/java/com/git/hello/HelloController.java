package com.git.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Dispatcher Servlet이 HTTP 요청을 받고 
// HTTP 요청 정보에 맞는 컨트롤러의 메서드를 실행함

// Controller: HTTP 요청에 대한 응답을 처리할 수 있는 클래스
// RestController: Controller인데 응답으로 데이터 객체만 처리함
//@Controller
//@ResponseBody
@RestController
public class HelloController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello, Spring Boot!";
	}
}
