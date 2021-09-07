package static_;

public class CalculatorExample {
	public static void main(String[] args) {
		// 인스턴스(객체) 생성안하고 바로 사용가능
		// 주로 자주쓰는 값이나 기능들을 static으로 정의하여 사용가능
		System.out.println(Calculator.PI);
		System.out.println(Calculator.plus(10, 5));

		System.out.println(Calculator.getAreaCircle(5));

		// static 변수 값은 수정 가능함
		// 수정을 못하게 지정하고 싶음
		// final을 이용하여 고정된 값만 사용함
//		Calculator.pi = 3.14;

//		A사람(아키텍트) - 클래스 설계: UML, Class Diagram
//		B사람(공동모듈개발) - 클래스 구현: Calculator
//		  -> 코드에 대한 테크니컬한(OOP, Design Pattern) 이해도 높은 사람
//		  -> 의존성 때문에 이걸 고치면 다수의 코드들에 문제가 생길 수 있음
//		C사람(서비스모듈개발) - 클래스 사용: CalculatorExample
//		  -> 비즈니스에 대한 이해도가 높은 사람
	}
}
