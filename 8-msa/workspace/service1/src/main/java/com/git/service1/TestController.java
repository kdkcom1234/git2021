package com.git.service1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/test")
	public String test(HttpServletRequest req) {
		System.out.println(req.getHeader("session-profile"));
		System.out.println("--service 1--");
		return "1";
	}
}
