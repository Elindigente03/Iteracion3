package uniandes.isis2304.EPSAndes.negocio;

public class Administrador  extends Usuario implements VOAdministrador{ 

	
 	private long id;
 	private String email;
 	private String nombre;
 	private long numeroDocumento;
 	private int rol;
 	private String tipoDocumento;
	
	/**
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numeroDocumento
	 * @param rol
	 * @param tipoDocumento
	 */
	public Administrador(long idP, String emailP, String nombreP, long numeroDocumentoP, int rolP, String tipoDocumentoP)
	{
		this.id =idP;
		this.email = emailP;
		this.nombre = nombreP;
		this.numeroDocumento = numeroDocumentoP;
		this.rol = rolP;
		this.tipoDocumento = tipoDocumentoP;
				
			
	}
	
	public Administrador()
	{
		this.id =0;
		this.email = "";
		this.nombre = "";
		this.numeroDocumento = 0;
		this.rol = 0;
		this.tipoDocumento = "";
	}

	@Override
	public long id() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String email() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String nombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public long numeroDocumento() {
		// TODO Auto-generated method stub
		return numeroDocumento;
	}

	@Override
	public int rol() {
		// TODO Auto-generated method stub
		return rol;
	}

	@Override
	public String tipoDocumento() {
		// TODO Auto-generated method stub
		return tipoDocumento;
	}
	

}
