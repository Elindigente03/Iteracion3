package uniandes.isis2304.EPSAndes.negocio;

public class Gerente  implements VOGerente{

	
	private long id;
	private long idUsuario;
	/**
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numeroDocumento
	 * @param rol
	 * @param tipoDocumento
	 */
	public Gerente(long id ,long idUsuario) {
		this.id = id;
		this.idUsuario = idUsuario;
	}
	
	public long getId()
	{
		return this.getId();
	}

	
	
	
}
