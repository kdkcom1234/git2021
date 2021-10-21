package com.example.commerce.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//엔터티 모델
public class Product {
	private int id; // 자동증가값
	private String productCode;
	private String productName;
	private int price;
	private int salesProductId; // 타 시스템에 받은 id값
}
