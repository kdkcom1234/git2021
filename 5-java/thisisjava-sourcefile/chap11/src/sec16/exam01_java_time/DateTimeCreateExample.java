package sec16.exam01_java_time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeCreateExample {
	public static void main(String[] args) throws InterruptedException {
		//��¥ ���
		LocalDate currDate = LocalDate.now();
		System.out.println("���� ��¥: " + currDate);

		LocalDate targetDate = LocalDate.of(2024, 5, 10);
		System.out.println("��ǥ ��¥: " + targetDate + "\n");

		//�ð� ���
		LocalTime currTime = LocalTime.now();
		System.out.println("���� �ð�: " + currTime);
		
		LocalTime targetTime = LocalTime.of(6, 30, 0, 0);
		System.out.println("��ǥ �ð�: " + targetTime + "\n");
		
		//��¥�� �ð� ���
		LocalDateTime currDateTime = LocalDateTime.now();
		System.out.println("���� ��¥�� �ð�: " + currDateTime);
		
		LocalDateTime targetDateTime = LocalDateTime.of(2024, 5, 10, 6, 30, 0, 0);
		System.out.println("��ǥ ��¥�� �ð�: " + targetDateTime + "\n");
		
		//���� ����ÿ� �ð���(TimeZone)
		ZonedDateTime utcDateTime =   ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("���� �����: " + utcDateTime);
		ZonedDateTime newyorkDateTime = 
       ZonedDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("���� �ð���: " + newyorkDateTime + "\n");
		
		//Ư�� ������ Ÿ�ӽ����� ���
		Instant instant1 = Instant.now();
		Thread.sleep(10);
		Instant instant2 = Instant.now();		
		if(instant1.isBefore(instant2)) { 
			System.out.println("instant1�� �����ϴ�.");
		} else if(instant1.isAfter(instant2)) {
			System.out.println("instant1�� �ʽ��ϴ�.");
		} else {
			System.out.println("������ �ð��Դϴ�.");
		}
		System.out.println("����(nanos): " + instant1.until(instant2, ChronoUnit.NANOS));
	}
}
