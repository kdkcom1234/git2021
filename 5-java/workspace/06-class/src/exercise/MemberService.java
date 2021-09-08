package exercise;

public class MemberService {

	// login(Member)
	// Member ��ü�� �޼��� �Ķ���ͷ� ����
	// Dependency: �������� ����
	// Dependency<<Usage>>: �����̳� ���ó���� �ٸ� Ŭ������ �ʿ���
	public boolean login(Member member) {
		if (member.getId() == "hong" && member.getPassword() == "12345") {
			return true;
		}
		return false;
	}

	// �����ε�: �޼��� �̸��� �����ϰ� �Ű������� Ÿ��, ����, ������ �޶����
	// login(String, String)
	public boolean login(String id, String password) {
		if (id == "hong" && password == "12345") {
			return true;
		} else {
			return false;
		}
	}

	public void logout(String id) {
		System.out.println("�α׾ƿ� �Ǿ����ϴ�");
	}

}
