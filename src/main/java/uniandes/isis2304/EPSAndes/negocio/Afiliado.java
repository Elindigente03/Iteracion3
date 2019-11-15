package uniandes.isis2304.EPSAndes.negocio;

import java.util.Date;

public class Afiliado  implements VOAfiliado{

	private long id;
	private String estadoSalud;
	private Date fechaNacimiento;
	private String recetaActual;
	private long idOrden;
	private long idUsuario;
	private long idEps;
	private long idCita;
	
	
	/**
	 * 
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numeroDocumento
	 * @param rol
	 * @param tipoDocumento
	 * @param estadoSalud
	 * @param fechaNacimiento
	 * @param recetaActual
	 */
	public Afiliado(long id, String estadoSalud, Date fechaNacimiento, String recetaActual,long idOrden , long idUsuario,long idEps ,long idCita) {
		this.id = id;
		this.estadoSalud = estadoSalud;
		this.fechaNacimiento = fechaNacimiento;
		this.recetaActual = recetaActual;
		this.idOrden = idOrden;
		this.idUsuario = idUsuario;
		this.idEps = idEps;
		this.idCita = idCita;
	}
	

	public String getEstadoSalud() {
		return estadoSalud;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getRecetaActual() {
		return recetaActual;
	}
	public void setEstadoSalud(String estadoSalud) {
		this.estadoSalud = estadoSalud;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setRecetaActual(String recetaActual) {
		this.recetaActual = recetaActual;
	}
	public long getId() 
	{
		return id;
	}
	public long getIdOrden() 
	{
		return id;
	}
	public long getIdUsuario() 
	{
		return id;
	}
	public long getIdEps() 
	{
		return id;
	}
	public long getIdCita() 
	{
		return id;
	}
	
	
}
