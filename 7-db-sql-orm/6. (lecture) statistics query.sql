set schema 'sales';

select * from product p ;
select * from sales_order so ;
select * from sales_order_detail sod ;


-- 1. 주문금액 합계 및 최고 주문금액
-- 수량 * 단가 = 주문금액
select sum(quantity * unit_price), max(quantity * unit_price)  
from sales_order_detail sod ;


-- 2. **특정 기간의 주문 금액 합계

-- [테이블 조인]
-- 왼쪽 테이블이 기준 테이블, 부가적인 테이블들을 오른쪽에 결합함
-- 주로 참조할 데이터가 왼쪽 테이블에 있음
-- ex) 주문금액은 sales_order_detail에 있음
-- [구문]
-- FROM 왼쪽테이블 JOIN 오른쪽테이블 ON 연결조건(ex.왼쪽컬럼=오른쪽컬럼)

-- 테이블 옆에 이름을 쓴 것을 alias(별칭), 짧게 이름을 바꿔 쓴것
select sum(quantity * unit_price) 
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
-- [포함검색]
-- WHERE 컬럼명 IN (값목록)	
--where sod.product_name in ('Chocolade', 'Maxilaku');	
--where sod.product_name in (select name from product p);	
-- [범위검색]
-- WHERE 컬럼명 BETWEEN 첫번째값 AND 두번째값
where so.date between '1997-01-01' and '1997-01-31';



-- 3. **특정기간 일별 주문금액 합계
-- **select에 그룹핑하는 컬럼이 와야함
-- **그외의 컬럼은 집계함수만 사용가능(sum, min, max...)
-- 컬럼명 as 별칭
select so.date, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
where so.date between '1997-01-01' and '1997-01-31'
-- 특정 컬럼을 기준으로 데이터를 그룹핑함
-- group by 그룹핑할 컬럼
group by so.date
order by so."date";
--order by amount desc;
-- 컬럼번호로 정렬
--order by 1;
--order by 2 desc;	


-- 4. 특정기간 월별 주문금액 합계
select substring(so.date, 1, 7) as yearmonth, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
where so.date between '1996-01-01' and '1996-12-31'
group by yearmonth
order by yearmonth;


-- 5. **특정기간 제품군(카테고리)별 주문금액 합계
select p.category, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
	join product p on sod.product_id = p.id 
where so.date between '1997-01-01' and '1997-01-31'
group by p.category 
order by p.category;


-- 6. 특정기간 일별/제품군별 주문금액 합계
select so.date, p.category, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
	join product p on sod.product_id = p.id 
where so.date between '1997-01-01' and '1997-01-31'
group by so.date, p.category 
order by so.date, p.category;


-- 7. 테이블피봇(pivot) 특정기간 월별/제품군별 주문금액 합계
select p.category, 
	sum(case 
			when substring(so.date, 1, 7) = '1996-07' 
				then quantity * unit_price -- when 조건식에 맞을때의 반환값
				else 0 					   -- when 조건식이 맞지 않을 떄의 반환값
		end
		) as "1996-07",
	sum(case when substring(so.date, 1, 7) = '1996-08' then quantity * unit_price else 0 end) as "1996-08",
	sum(case when substring(so.date, 1, 7) = '1996-09' then quantity * unit_price else 0 end) as "1996-09",	
	sum(case when substring(so.date, 1, 7) = '1996-10' then quantity * unit_price else 0 end) as "1996-10",		
	sum(case when substring(so.date, 1, 7) = '1996-11' then quantity * unit_price else 0 end) as "1996-11",	
	sum(case when substring(so.date, 1, 7) = '1996-12' then quantity * unit_price else 0 end) as "1996-12"		
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
	join product p on sod.product_id = p.id 
where so.date between '1996-07-01' and '1996-12-31'
group by p.category 
order by p.category;



