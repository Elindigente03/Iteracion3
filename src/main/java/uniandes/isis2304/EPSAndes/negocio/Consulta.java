package uniandes.isis2304.EPSAndes.negocio;

public class Consulta  implements VOConsulta{
	
	private long id;

	/**
	 * @param id
	 */
	public Consulta(long id  )
	{
		this.id= id;
			
	}


	public long getId() 
	{
		return id;
	}

	
}
