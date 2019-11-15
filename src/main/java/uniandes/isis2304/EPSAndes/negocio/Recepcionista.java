package uniandes.isis2304.EPSAndes.negocio;

public class Recepcionista  implements VORecepcionista{

	
	private long id;
	private long idUsuario;
	private long idIps;
	/**
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numeroDocumento
	 * @param rol
	 * @param tipoDocumento
	 */
	public Recepcionista(long id, long idUsuario,long idIps) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idIps = idIps;
	}
	public long getId()
	{
		return this.getId();
	}
	public long getIdUsuario()
	{
		return this.getIdUsuario();
	}
	public long getIdIps()
	{
		return this.getIdIps();
	}
}