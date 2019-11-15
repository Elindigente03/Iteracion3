package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.IPS;
import uniandes.isis2304.EPSAndes.negocio.Servicio;

public class SQLServicio {

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
	public SQLServicio(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarServicio(PersistenceManager pm,long id ,long capacidad , String nombre , long IPS)
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaServicio() + "(id,capacidad ,nombre ,IPS)" );
		q.setParameters(id , capacidad , nombre ,IPS);
		 return (long) q.executeUnique();
	}
	public long eliminarServicioID(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaServicio() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	public Servicio darServicioPorId(PersistenceManager pm, long idServicio) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio() + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (Servicio) q.executeUnique();
	}
}
