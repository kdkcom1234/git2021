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
// REST ������� ������ �� �ִ� �������̽� �����ϴ� ���α׷�

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
	// GET /todos
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		return todos.descendingMap().values();
	}

	// todo 1�� �߰�
	// POST /todos {"memo":"�׽�Ʈ�Դϴ�"}
	@PostMapping(value = "/todos")
	public Todo postTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// ������ ���� ����
		// �޸��� ������ ����ó����
		if(todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// Ŭ���̾�Ʈ���� �޸��� ���� �����ų� ������ ���� ����
			// Ŭ���̾�Ʈ ����, 4xx
			// ��û���� �߸����� ���� - Bad Request (400)
			// res.setStatus(400);
			
			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// id���� ����
		Long currentId = maxId.incrementAndGet();
		
		// �Է¹��� �����ͷ� todo��ü�� ����
		// id���� �����Ͻô� �������� ������ ������ ó����
		// html�±װ� ������ ��������(script���� ������ �߻���)
		Todo todoItem = Todo.builder()
								.id(currentId)
//								.memo(todo.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
								.memo(todo.getMemo())
								.createdTime(new Date().getTime())
							.build();
		// todo ��ϰ�ü �߰�
		todos.put(currentId, todoItem);
		
		// ���ҽ� ������
		// res.setStatus(201);
		res.setStatus(HttpServletResponse.SC_CREATED);
		
		// �߰��� ��ü�� ��ȯ
		return todoItem;
	}

}
