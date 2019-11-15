package uniandes.isis2304.EPSAndes.negocio;

public class Urgencia implements VOUrgencia{

	private long id;
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 */
	public Urgencia(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}

}
