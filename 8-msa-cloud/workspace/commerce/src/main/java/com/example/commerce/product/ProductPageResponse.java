package com.example.commerce.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageResponse {

	private boolean isLast;
	private long totalElements;
	private List<Product> content;
}
