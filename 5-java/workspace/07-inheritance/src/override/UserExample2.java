package override;

public class UserExample2 {
	public static void main(String[] args) {

		// �Ϲ� �����
		User user = new User();
		user.setId("hong");
		user.setName("ȫ�浿");
		user.setPhone("01012345678");
		user.printUserInfo();

		// �θ� Ŭ���� ��ü�� �ڽ� Ŭ���� ��ü�� ������ �� ����
		// �߿��� ���� �θ� Ŭ������ �ʵ�, �޼��常 ��밡����.

		// ������
		user = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		user.setId("john");
		user.setName("John Smith");
		user.setPhone("0212345678");
		user.printUserInfo();
		// �ڽ� ��ü�� �޼���, �ʵ�� ��� �Ұ���
//		user.setDeptNo("10001");

		// ����� ���
		user = new Member();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		user.setId("kim");
		user.setName("������");
		user.setPhone("01009876543");
		// �ڽ� ��ü�� �޼���, �ʵ�� ��� �Ұ���
//		user.setPoint(100000);

		// **�ڽ� ��ü�� ������ �޼��尡 ȣ���
		user.printUserInfo();
	}
}
