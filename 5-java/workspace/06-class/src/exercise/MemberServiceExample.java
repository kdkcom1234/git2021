package exercise;

public class MemberServiceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberService memberSerivce = new MemberService();
		// 로그인 성공 케이스
		boolean result = memberSerivce.login("hong", "12345");
		// 로그인 실패 케이스
//		boolean result = memberSerivce.login("hong", "1234");
//		boolean result = memberSerivce.login("hon", "12345");
		if (result) {
			System.out.println("로그인 되었습니다.");
			memberSerivce.logout("hong");
		} else {
			System.out.println("id 또는 password가 올바르지 않습니다.");
		}
	}

}
