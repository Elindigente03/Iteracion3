package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLGerente {

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
	public SQLGerente(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarGerente(PersistenceManager pm,long id ,long idUsuario)
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaGerente() + "(id,idgerenteusuario)" );
		q.setParameters(id,idUsuario);
		 return (long) q.executeUnique();
	}
	public long eliminarGerente(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaGerente() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
}
