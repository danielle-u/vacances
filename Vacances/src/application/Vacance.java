package application;

public class Vacance 
{

	private String prenom;
	private String nom;
	private String destination;
	private Double transport;
	private Double hotel;
	private String total;
	
	//contructeur vide
	public Vacance()
	{
		this(null,null, null);
	}
	
	//contructeur avec 2 paramètres
	public Vacance(String prenom, String nom, String total)
	{
		this.prenom=prenom;
		this.nom=nom;
		this.destination="";
		this.transport=0.0;
		this.hotel=0.0;
		this.total=total;
	}
	
	//generate getters and setters
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getTransport() {
		return transport;
	}

	public void setTransport(Double transport) {
		this.transport = transport;
	}

	public Double getHotel() {
		return hotel;
	}

	public void setHotel(Double hotel) {
		this.hotel = hotel;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
	