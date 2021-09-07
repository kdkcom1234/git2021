package exercise;

public class PrinterExample {
	public static void main(String[] args) {
		Printer printer = new Printer();
		printer.println(10);
		printer.println(true);
		printer.println(5.7);
		printer.println("홍길동");

		// 출력할 때 앞에 데코레이터를 추가해서 출력
		// println("메시지입니다.", 1)
		// 1 메시지입니다.
		printer.println("메시지입니다", 1);

		// 출력할 때 앞/뒤에 데코레이터를 추가해서 출력
		// println("알림메시지입니다.", 1, "!!")
		// 1 알림메시지입니다 !!
		printer.println("알림메시지입니다", 1, "!!");
	}
}
