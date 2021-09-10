package polymorphism;

import override.Admin;
import override.Member;
import override.User;

public class UserExample2 {
	public static void main(String[] args) {

		// 일반 사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("01012345678");
		user.printUserInfo();

		// param: User user <- User user
		sendMessage(user);

		// 관리자
		Admin admin = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0212345678");
		admin.printUserInfo();
		// 추가 필드 및 메서드 사용, 부서번호
		admin.setDeptNo("10001");
		// param: User user <- Admin admin
		sendMessage(admin);

		// 멤버십 멤버
		Member member = new Member();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		member.setId("kim");
		member.setName("김쿠팡");
		member.setPhone("01009876543");
//		member.printUserInfo();
		// 추가 필드 및 메서드 사용, 포인트
		member.setPoint(100000);
		member.printUserInfo();
		// param: User user <- Member member
		sendMessage(member);
	}

	// 부모타입으로 매개변수를 선언함
	public static void sendMessage(User user) {
		System.out.println("");
		System.out.println(user.getPhone() + ": 공지사항 메시지를 보냅니다");
		/// .. email, sms, push notification
	}
}
