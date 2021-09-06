package sec07.exam01_nonblocking_tcpchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientExample extends Application {
	Selector selector;
	SocketChannel socketChannel;
	
	void startClient() {
		try {
			selector = Selector.open();
		} catch(Exception e) {
			if(socketChannel.isOpen()) { stopClient(); }
			return;
		}
		
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(new InetSocketAddress("localhost", 5001));
		} catch(Exception e) {
			Platform.runLater(()->displayText("[서버 통신 안됨]"));
			if(socketChannel.isOpen()) { stopClient(); }
			return;
		}	
			
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						int keyCount = selector.select();
						if(keyCount == 0) { continue; }
						Set<SelectionKey> selectedKeys = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectedKeys.iterator();
						while (iterator.hasNext()) {
						    SelectionKey selectionKey = iterator.next();
		                    if (selectionKey.isConnectable()) {
		                		connect(selectionKey);
		                    } else if(selectionKey.isReadable()) {
		                    	receive(selectionKey);
		                    } else if(selectionKey.isWritable()) {
		                    	send(selectionKey);
		                    }
		                    iterator.remove();
						}
					} catch (Exception e) {
						Platform.runLater(()->displayText("[서버 통신 안됨]"));
						if(socketChannel.isOpen()) { stopClient(); }
						break;
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	
	void stopClient() {
		try {
			Platform.runLater(()->{
				displayText("[연결 끊음]");
		        btnConn.setText("start");
		        btnSend.setDisable(true);
			});    
			if(socketChannel!=null && socketChannel.isOpen()) {
				socketChannel.close();
			}
		} catch (IOException e) {}
	}
	
	void connect(SelectionKey selectionKey) {
		try {
			socketChannel.finishConnect();
			Platform.runLater(()->{
				try {
					displayText("[연결 완료: "  + socketChannel.getRemoteAddress() + "]");
					btnConn.setText("stop");
			        btnSend.setDisable(false);
				} catch (Exception e) {}
			});    
			selectionKey.interestOps(SelectionKey.OP_READ);
		} catch(Exception e) {
			Platform.runLater(()->displayText("[서버 통신 안됨]"));
			if(socketChannel.isOpen()) { stopClient(); }
		}
	}
	
	void receive(SelectionKey selectionKey) {
		try {
			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
			
			//서버가 비저상 종료를 했을 경우 자동 IOException 발생
			int byteCount = socketChannel.read(byteBuffer); 
			
			//서버가 정상적으로 SocketChannel의 close() 메소드를 호출할 경우
			if(byteCount == -1) { 
				throw new IOException();
			}
			
			byteBuffer.flip();
			Charset charset = Charset.forName("UTF-8");
			String data = charset.decode(byteBuffer).toString();
			
			Platform.runLater(()->displayText("[받기 완료] "  + data));
		} catch(Exception e) {
			Platform.runLater(()->displayText("[서버 통신 안됨]"));
			stopClient();
		}
	}
	
	void send(SelectionKey selectionKey) {
		try {		
			ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
			socketChannel.write(byteBuffer);
			Platform.runLater(()->displayText("[보내기 완료]"));
			selectionKey.interestOps(SelectionKey.OP_READ);
		} catch(Exception e) {
			Platform.runLater(()->displayText("[서버 통신 안됨]"));
			stopClient();
		}
	}
	
	void send(String data) {
		Charset charset = Charset.forName("UTF-8");
		ByteBuffer byteBuffer = charset.encode(data);
		SelectionKey key = socketChannel.keyFor(selector);
		key.attach(byteBuffer);
		key.interestOps(SelectionKey.OP_WRITE);
		selector.wakeup();
	}
	
	///////////////////////////////////////////
	TextArea txtDisplay;
	TextField txtInput;
	Button btnConn, btnSend;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(500, 300);
		
		txtDisplay = new TextArea();
		txtDisplay.setEditable(false);
		BorderPane.setMargin(txtDisplay, new Insets(0,0,2,0));
		root.setCenter(txtDisplay);
		
		BorderPane bottom = new BorderPane();
			txtInput = new TextField();
			txtInput.setPrefSize(60, 30);
			BorderPane.setMargin(txtInput, new Insets(0,1,1,1));
			
			btnConn = new Button("start");
			btnConn.setPrefSize(60, 30);
			btnConn.setOnAction(e->{
				if(btnConn.getText().equals("start")) {
					startClient();
				} else if(btnConn.getText().equals("stop")){
					stopClient();
				}
			});
			
			btnSend = new Button("send"); 
			btnSend.setPrefSize(60, 30);
			btnSend.setDisable(true);
			btnSend.setOnAction(e->send(txtInput.getText()));
			
			bottom.setCenter(txtInput);
			bottom.setLeft(btnConn);
			bottom.setRight(btnSend);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.setOnCloseRequest(event->stopClient());
		primaryStage.show();
	}
	
	void displayText(String text) {
		txtDisplay.appendText(text + "\n");
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
