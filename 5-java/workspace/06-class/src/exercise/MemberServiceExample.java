package exercise;

public class MemberServiceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberService memberSerivce = new MemberService();

		// �α��� ���� ���̽�
		// �׽�Ʈ ������ ����
		Member member = new Member("ȫ�浿", "hong");
		member.password = "12345";

		// �α��� �׽�Ʈ ���̽� ����
		boolean result = memberSerivce.login(member);
		if (result) {
			System.out.println("�α��� �Ǿ����ϴ�.");
			// �α׾ƿ� �׽�Ʈ ���̽� ����
			memberSerivce.logout("hong");
		} else {
			System.out.println("id �Ǵ� password�� �ùٸ��� �ʽ��ϴ�.");
		}
	}

}
