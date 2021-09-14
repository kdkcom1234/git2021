package com.git.controller;

// 값이 같은지를 비교하는 static 메서드
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import  org.springframework.mock.web.MockHttpServletResponse;

import com.git.controller.todo.Todo;
import com.git.controller.todo.TodoController;

//unit test
//- 자바 관점으로 봤을 때 클래스의 메서드들을 테스트하는 것
//
//integration test
//- 백엔드관점으로 봤을 때는 API를 테스트하는 것
//- 서버를 띄우고, 네트워크로 데이터를 보내고 처리 결과까지 확인

@SpringBootTest
public class TestTodoController {
		
	// test case: 할 일 추가
	// event flow(처리흐름): 할 일 1건을 추가함
	// pre-condition(사전조건): 따로 없음..
	// expected result(예상결과): 할 일 목록에 추가한 데이터가 존재해야함
	@Test
	void addTodo() {
		// given - 테스트 데이터 및 객체 준비
		TodoController controller = new TodoController();
		Todo expected = Todo.builder().memo("test").build();
		
		// when - 테스트 케이스의 event flow를 수행
		// ServletResponse 객체는 가짜(Mock)을 넣어줌
		controller.addTodo(expected, new MockHttpServletResponse());
		
		// then - 예상결과와 실제결과를 비교
		
		// 전체 목록에 추가한애를 가져옴
		List<Todo> todos =  controller.getTodos();
		Todo actual = todos.get(0);	// arrayList.get(인덱스);
		
		// 예상결과 데이터와 실제 데이터를 비교함
		assertEquals(1, actual.getId());
		assertEquals(expected.getMemo(), actual.getMemo());
	}
	
	// test case: 할 일 삭제
	// event flow(처리흐름): 할 일 1건을 삭제함
	// pre-condition(사전조건): 할 일 데이터 최소 1건 이상 있어야함
	// expected result(예상결과): 할 일 목록에 삭제한 데이터가 존재하면 안 됨	
	@Test
	void removeTodo() {
		// given - 테스트 데이터 및 객체 준비
		// 사전조건이 있다면 사전조건을 준비하는 단계
		// 여기서는 1건 추가가 되어있어야함
		TodoController controller = new TodoController();
		
		Todo testItem = Todo.builder().memo("test").build();
		
		controller.addTodo(testItem, new MockHttpServletResponse());
		
		List<Todo> beforeTodos = controller.getTodos();
		assertEquals(1, beforeTodos.size());	// 삭제 전에는 목록 크기가 1
		
		// when - 테스트 케이스의 event flow를 수행
		// id가 1인 todo 1건을 삭제
		controller.removeTodo(1, new MockHttpServletResponse());	
		
		// then - 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 목록의 크기가 0이여함
		List<Todo> afterTodos = controller.getTodos();
		assertEquals(0, afterTodos.size());		// 삭제 후에는 목록 크기가 0
	}

	// test case: 할 일 수정
	// event flow(처리흐름): 
	//   basic flow(정상흐름):
	//     1. 할 일 1건에 대해서 메모값을 수정함
	//   alternative flow(예외흐름): 
	//     1. 목록에 없는 id값으로 삭제를 실행 - 404
	//	   2. 메모값을 빈 값 또는 데이터 객체를 안보냈음 - 400 
	// pre-condition(사전조건): 할 일 데이터 최소 1건 이상 있어야함
	// expected result(예상결과): 할 일 목록에 수정한 메모값으로 출력되어야함		
	@Test
	void modifyTodo() {
		// given - 테스트 데이터 및 객체 준비
		// 사전조건이 있다면 사전조건을 준비하는 단계
		// 여기서는 1건 추가가 되어있어야함		
		TodoController controller = new TodoController();
		
		Todo testItem = Todo.builder().memo("test").build();
		controller.addTodo(testItem, new MockHttpServletResponse());		
		
		// 변경할 테스트 데이터
		String expectedResult = "modify test memo";
		Todo modifyData = Todo.builder().memo(expectedResult).build();
		
		HttpServletResponse res = new MockHttpServletResponse();
		
		// basic flow - 정상적인 상황
		// when - 테스트 케이스의 event flow를 수행
		// id가 1인 todo에 memo를 수정
		controller.modifyTodo(1, modifyData, res);
		
		// then - 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 해당 아이템의 메모값이 예상결과와 일치해야함
		List<Todo> todos = controller.getTodos();
		assertEquals(expectedResult, todos.get(0).getMemo());	
		
		// altanative flow - 1. id값이 없는 경우
		// when - id가 2로 수정해봄
		Todo resultTodoId = controller.modifyTodo(2, modifyData, res);
		
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status Code 404
		assertNull(resultTodoId); 
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());
		
		// altanative flow - 2-1. memo값이 null인경우
		// when
		Todo resultTodoMemoNull = controller.modifyTodo(1, new Todo(), res);
		
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status Code 400
		assertNull(resultTodoMemoNull); 
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// altanative flow - 2-2. memo값이 빈 값("")인경우
		// when
		Todo resultTodoMemoEmpty = controller.modifyTodo(1, Todo.builder().memo("").build(), res);
		
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status Code 400		
		assertNull(resultTodoMemoEmpty); 
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());		
	}
}
