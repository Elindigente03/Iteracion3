package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.IPS;
import uniandes.isis2304.EPSAndes.negocio.Usuario;

public class SQLIPS {

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
	public SQLIPS(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarIPS(PersistenceManager pm,long id ,long idEps ,String localizacion ,String nombre )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaIPS() + "(id,ideps,localizacion,nombre)" );
		q.setParameters(id,idEps ,localizacion ,nombre);
		 return (long) q.executeUnique();
	}
	public long eliminarIPS(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaIPS() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	public IPS darIPSPorId(PersistenceManager pm, long idIPS) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaIPS() + " WHERE id = ?");
		q.setResultClass(IPS.class);
		q.setParameters(idIPS);
		return (IPS) q.executeUnique();
	}

}
