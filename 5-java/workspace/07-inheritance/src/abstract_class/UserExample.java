package abstract_class;

public class UserExample {
	public static void main(String[] args) {

		// 사용자 종류에 대한 구조만 나타내고 실제로 객체를 생성하지는 않음
		// 추상클래스는 인스턴스를 생성하지 못함 - 말그대로 실체가 없음
//		User user = new User();
//		user.setId("hong");
//		user.setName("홍길동");
//		user.setPhone("01012345678");
//		user.printUserInfo();

		// **자식객체의 기본 필드나 메서드 들을 정의하고
		// **자식에서 구현할 메서드는 추상메서드로 정의

		// 관리자
		Admin admin = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0212345678");
		// 추가 필드 및 메서드 사용, 부서번호
		admin.setDeptNo("10001");
		// 구현한 메서드 사용
		admin.printUserInfo();

		// 멤버십 멤버
		Member member = new Member();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		member.setId("kim");
		member.setName("김쿠팡");
		member.setPhone("01009876543");
		// 추가 필드 및 메서드 사용, 포인트
		member.setPoint(100000);
		// 구현한 메서드 사용
		member.printUserInfo();
	}
}
