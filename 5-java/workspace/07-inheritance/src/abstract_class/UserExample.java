package abstract_class;

public class UserExample {
	public static void main(String[] args) {

		// ����� ������ ���� ������ ��Ÿ���� ������ ��ü�� ���������� ����
		// �߻�Ŭ������ �ν��Ͻ��� �������� ���� - ���״�� ��ü�� ����
//		User user = new User();
//		user.setId("hong");
//		user.setName("ȫ�浿");
//		user.setPhone("01012345678");
//		user.printUserInfo();

		// **�ڽİ�ü�� �⺻ �ʵ峪 �޼��� ���� �����ϰ�
		// **�ڽĿ��� ������ �޼���� �߻�޼���� ����

		// ������
		Admin admin = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0212345678");
		// �߰� �ʵ� �� �޼��� ���, �μ���ȣ
		admin.setDeptNo("10001");
		// ������ �޼��� ���
		admin.printUserInfo();

		// ����� ���
		Member member = new Member();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		member.setId("kim");
		member.setName("������");
		member.setPhone("01009876543");
		// �߰� �ʵ� �� �޼��� ���, ����Ʈ
		member.setPoint(100000);
		// ������ �޼��� ���
		member.printUserInfo();
	}
}
