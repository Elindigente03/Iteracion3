package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Date;

public class Orden implements VOOrden{

	
	private long id;
	private Date fecha;
	private long idServicio;
	private String estado;
	private String idCampaña;
	
	public Orden(long id ,Date fecha ,long idServicio ,String estado, String idCampaña)
	{
		this.id = id ;
		this.fecha = fecha; 
		this.idServicio = idServicio;
		this.estado = estado;
		this.idCampaña = idCampaña;
	}
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Date getFecha() {
		// TODO Auto-generated method stub
		return fecha;
	}

	@Override
	public long getIdServicio() {
		// TODO Auto-generated method stub
		return idServicio;
	}

	@Override
	public String getEstado() {
		// TODO Auto-generated method stub
		return estado;
	}

	
}
