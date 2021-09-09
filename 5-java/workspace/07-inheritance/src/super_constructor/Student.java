package super_constructor;

public class Student extends Person {
	private int studentNo;

	// quick fix를 하게 되면 부모 생성자에 맞는 생성자를 만들어줌
	// Person(String name, String phone) : 부모생성자
	// -> Student(String name, String phone)
	// 다른 필드를 같이 초기화해야하면 매개변수 및 초기화 구문 추가
	public Student(String name, String phone, int studentNo) {
		super(name, phone);
		this.studentNo = studentNo;
	}

	// 기본 생성자가 내부적으로
	// public Student() {
	// super(); 못함... 에러발생 -> 명시적으로 생성자를 만들어야함
	// }

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

}
