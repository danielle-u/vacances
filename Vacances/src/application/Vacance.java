package application;

public class Vacance 
{

	private String prenom;
	private String nom;
	private String destination;
	private Double transport;
	private Double hotel;
	private Double total;
	
	//contructeur vide
	public Vacance()
	{
		this(null,null);
	}
	
	//contructeur avec 2 paramètres
	public Vacance(String prenom, String nom)
	{
		this.prenom=prenom;
		this.nom=nom;
		this.destination="";
		this.transport=0.0;
		this.hotel=0.0;
		this.total=0.0;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
}
	