package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Orden;
import uniandes.isis2304.EPSAndes.negocio.Usuario;

public class SQLOrden {

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
	public SQLOrden(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarOrden(PersistenceManager pm,long id ,long idServicio, String nombreCampaña)
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaOrden() + "(id, idservicio)" );
		q.setParameters(id, idServicio);
		 return (long) q.executeUnique();
	}
	public long eliminarOrden(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaOrden() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	public Orden darOrdenPorId(PersistenceManager pm, long idOrden) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrden() + " WHERE id = ?");
		q.setResultClass(Orden.class);
		q.setParameters(idOrden);
		return (Orden) q.executeUnique();
	}
	public Orden cambiarestadoOrdenD(PersistenceManager pm, long idOrden ,String nombreCampaña) {
		Query q = pm.newQuery(SQL, "Update" + pp.darTablaOrden() + "set estado ,nombrecampaña = deshabilitado , ?  where id =" + idOrden);
		q.setResultClass(Orden.class);
		q.setParameters(nombreCampaña);
		return (Orden) q.executeUnique();
	}
	public Orden cambiarestadoOrdenH(PersistenceManager pm, long idOrden  ) {
		Query q = pm.newQuery(SQL, "Update" + pp.darTablaOrden() + "set estado ,nombrecampaña = habilitado , ?  where id =" + idOrden);
		q.setResultClass(Orden.class);
		q.setParameters(" ");
		return (Orden) q.executeUnique();
	}
}
