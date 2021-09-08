package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample {

	public static void main(String[] args) {
		// Map<ŰŸ��, ��Ÿ��>
		// �Ű������� �־��ִ� Ÿ��: ���׸�, ���ʸ�, generic
		// Ű: Member�� id
		// ��: Member ��ü
		Map<String, Member> members = new HashMap<String, Member>();

		// 1. ��ü �߰�
		// ���ο� ��ü ������ �����ϰ� map������ put �޼���� �߰�
		Member hong = new Member("hong", "password123", "ȫ�浿", 20);
		members.put("hong", hong);

		Member smith = new Member("john", "abcd1234", "John Smith", 30);
		members.put("john", smith);

	}

}
