package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Campaña;
import uniandes.isis2304.EPSAndes.negocio.Orden;

public class SQLCampaña
{
	private final static String SQL = PersistenciaEPSAndes.SQL;

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaEPSAndes pp;

	/**
	 * Constructor
	 * 
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLCampaña (PersistenciaEPSAndes pp) 
	{
		this.pp = pp;
	}
	public long adicionarCampaña(PersistenceManager pm, long id, long idOrganizador, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCampaña() + "(id,idOrganizador , nombre) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idOrganizador, nombre);
		return (long) q.executeUnique();
	}
	public long eliminarUsuarioPorId(PersistenceManager pm, long idCampaña) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCampaña() + " WHERE id = ?");
		q.setParameters(idCampaña);
		return (long) q.executeUnique();
	}
	public Campaña darCampañaPorId(PersistenceManager pm, long idCampaña) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampaña() + " WHERE id = ?");
		q.setResultClass(Campaña.class);
		q.setParameters(idCampaña);
		return (Campaña) q.executeUnique();
	}
}
