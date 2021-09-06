package verify.exam08;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample {
	public static void main(String[] args) {
		List<Member> list = Arrays.asList(
			new Member("홍길동", "개발자"),
			new Member("김나리", "디자이너"),
			new Member("신용권", "개발자")
		);
		
		Map<String, List<String>> groupingMap = list.stream()
			.collect(
				Collectors.groupingBy(
					Member::getJob, 
					Collectors.mapping(Member::getName, Collectors.toList())
				)
			); 

		System.out.print("[개발자] ");
		groupingMap.get("개발자").stream().forEach(s -> System.out.print(s + " "));
		System.out.print("\n[디자이너] ");
		groupingMap.get("디자이너").stream().forEach(s -> System.out.print(s + " "));
	}
	
	static class Member {
		private String name;
		private String job;
		
		
		public Member(String name, String job) {
			this.name = name;
			this.job = job;
		}
		
		public String getName() { return name; }
		public String getJob() { return job; }
	}
}
