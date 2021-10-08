package com.git.helloproducer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloProducerController {

	private HelloProducerService service;

	@Autowired
	public HelloProducerController(HelloProducerService service) {
		this.service = service;
	}

	@PostMapping(value = "/send-message")
	public boolean sendMessage(@RequestBody String message, HttpServletRequest req) {

		System.out.println(req.getHeader("content-type"));

		System.out.println(message);
		service.sendMessage(message.getBytes());
		return true;
	}
}
