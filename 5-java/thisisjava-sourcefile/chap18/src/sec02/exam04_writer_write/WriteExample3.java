package sec02.exam04_writer_write;

import java.io.FileWriter;
import java.io.Writer;

public class WriteExample3 {

	public static void main(String[] args) throws Exception {
		Writer writer = new FileWriter("C:/Temp/test.txt");
		char[] data = "ȫ�浿".toCharArray();
		writer.write(data, 1, 2);

		writer.flush();
		writer.close();
	}

}
