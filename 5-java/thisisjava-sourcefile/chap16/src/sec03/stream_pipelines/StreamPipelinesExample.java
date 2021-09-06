package sec03.stream_pipelines;

import java.util.Arrays;
import java.util.List;

public class StreamPipelinesExample {
	public static void main(String[] args) {
		List<Member> list = Arrays.asList(
				new Member("홍길동", Member.MALE, 30),
				new Member("김나리", Member.FEMALE, 20),
				new Member("신용권", Member.MALE, 45),
				new Member("박수미", Member.FEMALE, 27)
		);
		
		double ageAvg =   list.stream()
			.filter(m -> m.getSex()==Member.MALE)
			.mapToInt(Member :: getAge)
			.average()
			.getAsDouble();
		
		System.out.println("남자 평균 나이: " + ageAvg);
	}
}


