package com.example.sales.order;

import static com.example.sales.order.QSalesOrder.salesOrder;
import static com.example.sales.order.QSalesOrderDetail.salesOrderDetail;
import static com.example.sales.product.QProduct.product;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SalesOrderRepositorySupport {
	private JPAQueryFactory query;
	
	// EntityManager: JPA의 쿼리를 만드는 객체
	// JPAQueryFactory: QueryDSL의 쿼리를 만드는 객체
	@Autowired
	public SalesOrderRepositorySupport(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}
	
	// -- 특정기간 주문금액 합계
	public Long statsAmounts(String startDate, String endDate) {
//		select sum(quantity * unit_price) 
//		from sales_order_detail sod 
//			join sales_order so on sod.sales_order_id = so.id
//		where so.date between '1997-01-01' and '1997-01-31';		
		
		return query
				.select(salesOrderDetail.quantity.longValue().multiply(salesOrderDetail.unitPrice).sum())
				.from(salesOrderDetail)
					.join(salesOrder).on(salesOrderDetail.salesOrderId.eq(salesOrder.id))
				.where(salesOrder.date.between(startDate, endDate))
				.fetchOne();
	}
	
	//	-- **특정기간 일별 주문금액 합계
	public List<AmountByDate> statsAmountsByDates(String startDate, String endDate) {
		
//		select so."date" , sum(quantity*unit_price) as amount
//		from sales_order_detail sod 
//				join sales_order so on sod.sales_order_id = so.id 
//		where so.date between '1997-01-01' and '1997-01-31';	
//		group by so."date" 
//		order by 1 asc;	
		
		// select(Projections.fields(변환할클래스타입, 필드목....))
		// .as("별칭")
		return query
		.select(Projections.fields(AmountByDate.class, 
				salesOrder.date, 
				salesOrderDetail.quantity.longValue()
					.multiply(salesOrderDetail.unitPrice).sum().as("amount")))
		.from(salesOrderDetail)
			.join(salesOrder).on(salesOrderDetail.salesOrderId.eq(salesOrder.id))
		.where(salesOrder.date.between(startDate, endDate))
		.groupBy(salesOrder.date)
		.orderBy(salesOrder.date.asc())
		.fetch();
	}
	
	//	-- **특정기간 제품군(카테고리)별 주문금액 합계
	public List<AmountByCategory> statsAmountsByCategories(String startDate, String endDate) {
		
//		select p.category, sum(quantity*unit_price) as amount
//		from sales_order_detail sod
//				join sales_order so on sod.sales_order_id = so.id 
//				join product p on sod.product_id =p.id 
//		where so."date" between '1997-02-01' and '1997-02-29'
//		group by p.category 
//		order by 1 asc;
	
		return query
		.select(Projections.fields(AmountByCategory.class, 
				product.category, 
				salesOrderDetail.quantity.multiply(salesOrderDetail.unitPrice).sum().as("amount")))
		.from(salesOrderDetail)
			.join(salesOrder).on(salesOrderDetail.salesOrderId.eq(salesOrder.id))
			.join(product).on(salesOrderDetail.product.id.eq(product.id))
		.where(salesOrder.date.between(startDate, endDate))
		.groupBy(product.category)
		.orderBy(product.category.asc())
		.fetch();
	}		
}
