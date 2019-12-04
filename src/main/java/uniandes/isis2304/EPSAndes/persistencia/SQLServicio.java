package uniandes.isis2304.EPSAndes.persistencia;

import java.util.ArrayList;
import java.util.List;

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
	public List<Servicio> darServicios(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM "+pp.darTablaServicio()+ " ORDER BY id");
		q.setResultClass(List.class);
		return (List<Servicio>) q.executeUnique();
	}
	public ArrayList<Servicio> darServiciosPorCapacidad(PersistenceManager pm, long capacidad1, long capacidad2){
		
		Query q = pm.newQuery(SQL, "SELECT * FROM "+pp.darTablaServicio()+ " WHERE capacidad BETWEEN ? AND ?");
		
		q.setParameters(capacidad1, capacidad2);
		return (ArrayList<Servicio>) q.executeList();
		
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
	public ArrayList<Servicio> darServicioPorNombre(PersistenceManager pm, String nombre) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio() + " WHERE nombre = ?");
		q.setResultClass(ArrayList.class);
		q.setParameters(nombre);
		return (ArrayList<Servicio>) q.executeList();
	}
	public ArrayList<Servicio> darServicioPorIPS(PersistenceManager pm, long ips) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio() + " WHERE ips = ?");
		q.setResultClass(ArrayList.class);
		q.setParameters(ips);
		return (ArrayList<Servicio>) q.executeList();
	}
	
}
