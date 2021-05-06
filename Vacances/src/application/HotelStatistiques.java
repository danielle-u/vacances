package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class HotelStatistiques 
{
	@FXML
	private BarChart<String, Integer> barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList<String> intervalHotel=FXCollections.observableArrayList();
	
	@FXML
	private void initialize()
	{
		intervalHotel.add("0-10");
		intervalHotel.add("10-20");
		intervalHotel.add("20-30");
		intervalHotel.add("30-40");
		intervalHotel.add("40-50");
		intervalHotel.add("50-60");
		xAxis.setCategories(intervalHotel);
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
			series.getData().add(new XYChart.Data<>(intervalHotel.get(i), prixCounter[i]));
		}
		barChart.getData().add(series);
	}
}
