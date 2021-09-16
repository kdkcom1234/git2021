package com.git.controller.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private SortedMap<Long, Todo> todos = 
			Collections.synchronizedSortedMap(new TreeMap<Long, Todo>(Collections.reverseOrder())) ;
	// id�� ������ ����� ����
	private AtomicLong maxId = new AtomicLong();

	// todo �����ȸ
	// GET /todos
	@GetMapping(value = "/todos")
	public List<Todo> getTodos() {
		// �� �����
		return new ArrayList<Todo>(todos.values());
	}

	// todo 1�� �߰�
	// POST /todos {"memo":"�׽�Ʈ�Դϴ�"}
	@PostMapping(value = "/todos")
	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {
		System.out.println(todo);
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
		
		// �±׵� �� �������� ���ڿ�
		String memo = getPlainText(todo.getMemo());
		if(memo.isEmpty()) {
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
								.memo(memo)
//								.memo(todo.getMemo())
								.createdTime(new Date().getTime())
							.build();
		// todo ��ϰ�ü �߰�
		todos.put(currentId, todoItem);
		
		// ���ҽ� ������
		// res.setStatus(201);
		res.setStatus(HttpServletResponse.SC_CREATED);
		
		System.out.println(todoItem);
		
		// �߰��� ��ü�� ��ȯ
		return todoItem;
	}
	
	// todo 1�� ����
	// DELETE /todos/1 -> id�� 1�� �׸��� �����ض�
	// id���� path variable�� 
	@DeleteMapping(value="/todos/{id}")
	public boolean removeTodo(@PathVariable long id, HttpServletResponse res) {
		
		// �ش� id�� ������ 1���� ������
		Todo todo = todos.get(Long.valueOf(id));
		// �ش� id�� �����Ͱ� ������
		if(todo == null) {
			// res.setStatus(404);
			// NOT FOUND: �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		// ���� ����
		todos.remove(Long.valueOf(id));
		
		return true;
	}
	
	// todo 1�� ����
	// PUT /todos/1 {"memo":"..."}
	// id���� path variable�� 
	@PutMapping(value="/todos/{id}")	
	public Todo modifyTodo(@PathVariable long id, @RequestBody Todo todo, HttpServletResponse res) {
		// �ش� id�� ������ 1���� ������
		Todo findItem = todos.get(Long.valueOf(id));
		// �ش� id�� �����Ͱ� ������
		if(findItem == null) {
			// res.setStatus(404);
			// NOT FOUND: �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
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
		
		String memo = getPlainText(todo.getMemo());
		if(memo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// ������ ����
		findItem.setMemo(memo);
		
		return findItem;
	}
	
	// html �±׸� �����ϴ� �޼���
	private String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
}
