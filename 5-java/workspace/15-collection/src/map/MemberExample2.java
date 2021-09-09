package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample2 {

	public static void main(String[] args) {
		// Map<키타입, 값타입>
		// Generic: 제너릭, 줴네릭
		// 타입으로 들어가는 매개변수
		// 키가 Integer, String이든 상관없이 키가 같은지 비교할 때는
		// hashcode 메서드와 equals 메서드를 호출
		Map<Integer, Member> members = new HashMap<Integer, Member>();

//		IntegerMap
//		StringMap

//		Map<Member, Contact>
//		// Member: id, name, age
//		// Contact: home, company, mobile
//
//		String name = "hong";
//		System.out.println(name.hashCode());
	}

}
