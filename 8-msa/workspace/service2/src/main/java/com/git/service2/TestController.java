package com.git.service2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/test")
	public String test() {
		System.out.println("--service 2--");
		return "2";
	}
	
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() {
		System.out.println("--service2 contact--");
		List<Contact> contacts = new ArrayList<Contact>();
		
		// 샘플 데이터
		Contact contact1 = new Contact();
		contact1.setId(1);
		contact1.setName("hong");
		contact1.setPhone("01012345678");
		
		Contact contact2 = new Contact();
		contact2.setId(2);
		contact2.setName("john");
		contact2.setPhone("01098765432");		
		
		contacts.add(contact1);
		contacts.add(contact2);
		
		return contacts;
	}	
}
