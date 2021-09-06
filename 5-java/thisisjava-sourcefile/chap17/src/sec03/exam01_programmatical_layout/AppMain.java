package sec03.exam01_programmatical_layout;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.collections.ObservableList;

public class AppMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();							//HBox �����̳� ����
		hbox.setPadding(new Insets(10,10,10,10));        //���� ���� ����
		hbox.setSpacing(10);  									//��Ʈ�Ѱ��� ���� ���� ����
		
		TextField textField = new TextField(); 				//TextField ��Ʈ�� ����
		textField.setPrefWidth(200);							//TextField�� �� ����
		
		Button button = new Button();						//Button ��Ʈ�� ����
		button.setText("Ȯ��");									//Button ���� ����
		
		ObservableList list = hbox.getChildren();			//HBox�� ObservableList ���
		list.add(textField);										//TextField ��Ʈ�� ��ġ
		list.add(button);											//Button�� ��Ʈ�� ��ġ
		
		Scene scene = new Scene(hbox);					//ȭ���� ��Ʈ �����̳ʷ� HBox ����
		
		primaryStage.setTitle("AppMain");					//������ â ���� ����
		primaryStage.setScene(scene);						//������ â�� ȭ�� ����
		primaryStage.show();									//������ â �����ֱ�
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
