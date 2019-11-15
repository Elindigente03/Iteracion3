package uniandes.isis2304.EPSAndes.negocio;

public class Trabajan implements VOTrabajan{
    
	
	private long idIps;
	public long idMedico;
	public long id;
	
	public Trabajan (long id ,long idIps , long idMedico )
	{
		this.id = id;
	   this.idIps = idIps;
	   this.idMedico = idMedico;
	}

	@Override
	public long getIdIps() {
		// TODO Auto-generated method stub
		return idIps;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public long getIdMedico() {
		// TODO Auto-generated method stub
		return idMedico;
	}
	
	
}
