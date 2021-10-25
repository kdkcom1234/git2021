package com.example.sales.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.sales.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity 
//@Table(indexes = {
//		@Index(
//				name="idx_sales_order_id", 
//				columnList="salesOrderId"
//		)
//})
public class SalesOrderDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // not-nullable, 0
	private Long salesOrderId; // nullable
	private int productId;
//	@ManyToOne
//	@JoinColumn(name="productId") // FK있어야되는 테이블의 컬럼
//	private Product product;
	
	private long unitPrice;
	private int quantity;
}
