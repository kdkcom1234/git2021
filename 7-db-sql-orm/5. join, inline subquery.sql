select * from photo order by id asc;

-- 테이블명 별칭
select * from photo_comment pc ;

select * from photo_comment where photo_id = 4;
select * from photo_comment where photo_id = 8;

select count(*) from photo_comment where photo_id = 8;

-- inner join 
-- 테이블간 조건이 맞는 레코드끼리 결합하여 보여줌
-- 기준은 가장 왼쪽에 있는 테이블
select p.id, p.title, c."content" 
from photo p 
		inner join photo_comment c 
			on p.id = c.photo_id;
-- left join
-- 왼쪽 테이블은 모두 나옴
select p.id, p.title, c."content" 
from photo p 
		left join photo_comment c 
			on p.id = c.photo_id;

-- inline subquery		
select p.id, p.created_time , p.title,
(select count(*) from photo_comment where photo_id = p.id) comment_cnt
from photo p order by id asc;		