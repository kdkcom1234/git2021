package sec02.exam01_application_start;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();  //������ �����ֱ�
	}
	
	public static void main(String[] args) {
		launch(args); //AppMain ��ü ���� �� ���� ������ ����
	}
}

