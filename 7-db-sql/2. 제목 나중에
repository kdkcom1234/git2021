-- DDL(Data Definition Lanaguage)
-- 데이터 정의 언어
-- CREATE, TRUNCATE, ALTER .. 
-- 데이터구조를 정의하거나 변경하는 언어

-- DML(Data Manipulation Lanage)
-- 데이터 조작 언어
-- INSERT, UPDATE, DELETE, SELECT

-- 전체 테이블 데이터 삭제 및 구조초기화
-- restart identity 자동증가값 초기화

-- delete 명령어는 트랜잭션(transaction, 논리적인 처리단위) 로그(log)에 기록됨
-- truncate는 테이블 구조 초기화 이다보니 log에 기록이 안 됨
truncate table photo restart identity;


-- 기본적으로 Primary Key로 정렬
-- Index(목차): 특정 컬럼 또는 컬럼들이 별도의 데이터 공간을 가지고 있고, 실제 데이터 가리키고 있는 자료구조
select * from photo;
select * from photo where id = 1;


-- clustered index로 변경: 데이터 정렬 순서를 인덱스 순서에 맞게
cluster photo using photo_pkey;


-- SQL 정렬: ORDER BY
-- ORDER BY 컬럼명 정렬방법(desc, asc)
-- desc: 역정렬, asc: 순정렬
select * from photo order by id desc;
select * from photo order by title desc, id asc;

-- SHOW lc_collate;

-- COUNT: 데이터 개수 
-- 주로 count(*) 치면됨.
select count(*) from photo;
select count(id) from photo;

-- LIMIT: 데이터의 개수, OFFSET: 건너띌 개수
-- id 역정렬하고 앞에있는 2개만 가져오세요.
select * from photo 
order by id desc 
limit 2;

-- 앞쪽에서 2개 건너띄고 2개만 가져오세요.
select * from photo 
order by id desc 
limit 2 offset 1*2;

-- 앞쪽에서 4개 건너띄고 2개만 가져오세요.
select * from photo 
order by id desc 
limit 2 offset 2*2;

