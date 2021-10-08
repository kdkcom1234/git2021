package com.git.helloclient;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HelloClientService {

	// 1번 -- 수신쪽은 본인 번호
	@RabbitListener(queues = "test.hello.1")
	public void receiveMessage(byte[] message) throws UnsupportedEncodingException {

		System.out.println("-- test.hellp.1 --");
		System.out.println(new String(message, "UTF-8"));
	}
}
