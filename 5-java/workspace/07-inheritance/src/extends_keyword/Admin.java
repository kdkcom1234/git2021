package extends_keyword;

// ����� �߿����� ������
// �ڽ�Ŭ���� extends �θ�Ŭ����
public class Admin extends User {
	private String deptNo; // �μ���ȣ

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
