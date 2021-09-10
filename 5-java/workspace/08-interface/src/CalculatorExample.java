
public class CalculatorExample {
	public static void main(String[] args) {
		// 인터페이스는 객체를 생성할 수 없음
//		Calculator c = new Calculator();

		// 목업 클래스로 일단 사용
//		Calculator c = new CalculatorMock();

		// 클래스 구현이 완료됨 -> 구현된걸로 갈아낌
//		Calculator c = new CalculatorImplV1();

		// 구현 클래스를 버전업함 -> 새로운 버전으로 갈아낌
		Calculator c = new CalculatorImplV2();

		// 가져다가 쓰는 코드는 고칠필요가 없었음
		System.out.println(c.plus(5, 10));
		System.out.println(c.minus(5, 10));
		System.out.println(c.areaCircle(5));
	}
}
