package classnew;

public class StudentExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// new 클래스명()
		// : 인스턴스(instance)를 생성, instantiation
		// -> 클래스 구조의 메모리 공간을 생성
		// 인스턴스 == 객체(object)

		// Student s1 = 생성된 인스턴스
		// 클래스명 인스턴스변수 = new 클래스명()

		// 홍길동이라는 학생에 대한 정보
		Student s1 = new Student();
		// 필드: 홍길동이라는 학생의 데이터
		// 필드에 접근: 인스턴스변수명.필드명
		s1.name = "홍길동";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "컴퓨터공학";
		System.out.println(s1.name + " " + s1.age);
		// 메서드: 홍길동이라는 학생의 수강신청 기능
		// 2학기, 컴퓨터공학과 학생이 수강신청...
		// 메서드에 접근: 인스턴스변수명.메서드명
		s1.joinCourse();

		// 존스미스라는 학생에 대한 정보
		Student s2 = new Student();
		// 필드: 존스미스라는 학생의 데이터
		s2.name = "John Smith";
		s2.age = 21;
		s2.semester = 3;
		s2.major = "경영학";
		System.out.println(s2.name + " " + s2.age);
		// 메서드: 존스미스라는 학생의 수강신청 기능
		// 3학기, 경영학 학생이 수강신청
		s2.joinCourse();
	}

}
