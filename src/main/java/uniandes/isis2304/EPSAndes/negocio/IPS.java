package uniandes.isis2304.EPSAndes.negocio;

public class IPS implements VOIPS{

	private long id;
	private long idEPS;
	private String localizacion;
	private String nombre;
	
	
	/**
	 * @param localizacion
	 * @param nombre
	 */
	public IPS(long id , long idEps ,String localizacion, String nombre) {
		this.id = id;
		this.idEPS= idEps;
		this.localizacion = localizacion;
		this.nombre = nombre;
	}
	public long getID() {
		return id;
	}
	public long getIdEPS() {
		return idEPS;
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
