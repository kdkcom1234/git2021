package exercise;

public class MemberService {

	// Member 객체를 메서드 파라미터로 받음
	// Dependency: 의존성이 있음
	// Dependency<<Usage>>: 구현이나 기능처리에 다른 클래스가 필요함
	boolean login(Member member) {
		if (member.id == "hong" && member.password == "12345") {
			return true;
		}
		return false;
	}

//	boolean login(String id, String password) {
//		if (id == "hong" && password == "12345") {
//			return true;
//		} else {
//			return false;
//		}
//	}

	void logout(String id) {
		System.out.println("로그아웃 되었습니다");
	}

}
