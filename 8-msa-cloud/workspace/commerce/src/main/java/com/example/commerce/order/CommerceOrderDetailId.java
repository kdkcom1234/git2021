package com.example.commerce.order;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommerceOrderDetailId implements Serializable {

	private static final long serialVersionUID = 4115897150946242178L;
	
	private long commerceOrderId;
	private int seq;
}
