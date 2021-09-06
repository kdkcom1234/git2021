package sec01.stream_introduction;

import java.util.Arrays;
import java.util.List;

public class MapAndReduceExample {
	public static void main(String[] args) {
		List<Student> studentList = Arrays.asList(
				new Student("홍길동", 10),
				new Student("신용권", 20),
				new Student("유미선", 30)
		);		
			
		double avg = studentList.stream()
			//중간처리(학생 객체를 점수로 매핑)
			.mapToInt(Student :: getScore)
			//최종 처리(평균 점수)
			.average()
			.getAsDouble();
		
		System.out.println("평균 점수: " + avg);
	}
}
