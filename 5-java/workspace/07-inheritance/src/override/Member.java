package override;

// ����� �߿����� ������� �ִ� �����(���� �����)
public class Member extends User {
	private int point;

	// �޼��� �������̵�(override) - �޼��带 �������Ѵ�.
	// �޼��� �ñ״�ó(signature): �޼����+�Ű�����(Ÿ��,����,����)
	// �θ��� �޼���� �޼��� �ñ״�ó�� �����ؾ���
	// �����ϸ� @Override�� ���ִ°� ����, �׷��� �������Ѱ��� �� �� ����
	@Override
	public void printUserInfo() {

		// ���� ������ �ٸ�

		// 1. ���� ���� ����
		// System.out.println(this.getName() + ", " + this.getPhone() + " - ����Ʈ: " +
		// this.point);

		// 2. �θ� �޼��带 ��Ȱ��
		super.printUserInfo();
		System.out.print(" - ����Ʈ: " + this.point);
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
