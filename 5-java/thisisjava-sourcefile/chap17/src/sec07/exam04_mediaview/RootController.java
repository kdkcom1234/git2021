package sec07.exam04_mediaview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RootController implements Initializable {
	@FXML private MediaView mediaView;
	@FXML private ImageView imageView;
	@FXML private Button btnPlay;
	@FXML private Button btnPause;
	@FXML private Button btnStop;
	
	private boolean endOfMedia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�̵�� ��ü ����
		Media media = new Media(getClass().getResource("media/video.m4v").toString());
		//Media media = new Media(getClass().getResource("media/audio.wav").toString());
		
		//�̵�� �÷��̾� ���� �� �̵�� �信 ����
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
	
		//�ش� ���°� �Ǹ� ������ Runnable ����
		mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(false); btnPause.setDisable(true); btnStop.setDisable(true);
				if(mediaPlayer.isAutoPlay()) {mediaView.setVisible(false);}
			}
		});
		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true); btnPause.setDisable(false); btnStop.setDisable(false);
		});
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false); btnPause.setDisable(true); btnStop.setDisable(false);
		});
		mediaPlayer.setOnEndOfMedia(()->{
			endOfMedia = true;
			btnPlay.setDisable(false); btnPause.setDisable(true); btnStop.setDisable(true);
		});
		mediaPlayer.setOnStopped(()->{
			mediaPlayer.seek(mediaPlayer.getStartTime());
			btnPlay.setDisable(false); btnPause.setDisable(true); btnStop.setDisable(true);
		});
		
		//��ư ActionEvent ó��
		btnPlay.setOnAction(event->{
			if(endOfMedia) { 
				mediaPlayer.stop(); 
				mediaPlayer.seek(mediaPlayer.getStartTime());
			}
			mediaPlayer.play();
			endOfMedia = false;
		});
		btnPause.setOnAction(event->mediaPlayer.pause());
		btnStop.setOnAction(event->mediaPlayer.stop());
	}
}
