
// 2. 인터페이스 규격에 맞게 목업 클래스를 만듦
// 목업클래스는 실제 작동은 아니고 에러 안나게 가짜값만 처리되게 함

// implement: 구현하다
// 구현클래스명 implements 인터페이스명
public class CalculatorMock implements Calculator {

	@Override
	public int plus(int a, int b) {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int minus(int a, int b) {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public double areaCircle(int r) {
		// TODO Auto-generated method stub
		return 5 * 5 * 3.14;
	}

}
