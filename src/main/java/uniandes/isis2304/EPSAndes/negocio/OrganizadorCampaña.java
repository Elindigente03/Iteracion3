package uniandes.isis2304.EPSAndes.negocio;

public class OrganizadorCampaña implements VOOrganizadorCampaña
{
   private long id;
   
   public OrganizadorCampaña (long id)
   {
      this.id = id;
   }

@Override
public long getId() {
	// TODO Auto-generated method stub
	return id;
}
   
   
}
