
// 3. 실제 구현자가 기능을 구현함

public class CalculatorImplV1 implements Calculator {

	@Override
	public int plus(int a, int b) {
		return a + b;
	}

	@Override
	public int minus(int a, int b) {
		return a - b;
	}

	@Override
	public double areaCircle(int r) {
		// TODO Auto-generated method stub
		return 3.14 * r * r;
	}
}
