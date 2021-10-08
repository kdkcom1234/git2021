package com.git.helloclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class HelloClientController {

	private HelloClientService service;

	private Map<String, String> clientConnectedMap = new HashMap<String, String>();

	@Autowired
	public HelloClientController(HelloClientService service) {
		this.service = service;
	}

	// Server Sent Event 프로토콜로 처리할 때는 반환 타입이 SseEmitter
	@GetMapping("/event/{clientId}")
	public SseEmitter connectEvent(@PathVariable String clientId) {

		// Event를 발생시키는 객체를 생성
		SseEmitter emitter = new SseEmitter();

		// 서비스 객체에 emitter 객체를 넘겨줌
		service.putEmitter(clientId, emitter);

		try {
			emitter.send("connected");
//			emitter.send(SseEmitter.event().name("connect").data("connect").build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emitter;
	}
}
