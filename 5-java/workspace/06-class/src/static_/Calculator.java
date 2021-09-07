package static_;

// static ����� ����
// static �ʵ�, �޼���

// ��ü�� ���� Ŭ������ �ƴ�
// �ʵ尪, �޼��带 ������� �������� �����ϴ� Ŭ����
public class Calculator {
	// final Ű���带 �ָ� �����Ұ���
	// js: const
	// �� static�� �ִ°� �ƴ�, �ַ� static ������ �����
	// static ������ ����Ѵٴ� ���� �������� �����
	// �������ϰ� �ϴ°� �Ϲ���
	final static double PI = 3.141592;

	static int plus(int a, int b) {
		return a + b;
	}

	static int minus(int a, int b) {
		return a - b;
	}

	static double getAreaCircle(int r) {
		return r * r * PI;
	}
}
