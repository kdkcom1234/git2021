package sec09.exam01_udp;

import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class UdpSendExample {
	public static void main(String[] args) throws Exception {
		DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
		
		System.out.println("[발신 시작]");
		
		for(int i=1; i<3; i++) {
			String data = "메시지" + i;
			Charset charset = Charset.forName("UTF-8");
			ByteBuffer byteBuffer = charset.encode(data);
			
			int byteCount = datagramChannel.send(
				byteBuffer,  
				new InetSocketAddress("localhost", 5001)
			);
			System.out.println("[보낸 바이트 수] " + byteCount + " bytes");
		}
		
		System.out.println("[발신 종료]");
		
		datagramChannel.close();
	}
}
