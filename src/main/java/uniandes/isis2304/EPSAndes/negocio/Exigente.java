package uniandes.isis2304.EPSAndes.negocio;

public class Exigente implements VOExigente
{
	private String nombre;
	private long id;
	private String nombreServ;
	private long idservicio;
	private long cantidad;
	
	public Exigente(String n ,long i ,String nS ,long iS , long c )
	{
		this.nombre = n ;
		this.id = i;
		this.nombreServ = nS;
		this.idservicio =iS;
		this.cantidad = c;
		
	}
	
	public String toString()
	{
		return "el usuario: " + nombre + "con id: "+ id +"es exigente con el servicio: " + nombreServ + " con id: "  + idservicio + "tomandolo " + cantidad  + "veces" ;                      
	}
	
	  
}
