package singleton;

public class CalculatorExample {
	public static void main(String[] args) {
//		Calculator calc = new Calculator();

		// �̹� �����Ǿ��ִ� Calculator ��ü�� �����ͼ� ���
		Calculator calc = Calculator.getInstance();
		// ��ü�� ����ϸ�
		// ��) singleton.Calculator@7de26db8
		// Ŭ����Ÿ��@��ü���ؽ��ڵ�
		System.out.println(calc);
		calc.getAreaCircle(5);
		calc.plus(10, 5);
	}
}
