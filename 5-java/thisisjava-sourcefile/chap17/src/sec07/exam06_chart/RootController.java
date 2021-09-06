package sec07.exam06_chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class RootController implements Initializable {
	@FXML private PieChart pieChart;
	@FXML private BarChart barChart;
	@FXML private AreaChart areaChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pieChart.setData(FXCollections.observableArrayList(
				new PieChart.Data("AWT", 10),		
				new PieChart.Data("Swing", 30),
				new PieChart.Data("SWT", 25),
				new PieChart.Data("JavaFX", 35)
		));
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("남자");       
		series1.setData(FXCollections.observableArrayList(
			new XYChart.Data("2015", 70),
			new XYChart.Data("2016", 40),
			new XYChart.Data("2017", 50),
			new XYChart.Data("2018", 30)
		));  
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("여자");       
		series2.setData(FXCollections.observableArrayList(
			new XYChart.Data("2015", 30),
			new XYChart.Data("2016", 60),
			new XYChart.Data("2017", 50),
			new XYChart.Data("2018", 60)
		));
		barChart.getData().add(series1);
		barChart.getData().add(series2);
		
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("평균온도");       
		series3.setData(FXCollections.observableArrayList(
			new XYChart.Data("2015", 13),
			new XYChart.Data("2016", 6),
			new XYChart.Data("2017", 22),
			new XYChart.Data("2018", 19)
		));
		areaChart.getData().add(series3);
	}
}







