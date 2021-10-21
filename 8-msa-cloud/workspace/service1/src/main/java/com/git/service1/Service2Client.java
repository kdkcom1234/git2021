package com.git.service1;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// 인터페이스를 호출할 상대 서비스의 이름을 작성
// eureka service registry에 등록된 이름
@FeignClient("service-2")
public interface Service2Client {
	@GetMapping(value= "/test")
	public String test();
	
	@GetMapping(value= "/contacts")
	public List<Contact> getContacts();
}
