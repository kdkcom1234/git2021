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
	// HashMap ������ �� ��: get(key) -> O(1)
	// ConcurrentSkipListMap Ű �������� ������ �Ǿ�����: get(key) -> O(logn)
	public ConcurrentSkipListMap<Long, Todo> todos = new ConcurrentSkipListMap<Long, Todo>();
	// id�� ������ ����� ����
	public AtomicLong maxId = new AtomicLong();

	// todo �����ȸ
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		return todos.descendingMap().values();
	}

	// todo 1�� �߰�
	@PostMapping(value = "/todos")
	public Todo postTodo(@RequestBody Todo todo) {
		// id���� ����
		Long currentId = maxId.incrementAndGet();
		// �Է¹��� �����ͷ� todo��ü�� ����
		Todo todoItem = Todo.builder().id(currentId).memo(todo.getMemo()).createdTime(new Date().getTime()).build();
		// todo ��ϰ�ü �߰�
		todos.put(currentId, todoItem);
		// �߰��� ��ü�� ��ȯ
		return todoItem;
	}

}
