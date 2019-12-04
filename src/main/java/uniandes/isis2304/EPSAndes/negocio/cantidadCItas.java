package uniandes.isis2304.EPSAndes.negocio;

public class cantidadCItas implements VOCantidadCitas
{
   private long idServicio;
   private long cantidad; 
   
   public cantidadCItas(long id , long cant)
   {
	 idServicio = id;
	 cantidad = cant;
   }
   
   public String toString()
   {
      return "id :" + idServicio + " cantidad " + cantidad;
   }
}
