package com.example.sales.order;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDetailId implements Serializable {

	private static final long serialVersionUID = 4115897150946242178L;
	
	private long salesOrderId;
	private int seq;
}
