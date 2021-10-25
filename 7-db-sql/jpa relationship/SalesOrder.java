package com.example.sales.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.example.sales.order.command.CreateRequest;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity  // Aggregate root (집합 루트)
public class SalesOrder {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long amount;
	private String name;
	private String address;
	
	@Column(columnDefinition="CHAR(2)")
	private String status;
	
//	// 1.1.1 컬럼 추가
//	@Column(nullable=false) // DB NOT NULL 제약조건 X
//	private String created_by;
	
//	// SalesOrderDetail 여러개를 가지고 있는 필드
//	@OneToMany(cascade=CascadeType.PERSIST) // 영속성 전이로 저장
//	@JoinColumn(name="salesOrderId") // 하위테이블 필드, FK
//	private List<SalesOrderDetail> salesOrderDetails;
}
