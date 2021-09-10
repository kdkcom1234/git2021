package casting;

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

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
