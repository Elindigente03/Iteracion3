package uniandes.isis2304.EPSAndes.negocio;

public class ServiciosN  implements VOServiciosN
{
  private String nombreIPS;
  private int servicios;
  
  public ServiciosN(String nombre , int serv )
  {
	  nombreIPS = nombre;
	  servicios = serv;
  }
    
  public String toString()
  {
	  return "IPS" + nombreIPS + " cantidad de servicios: " + servicios;
  }
}
