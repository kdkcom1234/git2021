set schema 'sales';

select * from product p ;
select * from sales_order so ;
select * from sales_order_detail sod ;


-- 1. �ֹ��ݾ� �հ� �� �ְ� �ֹ��ݾ�
-- ���� * �ܰ� = �ֹ��ݾ�
select sum(quantity * unit_price), max(quantity * unit_price)  
from sales_order_detail sod ;


-- 2. **Ư�� �Ⱓ�� �ֹ� �ݾ� �հ�

-- [���̺� ����]
-- ���� ���̺��� ���� ���̺�, �ΰ����� ���̺���� �����ʿ� ������
-- �ַ� ������ �����Ͱ� ���� ���̺� ����
-- ex) �ֹ��ݾ��� sales_order_detail�� ����
-- [����]
-- FROM �������̺� JOIN ���������̺� ON ��������(ex.�����÷�=�������÷�)

-- ���̺� ���� �̸��� �� ���� alias(��Ī), ª�� �̸��� �ٲ� ����
select sum(quantity * unit_price) 
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
-- [���԰˻�]
-- WHERE �÷��� IN (�����)	
--where sod.product_name in ('Chocolade', 'Maxilaku');	
--where sod.product_name in (select name from product p);	
-- [�����˻�]
-- WHERE �÷��� BETWEEN ù��°�� AND �ι�°��
where so.date between '1997-01-01' and '1997-01-31';



-- 3. **Ư���Ⱓ �Ϻ� �ֹ��ݾ� �հ�
-- **select�� �׷����ϴ� �÷��� �;���
-- **�׿��� �÷��� �����Լ��� ��밡��(sum, min, max...)
-- �÷��� as ��Ī
select so.date, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
where so.date between '1997-01-01' and '1997-01-31'
-- Ư�� �÷��� �������� �����͸� �׷�����
-- group by �׷����� �÷�
group by so.date
order by so."date";
--order by amount desc;
-- �÷���ȣ�� ����
--order by 1;
--order by 2 desc;	


-- 4. Ư���Ⱓ ���� �ֹ��ݾ� �հ�
select substring(so.date, 1, 7) as yearmonth, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
where so.date between '1996-01-01' and '1996-12-31'
group by yearmonth
order by yearmonth;


-- 5. **Ư���Ⱓ ��ǰ��(ī�װ�)�� �ֹ��ݾ� �հ�
select p.category, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
	join product p on sod.product_id = p.id 
where so.date between '1997-01-01' and '1997-01-31'
group by p.category 
order by p.category;


-- 6. Ư���Ⱓ �Ϻ�/��ǰ���� �ֹ��ݾ� �հ�
select so.date, p.category, sum(quantity * unit_price) as amount
from sales_order_detail sod 
	join sales_order so on sod.sales_order_id = so.id
	join product p on sod.product_id = p.id 
where so.date between '1997-01-01' and '1997-01-31'
group by so.date, p.category 
order by so.date, p.category;


-- 7. ���̺��Ǻ�(pivot) Ư���Ⱓ ����/��ǰ���� �ֹ��ݾ� �հ�
select p.category, 
	sum(case 
			when substring(so.date, 1, 7) = '1996-07' 
				then quantity * unit_price -- when ���ǽĿ� �������� ��ȯ��
				else 0 					   -- when ���ǽ��� ���� ���� ���� ��ȯ��
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



