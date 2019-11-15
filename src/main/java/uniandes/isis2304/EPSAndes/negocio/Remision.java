package uniandes.isis2304.EPSAndes.negocio;

public class Remision  implements VORemision{
	
	private long id;
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 */
	public Remision(long id) {
		this.id = id;
	}
	public long getId()
	{
		return id;
	}

}
