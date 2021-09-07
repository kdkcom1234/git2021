package static_;

// static 멤버만 있음
// static 필드, 메서드

// 객체로 찍어내는 클래스가 아님
// 필드값, 메서드를 기능적인 관점에서 실행하는 클래스
public class Calculator {
	// final 키워드를 주면 수정불가함
	// js: const
	// 꼭 static에 넣는건 아님, 주로 static 변수에 사용함
	// static 변수를 사용한다는 것은 공용적인 사용임
	// 수정못하게 하는게 일반적
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
