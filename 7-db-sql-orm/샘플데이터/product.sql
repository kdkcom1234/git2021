-- sales.product definition

-- Drop table

-- DROP TABLE sales.product;

CREATE TABLE sales.product (
	id int4 NOT NULL,
	code varchar(255) NULL,
	"name" varchar(255) NULL,
	price int4 NOT NULL,
	category varchar(255) NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);