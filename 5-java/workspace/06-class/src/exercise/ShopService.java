package exercise;

public class ShopService {

	// static ��ü ���� ����
	private static ShopService ss;

	// �⺻ �����ڸ� private - ��ü �������ϰ�
	private ShopService() {

	}

	// ��ü�� ��ȯ�ϴ� �޼���
	public static ShopService getInstance() {
		// null�϶�(�ʱ����)�϶���
		// ��ü�� �ѹ� ������
		// �� �������ʹ� ������ ������ ��ü�� ��ȯ
		if (ss == null) {
			ss = new ShopService();
		}

		return ss;
	}

}
