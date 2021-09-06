package sec03.exam02_byteorder;

import java.nio.ByteOrder;

public class ComputerByteOrderExample {
	public static void main(String[] args) {
		System.out.println("운영체제 종류 : "+System.getProperty("os.name"));
		System.out.println("네이티브의 바이트 해석 순서 :"+ByteOrder.nativeOrder());
	}
}
