package exercise;

public class MemberService {

	// Member ��ü�� �޼��� �Ķ���ͷ� ����
	// Dependency: �������� ����
	// Dependency<<Usage>>: �����̳� ���ó���� �ٸ� Ŭ������ �ʿ���
	boolean login(Member member) {
		if (member.id == "hong" && member.password == "12345") {
			return true;
		}
		return false;
	}

//	boolean login(String id, String password) {
//		if (id == "hong" && password == "12345") {
//			return true;
//		} else {
//			return false;
//		}
//	}

	void logout(String id) {
		System.out.println("�α׾ƿ� �Ǿ����ϴ�");
	}

}
