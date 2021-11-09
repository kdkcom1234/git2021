package com.example.sales.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.example.sales.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 주문 제품 정보
// 예) 제품정보, 구매수량, 구매금액....

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//엔터티 모델
@Entity
@IdClass(SalesOrderDetailId.class)
public class SalesOrderDetail {

	@Id
	private long salesOrderId;	// 주문 기본정보의 id
	@Id
	private int seq;				// 주문 제품 정보의 번호
	
	// 제품정보 참조
	// product M:1 order_detail
	@ManyToOne
	private Product product;
	
	// 주문당시에 정보로 기록한다.
	private String productName;	// 제품명
	private int unitPrice;	// 단가
	private int quantity;	// 수량
}
