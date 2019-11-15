package uniandes.isis2304.EPSAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Cita;
import uniandes.isis2304.EPSAndes.negocio.Orden;

public class SQLCita {

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
	public SQLCita (PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarCita(PersistenceManager pm,long id ,long asistio, Date fecha , long idServicio ,String nombreServicio ,long idMedico )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaCita() + "(id,asistio, fecha,idservicio,nombreservicio,idMedico)" );
		q.setParameters(id,asistio,fecha,idServicio,nombreServicio,idMedico);
		 return (long) q.executeUnique();
	}
	public long eliminarAfiliado(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaCita() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	public Cita cambiarEstadoCitaA(PersistenceManager pm, long idUsuario) {
		Query q = pm.newQuery(SQL, "Update" + pp.darTablaCita() + "set estado = Asistio  where id =" + idUsuario);
		q.setResultClass(Cita.class);
		q.setParameters(idUsuario);
		return (Cita) q.executeUnique();
	}
	public Cita darCitaPorId(PersistenceManager pm, long idOrden) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCita() + " WHERE id = ?");
		q.setResultClass(Cita.class);
		q.setParameters(idOrden);
		return (Cita) q.executeUnique();
		}
}
