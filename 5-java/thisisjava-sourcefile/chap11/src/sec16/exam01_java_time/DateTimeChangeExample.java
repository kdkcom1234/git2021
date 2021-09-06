package sec16.exam01_java_time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateTimeChangeExample {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println("����: " + now);
		
		LocalDateTime  targetDateTime = null;
		
		//���� ����
		targetDateTime = now
			.withYear(2024)
			.withMonth(10)
			.withDayOfMonth(5)
			.withHour(13)
			.withMinute(30)
			.withSecond(20);
		System.out.println("���� ����: " + targetDateTime);
		
		//�⵵ ��� ����
		targetDateTime = now.with(TemporalAdjusters.firstDayOfYear());
			System.out.println("�̹� ���� ù ��: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.lastDayOfYear());
			System.out.println("�̹� ���� ������ ��: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.firstDayOfNextYear());
			System.out.println("���� ���� ù ��: " + targetDateTime);
		
		//�� ��� ����
		targetDateTime = now.with(TemporalAdjusters.firstDayOfMonth());
			System.out.println("�̹� ���� ù ��: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.lastDayOfMonth());
			System.out.println("�̹� ���� ������ ��: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.firstDayOfNextMonth());
			System.out.println("���� ���� ù ��: " + targetDateTime);
			
		//���� ��� ����
		targetDateTime = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			System.out.println("�̹� ���� ù ������: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			System.out.println("���ƿ��� ������: " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
			System.out.println("���� ������: " + targetDateTime);
	}
}

