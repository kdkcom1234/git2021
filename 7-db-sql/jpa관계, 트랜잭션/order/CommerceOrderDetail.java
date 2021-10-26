package com.example.commerce.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.example.commerce.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//엔터티 모델
@Entity
@IdClass(CommerceOrderDetailId.class)
public class CommerceOrderDetail {
	
	@Id
	private long commerceOrderId;
	@Id
	private int seq;
	
	// 제품정보 참조
	// product M:1 order_detail
	@ManyToOne
	private Product product;
	
	// 주문당시에 정보로 기록한다.
	private String productName;	// 제품명
	private int unitPrice;	// 단가
	private int quantity;	// 수량
}
