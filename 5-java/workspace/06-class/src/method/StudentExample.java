package method;

public class StudentExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 자바에서는 거이 쓰지 않는 방법
		// 객체를 생성하고 필드에 값을 대입
		Student s1 = new Student();
		s1.name = "홍길동";
		s1.age = 20;
		s1.semester = 1;
		s1.major = "컴퓨터공학";
		s1.printPersonInfo();

		// 다른 방법으로 객체 생성
		Student s3 = new Student("강윤석", 28, 3, "자바웹");
		s3.printPersonInfo();
		System.out.println(s3.getMajorInfo());
	}

}
