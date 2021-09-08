package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample {

	public static void main(String[] args) {
		// Map<키타입, 값타입>
		// 매개변수로 넣어주는 타입: 제네릭, 제너릭, generic
		// 키: Member의 id
		// 값: Member 객체
		Map<String, Member> members = new HashMap<String, Member>();

		// 1. 객체 추가
		// 새로운 객체 생성를 생성하고 map변수의 put 메서드로 추가
		Member hong = new Member("hong", "password123", "홍길동", 20);
		members.put("hong", hong);

		Member smith = new Member("john", "abcd1234", "John Smith", 30);
		members.put("john", smith);

	}

}
