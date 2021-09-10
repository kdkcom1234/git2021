
public class CalculatorExample {
	public static void main(String[] args) {
		// �������̽��� ��ü�� ������ �� ����
//		Calculator c = new Calculator();

		// ��� Ŭ������ �ϴ� ���
//		Calculator c = new CalculatorMock();

		// Ŭ���� ������ �Ϸ�� -> �����Ȱɷ� ���Ƴ�
//		Calculator c = new CalculatorImplV1();

		// ���� Ŭ������ �������� -> ���ο� �������� ���Ƴ�
		Calculator c = new CalculatorImplV2();

		// �����ٰ� ���� �ڵ�� ��ĥ�ʿ䰡 ������
		System.out.println(c.plus(5, 10));
		System.out.println(c.minus(5, 10));
		System.out.println(c.areaCircle(5));
	}
}
