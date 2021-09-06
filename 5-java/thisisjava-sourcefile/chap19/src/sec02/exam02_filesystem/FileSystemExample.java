package sec02.exam02_filesystem;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemExample {
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystems.getDefault();
		for(FileStore store : fileSystem.getFileStores()) {
			System.out.println("드라이버명: " + store.name());
			System.out.println("파일시스템: " + store.type());
			System.out.println("전체 공간: \t\t" + store.getTotalSpace() + " 바이트");
			System.out.println("사용 중인 공간: \t" + (store.getTotalSpace() - store.getUnallocatedSpace()) + " 바이트");
			System.out.println("사용 가능한 공간: \t" + store.getUsableSpace() + " 바이트");
			System.out.println();
		}
		
		System.out.println("파일 구분자: " + fileSystem.getSeparator());
		System.out.println();
		
		for(Path path : fileSystem.getRootDirectories()) {
			System.out.println(path.toString());
		}
	}
}
