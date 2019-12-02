package uniandes.isis2304.EPSAndes.negocio;

import java.util.Date;

public class Cita_afiliado implements VOCita_afiliado{
	
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	private long citaId;
	private long afiliadoId;
	private long idServicio;
	private Date fechaInic;
	private Date fechaFin;
	
	
public Date getFechaInic() {
		return fechaInic;
	}

	public void setFechaInic(Date fechaInic) {
		this.fechaInic = fechaInic;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

public Cita_afiliado() {
		
	this.id=0;
		this.citaId = 0;
		this.afiliadoId = 0;
		this.fechaInic=null;
		this.fechaFin=null;
	}
	
	public Cita_afiliado(long id, long citaId, long afiliadoId,long idServicio,Date fechaInic,Date fechaFin) {
		this.id=id;
		this.citaId = citaId;
		this.afiliadoId = afiliadoId;
		this.idServicio=idServicio;
		this.fechaFin=fechaFin;
		this.fechaInic=fechaInic;
	}
	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public long getCitaId() {
		return citaId;
	}
	public void setCitaId(long citaId) {
		this.citaId = citaId;
	}
	public long getAfiliadoId() {
		return afiliadoId;
	}
	public void setAfiliadoId(long afiliadoId) {
		this.afiliadoId = afiliadoId;
	}
	

}
