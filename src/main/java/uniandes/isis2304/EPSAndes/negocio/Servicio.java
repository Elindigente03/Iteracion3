package uniandes.isis2304.EPSAndes.negocio;

public class Servicio implements VOServicio{
	
	private long id;
	private long capacidad;
	private String nombre;
	private long idIPS;
	
	
	
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 */
	public Servicio(long id, long capacidad, String nombre , long idIPS) {
		this.id = id;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.idIPS= idIPS;
		
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	public long getIdIPS() {
		return idIPS;
	}
	/**
	 * @return the capacidad
	 */
	public long getCapacidad() {
		return capacidad;
	}
	/**
	 * @return the horariosAtencion
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
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * @param horariosAtencion the horariosAtencion to set
	 */
	public void setHorariosAtencion(String nombre) {
		this.nombre = nombre;
	}

	

}
