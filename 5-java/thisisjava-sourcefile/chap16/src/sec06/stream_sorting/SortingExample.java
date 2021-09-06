package sec06.stream_sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortingExample {
	public static void main(String[] args) {
		//숫자 요소일 경우
		IntStream intStream = Arrays.stream(new int[] {5, 3, 2, 1, 4});
		intStream
			.sorted()
			.forEach(n -> System.out.print(n + ","));
		System.out.println();
		
		//객체 요소일 경우
		List<Student> studentList = Arrays.asList(
			new Student("홍길동", 30),
			new Student("신용권", 10),
			new Student("유미선", 20)
		);
		
		studentList.stream()
			.sorted( )
			.forEach(s -> System.out.print(s.getScore() + ","));
		System.out.println();
		
		studentList.stream()
		.sorted( Comparator.reverseOrder() )
		.forEach(s -> System.out.print(s.getScore() + ","));	
	}
}
