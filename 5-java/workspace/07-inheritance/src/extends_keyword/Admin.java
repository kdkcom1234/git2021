package extends_keyword;

// 사용자 중에서도 관리자
// 자식클래스 extends 부모클래스
public class Admin extends User {
	private String deptNo; // 부서번호

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
