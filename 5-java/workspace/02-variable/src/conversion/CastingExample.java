package conversion;

public class CastingExample {
	public static void main(String[] args) {
		int intValue = 44032;
		// (��ȯŸ��) ������
		// �� �ս��� ���� ����,
		// 0 ~ 2^16 - 1
		char charValue = (char) intValue;
		System.out.println(charValue);
		
		double doubleValue = 3.14;
		// �� �ս��� �߻���
		intValue = (int) doubleValue;
		System.out.println(intValue);
	}
}
