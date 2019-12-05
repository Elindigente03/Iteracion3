package uniandes.isis2304.EPSAndes.negocio;

public class demanda implements VODemanda
{
  private long servicioid;
  
  public demanda ( long id)
  {
	  this.servicioid = id;
  }
  
  public String toStrinng()
  {
     return "el servicio con alta demanda es" + servicioid;
  }
  
}
