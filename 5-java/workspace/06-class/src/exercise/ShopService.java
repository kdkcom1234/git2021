package exercise;

public class ShopService {

	// static 객체 변수 선언
	private static ShopService ss;

	// 기본 생성자를 private - 객체 생성못하게
	private ShopService() {

	}

	// 객체를 반환하는 메서드
	public static ShopService getInstance() {
		// null일때(초기상태)일때만
		// 객체를 한번 생성함
		// 그 다음부터는 이전에 생성된 객체를 반환
		if (ss == null) {
			ss = new ShopService();
		}

		return ss;
	}

}
