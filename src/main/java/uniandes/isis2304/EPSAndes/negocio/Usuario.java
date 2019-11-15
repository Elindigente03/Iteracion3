package uniandes.isis2304.EPSAndes.negocio;

public class Usuario implements VOUsuario{
	
	/**
	 * El identificador ÚNICO del administrador
	 */
	private long id;
	private String email;
	private String nombre;
	private long numeroDocumento;
	private Rol rol;
	private String rol2;
	private String tipoDocumento;
	
	
	public Usuario( long id, String email, String nombre, long numeroDocumento, String rol, String tipoDocumento) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numeroDocumento = numeroDocumento;
		this.rol2 = rol;
		this.tipoDocumento = tipoDocumento;
	}
	
	public Usuario()
	{
		this.id = 0;
		this.email = "";
		this.nombre = "";
		this.numeroDocumento = 0;
		this.rol = new Rol();
		this.tipoDocumento = "";
	}
	
	public long getId()
	{
		return id;
	}
	public String getEmail() 
	{
		return email;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public long getNumeroDocumento() 
	{
		return numeroDocumento;
	}
	public String getRol()
	{
		return rol2;
	}
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public void setNumeroDocumento(int numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}
	public void setRol(Rol rol)
	{
		this.rol = rol;
	}
	public void setTipoDocumento(String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + "]";
	}



}
