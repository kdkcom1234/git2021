package exercise;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Scanner 위에 마우스 커서를 올리고 ctrl + 1 -> quick fix
		// java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("--입력값을 입력하세요.--");
		System.out.println(">");

		String input = "";
		input = scanner.next(); // 문자열 값을 입력 받을 수 있음

		System.out.println(input);

		int num = 0;

		System.out.println("-- 숫자를 입력하세요.--");
		System.out.println(">");
		num = scanner.nextInt();

		System.out.println(num);
	}
}
