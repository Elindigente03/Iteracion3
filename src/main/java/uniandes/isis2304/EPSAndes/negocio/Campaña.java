package uniandes.isis2304.EPSAndes.negocio;

public class Campaña implements VOCampaña
{
 private long id;
 private long idOrganizador;
 private String nombre;
 
 public Campaña(long id , long idOrganizador ,String nombre)
 {
	 this.id = id;
	 this.idOrganizador = idOrganizador;
	 this.nombre = nombre ;
	 
 }

@Override
public String getNombre() {
	// TODO Auto-generated method stub
	return nombre;
}

@Override
public long getOrganizador() {
	// TODO Auto-generated method stub
	return idOrganizador;
}

@Override
public long getId() {
	// TODO Auto-generated method stub
	return id;
}
}
