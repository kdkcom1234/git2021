package com.example.sales.product;

import com.example.sales.Result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateResponse {
	private Product product;
	private Result result; // {"code": ..., "message": "정사적으로 생성되었습니다."}
}
