set schema 'sales';

select * from product p ;
select * from sales_order so;
select * from sales_order_detail sod;


-- 주문 금액 합계
select sum(quantity*unit_price) as amount
from sales_order_detail sod;

-- **특정기간 주문금액 합계
select sum(quantity*unit_price) as amount
from sales_order_detail sod
		join sales_order so on sod.sales_order_id = so.id 
where so."date" between '1997-02-01' and '1997-02-29';


-- 일별 주문금액 합계
select so."date" , sum(quantity*unit_price) as amount
from sales_order_detail sod 
		join sales_order so on sod.sales_order_id = so.id 
group by so."date" 
order by 1 desc;

-- 월별 주문금액 합계
select substring(so."date", 1, 7) year_month , sum(quantity*unit_price) as amount
from sales_order_detail sod 
		join sales_order so on sod.sales_order_id = so.id 
group by substring(so."date", 1, 7) 
order by 1 desc;

-- **특정기간 일별 주문금액 합계
select so."date" , sum(quantity*unit_price) as amount
from sales_order_detail sod 
		join sales_order so on sod.sales_order_id = so.id 
where so."date" between '1997-02-01' and '1997-02-29'
group by so."date" 
order by 1 asc;


-- 제품별 주문금액 합계
select product_name, sum(quantity*unit_price) as amount 
from sales_order_detail sod 
group by product_name
order by 2 desc;

-- 제품군(카테고리)별 주문금액 합계
select p.category, sum(quantity*unit_price) as amount
from sales_order_detail sod
		join product p on sod.product_id =p.id 
group by p.category 
order by 2 desc;

-- **특정기간 제품군(카테고리)별 주문금액 합계
select p.category, sum(quantity*unit_price) as amount
from sales_order_detail sod
		join sales_order so on sod.sales_order_id = so.id 
		join product p on sod.product_id =p.id 
where so."date" between '1997-02-01' and '1997-02-29'
group by p.category 
order by 1 asc;




-- 일별/제품군별 주문금액 합계
select so."date", p.category, sum(quantity*unit_price) as amount
from sales_order_detail sod 
		join sales_order so on sod.sales_order_id = so.id
		join product p on sod.product_id = p.id
group by so."date", p.category 
where so."date" between '1997-02-01' and '1997-02-29'
order by 1 asc;


-- 월별/제품군별 주문금액 합계- 테이블 피봇(pivot)
select p.category , 
		sum(case when substring(so."date", 1, 7) = '1997-02' then (quantity*sod.unit_price) else 0 end) as "1997-02",
		sum(case when substring(so."date", 1, 7) = '1997-01' then (quantity*sod.unit_price) else 0 end) as "1997-01",
		sum(case when substring(so."date", 1, 7) = '1996-12' then (quantity*sod.unit_price) else 0 end) as "1996-12",
		sum(case when substring(so."date", 1, 7) = '1996-11' then (quantity*sod.unit_price) else 0 end) as "1996-11",
		sum(case when substring(so."date", 1, 7) = '1996-10' then (quantity*sod.unit_price) else 0 end) as "1996-01",
		sum(case when substring(so."date", 1, 7) = '1996-09' then (quantity*sod.unit_price) else 0 end) as "1996-09"		
from sales_order_detail sod
		join sales_order so on sod.sales_order_id = so.id
		join product p on sod.product_id = p.id
group by p.category 
order by 1;