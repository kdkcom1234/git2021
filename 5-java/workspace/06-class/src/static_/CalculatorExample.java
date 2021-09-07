package static_;

public class CalculatorExample {
	public static void main(String[] args) {
		// �ν��Ͻ�(��ü) �������ϰ� �ٷ� ��밡��
		// �ַ� ���־��� ���̳� ��ɵ��� static���� �����Ͽ� ��밡��
		System.out.println(Calculator.PI);
		System.out.println(Calculator.plus(10, 5));

		System.out.println(Calculator.getAreaCircle(5));

		// static ���� ���� ���� ������
		// ������ ���ϰ� �����ϰ� ����
		// final�� �̿��Ͽ� ������ ���� �����
//		Calculator.pi = 3.14;

//		A���(��Ű��Ʈ) - Ŭ���� ����: UML, Class Diagram
//		B���(������ⰳ��) - Ŭ���� ����: Calculator
//		  -> �ڵ忡 ���� ��ũ������(OOP, Design Pattern) ���ص� ���� ���
//		  -> ������ ������ �̰� ��ġ�� �ټ��� �ڵ�鿡 ������ ���� �� ����
//		C���(���񽺸�ⰳ��) - Ŭ���� ���: CalculatorExample
//		  -> ����Ͻ��� ���� ���ص��� ���� ���
	}
}
