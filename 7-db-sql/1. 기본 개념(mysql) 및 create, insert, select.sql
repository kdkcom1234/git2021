-- MySQl Schema = Database 동일한 개념
-- 또한 Database가 하나의 파일로 생성됨

-- 스키마 생성
create schema myworkspace;

-- 현재 쿼리창에서 사용할 스키마 선택
use myworkspace;


create table photo (
	id bigint not null auto_increment, 
	created_time bigint not null, 
	description varchar(255), 
	file_name varchar(255), 
	file_type varchar(255), 
	photo_url BLOB, 
	title varchar(255), 
primary key (id)) engine=InnoDB;


-- 데이터를 1건 추가
-- insert into 테이블(컬럼1, 컬럼2...) values(값1, 값2)..
insert into photo(created_time, title)
values(1, 'test');



-- 테이블 데이터를 전체 조회
-- select 컬럼목록 from 테이블명;
-- 전체 컬럼 선택
select * from photo; 
-- 특정 컬럼 선택
select id, title from photo; 



-- 특정 데이터 조회
-- select 컬럼목록 from 테이블명 where 조건식
-- equal 조건식: 컬럼명 = 값
-- map.get(1)
select * from photo where id = 1; 
select * from photo where id >= 1;
