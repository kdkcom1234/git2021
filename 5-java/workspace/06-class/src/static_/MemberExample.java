package static_;

public class MemberExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// static �޼��忡 �����ϴ� ����� Ŭ������.�޼����(...)
		System.out.println(Member.SERVICE_NAME);
		Member.printServiceName();

		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member member1 = new Member("ȫ�浿", "hong");
		Member member2 = new Member("���ڹ�", "java");

		// static ������ �����ϴ� ����� Ŭ������.������
//		System.out.println(Member.serviceName + ", �̸�: " + member1.name);
//		System.out.println(Member.serviceName + ", �̸�: " + member2.name);

		Member.printNameWithServiceName(member1.name);
		Member.printNameWithServiceName(member2.name);

		System.out.println("ȸ����: " + Member.memberCount);

		singleton.Calculator calc = singleton.Calculator.getInstance();
		calc.getAreaCircle(25);
	}
}
