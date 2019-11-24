package uniandes.isis2304.EPSAndes.negocio;

public class Cita_afiliado implements VOCita_afiliado{
	
	private long citaId;
	private long afiliadoId;
	
	
public Cita_afiliado() {
		
		this.citaId = 0;
		this.afiliadoId = 0;
	}
	
	public Cita_afiliado(long citaId, long afiliadoId) {
		
		this.citaId = citaId;
		this.afiliadoId = afiliadoId;
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
