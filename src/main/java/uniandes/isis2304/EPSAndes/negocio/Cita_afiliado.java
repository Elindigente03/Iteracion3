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
	private Date dia;
	private int efactuada;
	
	
public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public int getEfactuada() {
		return efactuada;
	}

	public void setEfactuada(int efactuada) {
		this.efactuada = efactuada;
	}

public Date getFechaInic() {
		return dia;
	}

	public void setFechaInic(Date fechaInic) {
		this.dia = fechaInic;
	}

	

public Cita_afiliado() {
		
	this.id=0;
		this.citaId = 0;
		this.afiliadoId = 0;
		this.dia=null;
		
	}
	
	public Cita_afiliado(long id, long citaId, long afiliadoId,long idServicio,Date dia) {
		this.id=id;
		this.citaId = citaId;
		this.afiliadoId = afiliadoId;
		this.idServicio=idServicio;
		
		this.dia=dia;
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
