package com.git.controller.todo;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		return todos.descendingMap().values();
	}

	// todo 1건 추가
	@PostMapping(value = "/todos")
	public Todo postTodo(@RequestBody Todo todo) {
		// id값을 생성
		Long currentId = maxId.incrementAndGet();
		// 입력받은 데이터로 todo객체를 생성
		Todo todoItem = Todo.builder().id(currentId).memo(todo.getMemo()).createdTime(new Date().getTime()).build();
		// todo 목록객체 추가
		todos.put(currentId, todoItem);
		// 추가된 객체를 반환
		return todoItem;
	}

}
