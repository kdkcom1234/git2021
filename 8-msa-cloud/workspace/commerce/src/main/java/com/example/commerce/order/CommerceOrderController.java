package com.example.commerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.commerce.order.request.CommerceOrderRequest;


@RestController
public class CommerceOrderController {

	private CommerceOrderService service;
	private CommerceOrderRepository repo;

	@Autowired
	public CommerceOrderController(CommerceOrderService service, CommerceOrderRepository repo){
		this.service = service;
		this.repo = repo;
	}

	@PostMapping("/orders")
	public CommerceOrder requestOrder(@RequestBody CommerceOrderRequest reqOrder) {
		CommerceOrder savedOrder = service.saveOrder(reqOrder);
		service.sendOrder(savedOrder);
		return savedOrder;
	}
	
	// 주문 1건만 조회
	// 하위 테이블 정보를 포함하여 반환한다.
	@GetMapping("/orders/{id}")
	public CommerceOrder getOrder(@PathVariable long id){
		System.out.println(id);
		return repo.findById(id).orElse(null);
	}

	// 주문 목록 조회
	// 하위 테이블 정보를 null처리하고 조회한다.
	
	// ** 엔티티 객체가 연관관계를 맵핑할 때(@OneToMany, @ManyToOn)
	// ** 기본적으로 FetchType.Lazy 전략으로 하위 객체를 접근하는 시점에 테이블 데이터를 읽어옴
	
	// ** findAll을 실행한다고 실제 sql 쿼리가 실행되는 것임
	// 즉, Controller로 반환된 다음에 JSON객체로 변환되기 위해서 읽어 들이는 시점에 SQL 쿼리 실행
	// 스프링 프레임워크에서 JSON으로 변환할 때 하위 객체 null이기 때문에 조회를 해오지 않음
	@GetMapping("/orders")
	public List<CommerceOrder> getOrders(){
//		// 하위 테이블까지 조회함
//		return repo.findAll();
		
		// repository로 조회해 온 후에 null처리했는데
		// 실제로 하위테이블 select 안 해옴
		return repo.findAll().stream()
				.map(order -> {
					order.setDetails(null);
					return order;
				})
				.collect(Collectors.toList());
	}
}
