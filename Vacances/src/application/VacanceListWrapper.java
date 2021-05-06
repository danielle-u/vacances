package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "vacances")
public class VacanceListWrapper 
{
	private List<Vacance> vacances;
	@XmlElement(name = "vacance")
	
	public List <Vacance> getVacances()
	{
		return vacances;
	}
	public void setVacances(List<Vacance> vacances)
	{
		this.vacances=vacances;
	}
	
} 
