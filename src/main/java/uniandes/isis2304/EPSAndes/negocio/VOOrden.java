package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Date;

public interface VOOrden {
	
	public long getId();
	public Date getFecha();
	public long getIdServicio();
	public String getEstado();

}
