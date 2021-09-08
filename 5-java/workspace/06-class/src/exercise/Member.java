package exercise;

import constructor.sub.Student;

// Member 클래스를 선언
// extends 확장
// Member 자식 extends Student 부모
public class Member extends Student {
	// 자바에서 기본적으로 필드는 private 접근제한자로 선언
	// 자바에서 필드를 public, protected 같은거 안 씀
	private String name;
	// getter, setter로 외부에 공개된 필드를 property라고 함
	private String id;
	private String password;
	private int age;
	private String abcd;

	// 필드를 접근하게 해주는 메서드를 작성

	// get필드명: 필드의 값을 가져오는 메서드
	// Getter
	// public 필드타입 get필드명() {
	// return this.필드명;
	// }
	public String getName() {
//		return this.name;
		return "[" + this.name + "]";
	}

	// set필드명: 필드의 값을 설정하는 메서드
	// Setter
	// public void set필드명(필드타입 변수명) {
	// this.필드명 = 변수명;
	// }
	public void setAge(int age) {
		this.age = age;
	}

	// 생성자를 선언
	// 이름과 id를 초기화는 생성자
	Member(String name, String id) {
		this.name = name;
		this.setId(id);
		// 상속받았기 때문에 사용가능
		this.semester = 1; // protected로 제한한 필드
		this.joinCourse(); // proetected로 제한한 메서드
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbcd() {
		return abcd;
	}

	public void setAbcd(String abcd) {
		this.abcd = abcd;
	}
}
