package abstract_class;

// 사용자 중에서도 관리자
// 자식클래스 extends 부모클래스
public class Admin extends User {
	private String deptNo; // 부서번호

	// 기본생성자가 내장 되어있음
	// 내부적으로 부모 생성자를 호출하게 되어있음

	// 자식객체를 생성할 때 내부적으로 부모객체도 생성

	// public Admin () {
	// super(); // 기본 부모생성자 호출
	// }

	// 추상 메서드를 구현하지 않으면 컴파일 타임에 오류발생
	// 즉, 무조건 구현해야함
	@Override
	public void printUserInfo() {
		System.out.println("부서:" + deptNo + " - " + this.getName() + ", " + this.getPhone());
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
