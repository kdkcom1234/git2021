package verify.exam05;

public class AnonymousExample {
	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		//익명 객체 필드 사용
		anony.field.run();
		//익명 객체 로컬변수 사용
		anony.method1();
		//익명 객체 매개값 사용
		anony.method2(
			new Vehicle() {
				@Override
				public void run() {
					System.out.println("트럭이 달립니다.");
				}
			}
		);
	}
}
