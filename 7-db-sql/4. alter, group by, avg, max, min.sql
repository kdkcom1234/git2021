
-- column 타입 변경
alter table air_sigungu_hour 
alter column pm10value type int using (nullif(pm10value, '')::integer);

alter table air_sigungu_hour 
alter column pm25value type int using (nullif(pm25value, '')::integer);



select * from air_sigungu_hour ash ;

-- 구별 pm10 평균, 최소, 최대값
-- ex) 강남구 30 10 50
--     중랑구 25  5 45

-- group by: 특정 컬럼값을 기준으로 그룹핑함
select city_name from air_sigungu_hour
group by city_name;


-- group by 이외의 컬럼은 집계가 가능한 컬럼만 가능
select city_name, 
	avg(pm10value)::int pm10_avg, 
	min(pm10value) pm10_min, 
	max(pm10value) pm10_max 
from air_sigungu_hour
group by city_name
order by 2 desc, city_name asc;


select city_name, 
	avg(pm10value)::int pm10_avg, 
	min(pm10value) pm10_min, 
	max(pm10value) pm10_max 
from air_sigungu_hour
where city_name = '강남구'
group by city_name
order by 2 desc, city_name asc;