package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.OrganizadorCampaña;
import uniandes.isis2304.EPSAndes.negocio.Usuario;

public class SQLOrganizadorCampaña 
{

	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las
	 * sentencias de acceso a la base de datos Se renombra acá para facilitar la
	 * escritura de las sentencias
	 */
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
	public SQLOrganizadorCampaña (PersistenciaEPSAndes pp) {
		this.pp = pp;
	}

	public long adicionarOrganizadorCampaña(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrganizadorCampaña() + "(id) values (?)");
		q.setParameters(id );
		return (long) q.executeUnique();
	}
	
	public long eliminarOrganizadorCampaña(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrganizadorCampaña() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	public OrganizadorCampaña darOrganizadorCampañaPorId(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE id = ?");
		q.setResultClass(OrganizadorCampaña.class);
		q.setParameters(id);
		return (OrganizadorCampaña) q.executeUnique();
	}
}
