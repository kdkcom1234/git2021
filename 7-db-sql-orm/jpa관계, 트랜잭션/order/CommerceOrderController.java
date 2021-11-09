package com.example.commerce.order;

import com.example.commerce.order.request.CommerceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
	// **기본 FetchType.Lazy 전략으로 하위 객체를 접근하는 시점에 테이블 데이터를 읽어옴
	// 즉, Controller로 반환될 때 읽어옴
	@GetMapping("/orders")
	public List<CommerceOrder> getOrders(){
		return repo.findAll().parallelStream()
				.map(order -> {
					order.setDetails(null);
					return  order;})
				.collect(Collectors.toList());
	}
}
