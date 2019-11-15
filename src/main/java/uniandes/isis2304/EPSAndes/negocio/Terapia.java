package uniandes.isis2304.EPSAndes.negocio;

public class Terapia  implements VOTerapia{
	
	private long id;
	private int numeroSesiones;
	private String tipo;
	
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 * @param numeroSesiones
	 * @param tipo
	 */
	public Terapia(long id,  int numeroSesiones, String tipo) {
		this.id = id;
		this.numeroSesiones = numeroSesiones;
		this.tipo = tipo;
	}

	/**
	 * @return the numeroSesiones
	 */
	public int getNumeroSesiones() {
		return numeroSesiones;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param numeroSesiones the numeroSesiones to set
	 */
	public void setNumeroSesiones(int numeroSesiones) {
		this.numeroSesiones = numeroSesiones;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getId()
	{
		return this.id;
	}
	
	

}
