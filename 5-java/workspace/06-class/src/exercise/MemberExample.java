package exercise;

public class MemberExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member member1 = new Member("ȫ�浿", "hong");
		Member member2 = new Member("���ڹ�", "java");

		System.out.println(member1.name + " " + member1.id);
		System.out.println(member2.name + " " + member2.id);
	}
}
