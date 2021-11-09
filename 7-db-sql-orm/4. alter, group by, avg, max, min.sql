
-- column Ÿ�� ����
alter table air_sigungu_hour 
alter column pm10value type int using (nullif(pm10value, '')::integer);

alter table air_sigungu_hour 
alter column pm25value type int using (nullif(pm25value, '')::integer);



select * from air_sigungu_hour ash ;

-- ���� pm10 ���, �ּ�, �ִ밪
-- ex) ������ 30 10 50
--     �߶��� 25  5 45

-- group by: Ư�� �÷����� �������� �׷�����
select city_name from air_sigungu_hour
group by city_name;


-- group by �̿��� �÷��� ���谡 ������ �÷��� ����
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
where city_name = '������'
group by city_name
order by 2 desc, city_name asc;