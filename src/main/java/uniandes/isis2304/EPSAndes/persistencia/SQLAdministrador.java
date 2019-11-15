package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.*;

public class SQLAdministrador {
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
	public SQLAdministrador(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
    
	public long adicionarAdministrador(PersistenceManager pm,long id ,long idUsuario , long Eps )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaAdministrador() + "(id,idUsuario, ideps)" );
		q.setParameters(id,idUsuario,Eps);
		 return (long) q.executeUnique();
	}
	public long eliminarAdministrador(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaAdministrador() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
}

