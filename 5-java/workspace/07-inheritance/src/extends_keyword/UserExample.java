package extends_keyword;

public class UserExample {
	public static void main(String[] args) {

		// 일반 사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("01012345678");
		user.printUserInfo();

		// 관리자
		Admin admin = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0212345678");
		admin.printUserInfo();
		// 추가 필드 및 메서드 사용, 부서번호
		admin.setDeptNo("10001");

		// 멤버십 멤버
		Member member = new Member();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		member.setId("kim");
		member.setName("김쿠팡");
		member.setPhone("01009876543");
		member.printUserInfo();
		// 추가 필드 및 메서드 사용, 포인트
		member.setPoint(100000);
	}
}
