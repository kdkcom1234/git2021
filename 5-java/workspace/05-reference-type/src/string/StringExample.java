package string;

public class StringExample {
	public static void main(String[] args) {
		String name1 = "����"; // �ڹٿ����� ���ڿ� �ֵ���ǥ��
		String name2 = "����";
		// String ��ġ�񱳿��� �� ����� ���� ����
		System.out.println(name1 == name2);
		// !! String�� ��� ��ġ �񱳿� equal �Լ��� ���
		System.out.println(name1.equals(name2));

		String name3 = new String("����");
		String name4 = new String("����");
		// String ��ġ�񱳿��� �� ����� ���� ����
		System.out.println(name3 == name4);
		// !! String�� ��� ��ġ �񱳿� equal �Լ��� ���
		System.out.println(name3.equals(name4));

		// �������� ������.
//		if(name3 == "����") {
//			
//		}

		// equal �Լ��� ���
//		if(name3.equals("����")) {
//			
//		}
	}
}
