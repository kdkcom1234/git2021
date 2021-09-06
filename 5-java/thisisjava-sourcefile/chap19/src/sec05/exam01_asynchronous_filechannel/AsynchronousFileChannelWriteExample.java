package sec05.exam01_asynchronous_filechannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousFileChannelWriteExample {
	public static void main(String[] args) throws Exception {
		//스레드풀 생성
		ExecutorService executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors() 
		);
		
		for(int i=0; i<10; i++) {
			Path path = Paths.get("C:/Temp/file" + i + ".txt");
			Files.createDirectories(path.getParent());
			
			//비동기 파일 채널 생성
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
				path, 
				EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE),
				executorService
			);  
			
			Charset charset = Charset.defaultCharset();
			ByteBuffer byteBuffer = charset.encode("안녕하세요");
			
			//첨부 객체 생성
			class Attachment {
				Path path;
				AsynchronousFileChannel fileChannel;
			}
			Attachment attachment = new Attachment();
			attachment.path = path;
			attachment.fileChannel = fileChannel;
			
			//CompletionHandler 객체 생성			
			CompletionHandler<Integer, Attachment> completionHandler = 
				new CompletionHandler<Integer, Attachment>() {
				@Override
				public void completed(Integer result, Attachment attachment) {
					System.out.println(attachment.path.getFileName() + " : " + result + " bytes written : " + Thread.currentThread().getName());
					try { attachment.fileChannel.close(); } catch (IOException e) {}
				}
				@Override
				public void failed(Throwable exc, Attachment attachment) {
					exc.printStackTrace();
					try { attachment.fileChannel.close(); } catch (IOException e) {}
				}
			};
			
			fileChannel.write(byteBuffer, 0, attachment, completionHandler);
		}
		
		//스레드풀 종료
		executorService.shutdown();
	}
}
