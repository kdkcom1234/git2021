package exercise;

public class MemberServiceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberService memberSerivce = new MemberService();
		// �α��� ���� ���̽�
		boolean result = memberSerivce.login("hong", "12345");
		// �α��� ���� ���̽�
//		boolean result = memberSerivce.login("hong", "1234");
//		boolean result = memberSerivce.login("hon", "12345");
		if (result) {
			System.out.println("�α��� �Ǿ����ϴ�.");
			memberSerivce.logout("hong");
		} else {
			System.out.println("id �Ǵ� password�� �ùٸ��� �ʽ��ϴ�.");
		}
	}

}
