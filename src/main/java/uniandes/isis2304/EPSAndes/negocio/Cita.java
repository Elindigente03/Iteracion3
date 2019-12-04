package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Date;

public class Cita implements VOCita{
	
	private long id;
	private long cumplida;
	private Date fecha;
	private long idAfiliado;
	
	
	
	
	/**
	 * @param id
	 * @param cumplida
	 * @param fecha
	 */
	public Cita(long id, long cumplida, Date fecha, long idAfiliado) {
		this.id = id;
		this.cumplida = cumplida;
		this.fecha = fecha;
		this.idAfiliado  = idAfiliado;
	}
	public long getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(long idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the cumplida
	 */
	public long getCumplida() {
		
		return cumplida;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param cumplida the cumplida to set
	 */
	public void setCumplida(long cumplida) {
		this.cumplida = cumplida;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String toString()
	{
		return "id:" + id + "afiliado:" + idAfiliado + "estado:" + cumplida + "fecha:" + fecha + "." ;
		
	}

}
