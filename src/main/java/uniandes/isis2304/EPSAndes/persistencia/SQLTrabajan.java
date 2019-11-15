package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTrabajan {

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
	public SQLTrabajan(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarConsulta(PersistenceManager pm,long id , long idMedico , long idIps )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaTrabajan() + "(id, idmedico , idips)" );
		q.setParameters(id , idMedico , idIps);
		 return (long) q.executeUnique();
	}
	public long eliminarConsulta(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaTrabajan() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
}
