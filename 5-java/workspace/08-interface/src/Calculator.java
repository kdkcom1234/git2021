
// 인터페이스는 클래스의 특수형태
// 구현체는 없고 껍데기만 있음, 구조만 있음
// 1. 설계자가 정의함
public interface Calculator {

	// 추상메서드 선언
	// 메서드 본체(정의부분)이 없음

	// 계산기 기능에는 아래의 메서드3개가 있다고 규격을 정함
	int plus(int a, int b);

	int minus(int a, int b);

	double areaCircle(int r);
}
