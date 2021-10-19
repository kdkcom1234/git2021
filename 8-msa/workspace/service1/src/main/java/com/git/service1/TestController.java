package com.git.service1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	Service2Client client;
	
	@Autowired
	public TestController(Service2Client client) {
		this.client = client;
	}
	
	@GetMapping(value = "/test")
	public String test(HttpServletRequest req) {
		System.out.println(req.getHeader("session-profile"));
		System.out.println("--service 1--");
		return "123";
	}
	
	@GetMapping(value = "/test2")
	public String test2() {
		return client.test();
	}
	
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() {
		System.out.println("--service1 contact--");
		return client.getContacts();
	}	
}
