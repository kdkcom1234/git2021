package super_constructor;

// ����� �߿����� ������
// �ڽ�Ŭ���� extends �θ�Ŭ����
public class Admin extends User {
	private String deptNo; // �μ���ȣ

	// �⺻�����ڰ� ���� �Ǿ�����
	// ���������� �θ� �����ڸ� ȣ���ϰ� �Ǿ�����

	// �ڽİ�ü�� ������ �� ���������� �θ�ü�� ����

	// public Admin () {
	// super(); // �⺻ �θ������ ȣ��
	// }

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
