package exercise;

// 1. 클래스를 import하고
import constructor.sub.Student;
// 다른 패키지의 같은 클래스명을 가진 클래스는 import 불가함
//import constructor.Student;

public class MemberExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member member1 = new Member("홍길동", "hong");
		Member member2 = new Member("강자바", "java");
		member1.setAge(20);

		// member1.password // private이라 외부에서 접근 불가임

		// 2. 클래스를 사용 - constructor.sub.Student
		Student student = new Student();
		System.out.println(student.age); // public으로 열려있는 필드

		// 2. 클래스를 사용 - 패키지명까지 작성
		constructor.Student student2 = new constructor.Student();

		System.out.println(member1.getName() + " " + member1.getId());
		System.out.println(member2.getName() + " " + member2.getId());
	}
}
