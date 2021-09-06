package sec06.exam01_serversocketchannel_socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerExample {
	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(true);		
			serverSocketChannel.bind(new InetSocketAddress(5001));
			while(true) {
				System.out.println( "[연결 기다림]");
				SocketChannel socketChannel = serverSocketChannel.accept();
				InetSocketAddress isa = (InetSocketAddress) socketChannel.getRemoteAddress();
				System.out.println("[연결 수락함] " + isa.getHostName());
			}
		} catch(Exception e) {}
		
		if(serverSocketChannel.isOpen()) {
			try {
				serverSocketChannel.close();
			} catch (IOException e1) {}
		}
	}
}
