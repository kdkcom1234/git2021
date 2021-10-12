package com.example.commerce.product.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesProduct {

	private int id;
	private String code;
	private String name;
	private int unitPrice;
}
