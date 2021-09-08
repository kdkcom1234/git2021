package exercise;

public class MemberServiceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberService memberSerivce = new MemberService();

		// 로그인 성공 케이스
		// 테스트 데이터 생성
		Member member = new Member("홍길동", "hong");
		member.setPassword("12345");

		// 로그인 테스트 케이스 실행
		boolean result = memberSerivce.login(member);
//		boolean result = memberSerivce.login("hong", "12345");
		if (result) {
			System.out.println("로그인 되었습니다.");
			// 로그아웃 테스트 케이스 실행
			memberSerivce.logout("hong");
		} else {
			System.out.println("id 또는 password가 올바르지 않습니다.");
		}
	}

}
