package com.example.sales.order.stats;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmountStat {
	private Long amounts;
	private List<AmountByDate> amountsByDates;
	private List<AmountByCategory> amountsByCategories;
}
