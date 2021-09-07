package method;

public class Student {

	String name;
	int age;
	int semester;
	String major;

	// 생성자는 반환형식이 없음
	// 클래스 이름과 동일(대문자로 시작)
	Student() {
	}

	// 이름, 나이, 학기, 학과 받고 필드 초기화 및 객체 생성
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}

	// void: 반환형식이 없음
	// 반환형식 메서드명() {...}
	// 무조건 메서드는 camel-case
	void printPersonInfo() {
		System.out.println(this.name + " " + this.age);
	}

	// 거이 데이터를 가져오는 메서드는 get.... 이런형태
	String getMajorInfo() {
		return this.major + ", 학기: " + this.semester;
	}
}
