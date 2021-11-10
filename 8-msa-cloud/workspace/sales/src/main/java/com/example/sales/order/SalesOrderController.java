package com.example.sales.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesOrderController {

	SalesOrderRepositorySupport support;
	
	@Autowired
	public SalesOrderController(SalesOrderRepositorySupport support) {
		this.support = support;
	}
	
	// GET /sales-orders/stats?sd=1997-02-01&ed=1997-02-29
	@GetMapping("/sales-orders/stats")
	public AmountStat getStats(@RequestParam String sd, @RequestParam String ed){
		return AmountStat
				.builder()
				.amounts(support.statsAmounts(sd, ed))
				.amountsByDates(support.statsAmountsByDates(sd, ed))
				.amountsByCategories(support.statsAmountsByCategories(sd, ed))
				.build();
	}	
	
	// GET /sales-orders/amounts?sd=1997-02-01&ed=1997-02-29
	@GetMapping("/sales-orders/amounts")
	public Long getAmounts(@RequestParam String sd, @RequestParam String ed){
		return support.statsAmounts(sd, ed);
	}	
	
	// GET /sales-orders/amounts-by-dates?sd=1997-02-01&ed=1997-02-29
	@GetMapping("/sales-orders/amounts-by-dates")
	public List<AmountByDate> getAmountsByDates(@RequestParam String sd, @RequestParam String ed){
		return support.statsAmountsByDates(sd, ed);
	}
	
	// GET /sales-orders/amounts-by-categories?sd=1997-02-01&ed=1997-02-29
	@GetMapping("/sales-orders/amounts-by-categories")
	public List<AmountByCategory> getAmountsByCategories(@RequestParam String sd, @RequestParam String ed){
		return support.statsAmountsByCategories(sd, ed);
	}	
}
