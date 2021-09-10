package casting;

public class UserExample {
	public static void main(String[] args) {

		// 일반 사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("01012345678");
		user.printUserInfo();

		// 부모 클래스 객체에 자식 클래스 객체를 대입할 수 있음
		// 중요한 것은 부모 클래스의 필드, 메서드만 사용가능함.

		// 관리자
		user = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		user.setId("john");
		user.setName("John Smith");
		user.setPhone("0212345678");
		user.printUserInfo();
		// 자식 객체의 메서드, 필드는 사용 불가함
//		user.setDeptNo("10001");
		// 가능함 Admin <- Admin
		Admin admin2 = (Admin) user;

		// 멤버십 멤버
		user = new Member();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		user.setId("kim");
		user.setName("김쿠팡");
		user.setPhone("01009876543");
		// 자식 객체의 메서드, 필드는 사용 불가함
//		user.setPoint(100000);

		// **자식 객체의 재정의 메서드가 호출됨
		user.printUserInfo();

		// 컴파일 타임에서는 오류가 발생하지 않음
		// 실행 타임에서는 오류가 발생함 Admin <- Member
		// intanceof 연산을 통해 해당 클래스의 객체가 맞는지 확인
		// user객체가 Admin타입의 인스턴스인지 확인
		if (user instanceof Admin) {
			Admin admin3 = (Admin) user;
		}

		// 모든 클래스들의 최상위 부모가 Object 클래스임
		// extends를 쓰고 있지 않지만 내부적으로 extends 되어 있는 상태
		Object obj = new Object();
		obj = user;

		System.out.println();
		System.out.println(obj);
		// 강제로 형식 객체 형식 변환을 할 때는 instanceof 사용
		if (obj instanceof Admin) {
			Admin admin4 = (Admin) obj;
			System.out.println(admin4);
		}
	}
}
