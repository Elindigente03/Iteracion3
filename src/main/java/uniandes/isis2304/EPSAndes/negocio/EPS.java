package uniandes.isis2304.EPSAndes.negocio;

public class EPS implements VOEPS{
	
	private long id;
	private String localizacion;
	private String nombre;
	
	
	/**
	 * @param id
	 * @param localizacion
	 * @param nombre
	 */
	public EPS(long id, String localizacion, String nombre) {
		this.id = id;
		this.localizacion = localizacion;
		this.nombre = nombre;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
