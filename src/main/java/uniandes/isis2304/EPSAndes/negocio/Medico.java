package uniandes.isis2304.EPSAndes.negocio;

public class Medico  implements VOMedico{
	
	private Long id;
	private String especialidad;
	private String numeroRegistro;
	private Long idUsuario;
	
	
	/**
	 * @param id
	 * @param especialidad
	 * @param numeroRegistro
	 */
	public Medico(Long id, String especialidad, String numeroRegistro,long idUsuario) 
	{
		this.id = id;
		this.especialidad = especialidad;
		this.numeroRegistro = numeroRegistro;
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	/**
	 * @return the numeroRegistro
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}
	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	public long getId()
	{
		return this.getId();
	}
	
	

}
