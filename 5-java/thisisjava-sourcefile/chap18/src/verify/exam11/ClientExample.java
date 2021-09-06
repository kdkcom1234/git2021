package verify.exam11;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;


public class ClientExample {
	public static void main(String[] args) throws Exception {		
		Socket socket = new Socket("localhost", 5001);
		OutputStream os = socket.getOutputStream();	
		
		String filePath = "C:/JavaProgramming/source/chap18/src/sec04/exam03_fileoutputstream/house.jpg";
		File file = new File(filePath);
		
		String fileName = file.getName();
		byte[] fileNameBytes = fileName.getBytes("UTF-8");
		fileNameBytes = Arrays.copyOf(fileNameBytes, 100);
		os.write(fileNameBytes);
		
		System.out.println("[파일 보내기 시작] " + fileName);
		FileInputStream fis = new FileInputStream(file);	
		byte[] bytes = new byte[1000];
		int readByteCount = -1;
		while((readByteCount=fis.read(bytes))!=-1) {
			os.write(bytes, 0, readByteCount);
		}
		
		os.flush();
		System.out.println("[파일 보내기 완료]");		
		
		fis.close();
		os.close();
		socket.close();
	}
}
