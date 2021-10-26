package com.example.commerce.order;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//엔터티 모델
@Entity
public class CommerceOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id; // 자동증가값
	private String name;	// 주문자명
	private String address;	// 주소
	private String date;	// 주문일자
	
	private int totalAmount;	// 총 주문금액
	
	@OneToMany
	@JoinColumn(name="commerceOrderId")
	private List<CommerceOrderDetail> details;

	// 하위 레코드 개수
	@Formula("(SELECT COUNT(1) FROM commerce_order_detail d WHERE d.commerce_order_id = id)")
	private int detailCnt;

	// 하위 레코드 중 첫번째 레코드의 정보
	@Formula("(SELECT d.product_name FROM commerce_order_detail d WHERE d.commerce_order_id = id LIMIT 1)")
	private String firstProductName;
	
	private String status;	// 상태
	private long createdTime;	// 생성시간
}
