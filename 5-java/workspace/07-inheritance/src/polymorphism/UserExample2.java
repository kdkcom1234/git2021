package polymorphism;

import override.Admin;
import override.Member;
import override.User;

public class UserExample2 {
	public static void main(String[] args) {

		// �Ϲ� �����
		User user = new User();
		user.setId("hong");
		user.setName("ȫ�浿");
		user.setPhone("01012345678");
		user.printUserInfo();

		// param: User user <- User user
		sendMessage(user);

		// ������
		Admin admin = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0212345678");
		admin.printUserInfo();
		// �߰� �ʵ� �� �޼��� ���, �μ���ȣ
		admin.setDeptNo("10001");
		// param: User user <- Admin admin
		sendMessage(admin);

		// ����� ���
		Member member = new Member();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		member.setId("kim");
		member.setName("������");
		member.setPhone("01009876543");
//		member.printUserInfo();
		// �߰� �ʵ� �� �޼��� ���, ����Ʈ
		member.setPoint(100000);
		member.printUserInfo();
		// param: User user <- Member member
		sendMessage(member);
	}

	// �θ�Ÿ������ �Ű������� ������
	public static void sendMessage(User user) {
		System.out.println("");
		System.out.println(user.getPhone() + ": �������� �޽����� �����ϴ�");
		/// .. email, sms, push notification
	}
}
