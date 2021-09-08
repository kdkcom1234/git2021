package static_;

public class MemberExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// static 메서드에 접근하는 방법은 클래스명.메서드명(...)
		System.out.println(Member.SERVICE_NAME);
		Member.printServiceName();

		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member member1 = new Member("홍길동", "hong");
		Member member2 = new Member("강자바", "java");

		// static 변수에 접근하는 방법은 클래스명.변수명
//		System.out.println(Member.serviceName + ", 이름: " + member1.name);
//		System.out.println(Member.serviceName + ", 이름: " + member2.name);

		Member.printNameWithServiceName(member1.name);
		Member.printNameWithServiceName(member2.name);

		System.out.println("회원수: " + Member.memberCount);

		singleton.Calculator calc = singleton.Calculator.getInstance();
		calc.getAreaCircle(25);
	}
}
