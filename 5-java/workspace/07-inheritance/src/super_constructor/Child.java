package super_constructor;

public class Child extends Parent {
	private String name;

	// 호출 - 1
	public Child() {
		// super(); // 암시적(자동)으로 호출이 됨
		this("홍길동"); // -> Child("홍길동") 호출
		// 출력 - 4
		System.out.println("Child() call");
	}

	// 호출 - 4
	public Child(String name) {
		this.name = name;
		// 출력 - 3
		System.out.println("Child(String name) call");
	}
}
