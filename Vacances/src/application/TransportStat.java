package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class TransportStat 
{
	@FXML
	private BarChart<String, Integer> barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList<String> intervalTransport=FXCollections.observableArrayList();
	
	@FXML
	private void initialize()
	{
		intervalTransport.add("0-10");
		intervalTransport.add("10-20");
		intervalTransport.add("20-30");
		intervalTransport.add("30-40");
		intervalTransport.add("40-50");
		intervalTransport.add("50-60");
		xAxis.setCategories(intervalTransport);
	}
	public void SetStats(List<Vacance> vacances)
	{
		//compter les étudiants appartenant à la meme tranche de prix de transportation
		
		//tableau pour les nombres des tranches de prix de transportation
		int[] prixCounter = new int[6];
		
		for(Vacance e:vacances)
		{
			double transport= e.getTransport();
			
			if(transport<=10)
				prixCounter[0]++;
			else
				if(transport<=20)
					prixCounter[1]++;
				else
					if(transport<=30)
						prixCounter[2]++;
					else
						if(transport<=40)
							prixCounter[3]++;
						else
							if(transport<=50)
						prixCounter[4]++;
							else
								prixCounter[5]++;
		}
		
		XYChart.Series<String, Integer> series=new XYChart.Series<>();
		//légende pour graphique
		series.setName("prix de transportation");
		
		//création du graphique
		for(int i=0; i<prixCounter.length;i++)
		{
			series.getData().add(new XYChart.Data<>(intervalTransport.get(i), prixCounter[i]));
		}
		barChart.getData().add(series);
	}
}
