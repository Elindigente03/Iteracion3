package uniandes.isis2304.EPSAndes.negocio;

public class Hospitalizacion  implements VOHospitalizacion{
	
	private long id;
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 */
	public Hospitalizacion(long id) {
		this.id =id;
	}

	
	public long getId()
	{
		return id;
	}

	
	
}
