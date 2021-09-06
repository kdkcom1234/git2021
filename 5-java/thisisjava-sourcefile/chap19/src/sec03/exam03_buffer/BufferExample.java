package sec03.exam03_buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferExample {
	public static void main(String[] args) {
		System.out.println("[7바이트 크기로 버퍼 생성]");
		ByteBuffer buffer = ByteBuffer.allocateDirect(7);
		printState(buffer);
		
		buffer.put((byte)10);
		buffer.put((byte)11);
		System.out.println("[2바이트 저장후]");
		printState(buffer);
		
		buffer.put((byte)12);
		buffer.put((byte)13);
		buffer.put((byte)14);
		System.out.println("[3바이트 저장후]");
		printState(buffer);
		
		buffer.flip();
		System.out.println("[flip() 실행후]");
		printState(buffer);
		
		buffer.get(new byte[3]);
		System.out.println("[3바이트 읽은후]");
		printState(buffer);
		
		buffer.mark();
		System.out.println("--------[현재 위치를 마크 해놓음]");
		
		buffer.get(new byte[2]);
		System.out.println("[2바이트 읽은후]");
		printState(buffer);
		
		buffer.reset();
		System.out.println("--------[position을 마크 위치로 옮김]");
		printState(buffer);
		
		buffer.rewind();
		System.out.println("[rewind() 실행후]");
		printState(buffer);
		
		buffer.clear();
		System.out.println("[clear() 실행후]");
		printState(buffer);
	}
	
	public static void printState(Buffer buffer) {
		System.out.print("\tposition:" + buffer.position() + ", ");
		System.out.print("\tlimit:" + buffer.limit() + ", ");
		System.out.println("\tcapacity:" + buffer.capacity());
	}
}
