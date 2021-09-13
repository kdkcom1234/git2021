package com.git.controller.todo;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// REST API
// REST 방식으로 접근할 수 있는 인터페이스 제공하는 프로그램

//@Controller
//@ResponseBody
@RestController
public class TodoController {
	// HashMap 정렬이 안 됨: get(key) -> O(1)
	// ConcurrentSkipListMap 키 기준으로 정렬이 되었있음: get(key) -> O(logn)
	public ConcurrentSkipListMap<Long, Todo> todos = new ConcurrentSkipListMap<Long, Todo>();
	// id값 생성에 사용할 변수
	public AtomicLong maxId = new AtomicLong();

	// todo 목록조회
	// GET /todos
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		return todos.descendingMap().values();
	}

	// todo 1건 추가
	// POST /todos {"memo":"테스트입니다"}
	@PostMapping(value = "/todos")
	public Todo postTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// 데이터 검증 로직
		// 메모값이 없으면 에러처리함
		if(todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// 클라이언트에서 메모값이 없이 보내거나 빈값으로 보낸 것임
			// 클라이언트 오류, 4xx
			// 요청값을 잘못보낸 것임 - Bad Request (400)
			// res.setStatus(400);
			
			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// id값을 생성
		Long currentId = maxId.incrementAndGet();
		
		// 입력받은 데이터로 todo객체를 생성
		// id값과 생성일시는 서버에서 생성한 것으로 처리함
		// html태그가 있으면 날려버림(script에서 문제가 발생함)
		Todo todoItem = Todo.builder()
								.id(currentId)
//								.memo(todo.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
								.memo(todo.getMemo())
								.createdTime(new Date().getTime())
							.build();
		// todo 목록객체 추가
		todos.put(currentId, todoItem);
		
		// 리소스 생성됨
		// res.setStatus(201);
		res.setStatus(HttpServletResponse.SC_CREATED);
		
		// 추가된 객체를 반환
		return todoItem;
	}

}
