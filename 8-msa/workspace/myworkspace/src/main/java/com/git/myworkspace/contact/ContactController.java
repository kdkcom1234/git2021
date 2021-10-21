package com.git.myworkspace.contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private ContactRepository repo;

	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}
	
	// 목록 findAll() - 정렬x
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() {
		// SELECT * FROM contact;
		return repo.findAll();
	}
	// 페이지 findAll(...)
	@GetMapping("/contacts/paging")
	public Page<Contact> getContactsPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// 연락처 추가 save(Contact)
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		// 이름, 폰번호 검증처리
		if (contact.getName() == null || contact.getName().isEmpty() || contact.getPhoneNum() == null
				|| contact.getPhoneNum().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;
		}

		// contact 객체 생성
		Contact contactItem = Contact.builder().name(contact.getName()).phoneNum(contact.getPhoneNum())
				.email(contact.getEmail()).memo(contact.getMemo())
				.createdTime(new Date().getTime()).build();

		// INSERT INTO contact(...) values(...)
		Contact contactSaved = repo.save(contactItem);
		res.setStatus(HttpServletResponse.SC_CREATED);
		return contactSaved;
	}
	
	// 연락처 삭제 deleteById(id)
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {
		Optional<Contact> contact = repo.findById(Long.valueOf(id));
		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		// 삭제 처리
		// DELETE FROM contact WHERE id=?
		repo.deleteById(id);
		return true;
	}

	// 연락처 수정 save(Contact)
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res) {
		Optional<Contact> contactItem = repo.findById(id);
		if (contactItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// 이름이 없으면 에러처리
		if (contact.getName() == null || contact.getName().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		String name = getPlainText(contact.getName());
		if (name.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		String phoneNum = getPlainText(contact.getPhoneNum());
		if (phoneNum.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		String email = getPlainText(contact.getEmail());
		String memo = getPlainText(contact.getMemo());
		// 데이터 변경

		Contact contactToSave = contactItem.get();

		contactToSave.setName(name);
		contactToSave.setPhoneNum(phoneNum);
		contactToSave.setEmail(email);
		contactToSave.setMemo(memo);
		// UPDATE SET name=?, (...), memo=? WHERE id=?
		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;
	}

	// 수정 요청에 필요한 메서드
	private String getPlainText(String text) {
		// 태그 안들어가게하는 정규식
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
}
