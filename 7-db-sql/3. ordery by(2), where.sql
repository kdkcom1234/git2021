-- order by �÷���1 ���Ĺ���, �÷���2 ���Ĺ���
-- �ð������� ������, �ð����� ���ٸ� �õ����������� ������
select * from air_sigungu_hour ash 
order by data_time desc, city_name asc
limit 25;


-- ���� �˻�1
-- where �÷� = '�÷���'
-- �÷����� ��ġ�ϴ� ���̽��� ã��
select * from air_sigungu_hour ash 
WHERE city_name = '������'
order by data_time desc
limit 12;

-- ���� �˻�2
-- where �÷� LIKE '%Ű����%'
-- �÷��� �ش� Ű���尡 ���ԵǾ� �ִ����� ã��
-- ** LIMIT�� ���� ó���� ��� ��ü ���̺� �˻��� �Ͼ�Ƿ� ���ɻ� ����ϰ� �����
select * from photo 
where description like '%���%'
limit 3; 
