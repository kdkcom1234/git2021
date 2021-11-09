-- order by 컬럼명1 정렬방향, 컬럼명2 정렬방향
-- 시간값으로 역정렬, 시간값이 같다면 시도구군명으로 순정렬
select * from air_sigungu_hour ash 
order by data_time desc, city_name asc
limit 25;


-- 조건 검색1
-- where 컬럼 = '컬럼값'
-- 컬럼명이 일치하는 케이스를 찾음
select * from air_sigungu_hour ash 
WHERE city_name = '강남구'
order by data_time desc
limit 12;

-- 조건 검색2
-- where 컬럼 LIKE '%키워드%'
-- 컬럼에 해당 키워드가 포함되어 있는지를 찾음
-- ** LIMIT가 없이 처리될 경우 전체 테이블 검색이 일어나므로 성능상 고려하고 써야함
select * from photo 
where description like '%펭귄%'
limit 3; 
