package exercise;

import java.util.Date;

public class Printer {

	void println(int i) {
		// TODO Auto-generated method stub
		System.out.println(i);

	}

	void println(boolean b) {
		// TODO Auto-generated method stub
		System.out.println(b);
	}

	void println(double d) {
		// TODO Auto-generated method stub
		System.out.println(d);
	}

	@SuppressWarnings("deprecation")
	void println(String string) {
		// TODO Auto-generated method stub
		System.out.println(new Date().toLocaleString() + ": " + string);
	}

	void println(String string, int index) {
		// TODO Auto-generated method stub
		System.out.println(index + " " + string);
	}

	// postfix: ���ʿ� �ٴ� ����
	// prefix: ���ʿ� �ٴ� ����
	void println(String string, int index, String postfix) {
		// TODO Auto-generated method stub
		System.out.println(index + " " + string + " " + postfix);
	}

}
