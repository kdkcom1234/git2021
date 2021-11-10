-- sales.sales_order_detail definition

-- Drop table

-- DROP TABLE sales.sales_order_detail;

CREATE TABLE sales.sales_order_detail (
	sales_order_id int8 NOT NULL,
	seq int4 NOT NULL,
	product_name varchar(255) NULL,
	quantity int4 NOT NULL,
	unit_price int4 NOT NULL,
	product_id int4 NULL,
	CONSTRAINT sales_order_detail_pkey PRIMARY KEY (sales_order_id, seq)
);


-- sales.sales_order_detail foreign keys

ALTER TABLE sales.sales_order_detail ADD CONSTRAINT fk4t15y62dixfxu4tye03ed9abl FOREIGN KEY (sales_order_id) REFERENCES sales.sales_order(id);
ALTER TABLE sales.sales_order_detail ADD CONSTRAINT fkgeemxaf5bbmq6nb1ph3h26mnw FOREIGN KEY (product_id) REFERENCES sales.product(id);