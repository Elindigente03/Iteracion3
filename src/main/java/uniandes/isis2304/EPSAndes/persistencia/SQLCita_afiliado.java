package uniandes.isis2304.EPSAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Afiliado;
import uniandes.isis2304.EPSAndes.negocio.Cita;
import uniandes.isis2304.EPSAndes.negocio.Cita_afiliado;
import uniandes.isis2304.EPSAndes.negocio.Orden;

public class SQLCita_afiliado {
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
	public SQLCita_afiliado (PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarCitaAfiliada(PersistenceManager pm, long id,long citaId ,long afiliadoId )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaCita_afiliado() + "(id, citaId, afiliadoId)" );
		q.setParameters(id, citaId, afiliadoId);
		 return (long) q.executeUnique();
	}
	public long eliminarCitaAfiliada(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaCita_afiliado() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	
	
	public Cita_afiliado darCitaAfiliadaPorId(PersistenceManager pm, long idOrden) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCita_afiliado() + " WHERE id = ?");
		q.setResultClass(Cita.class);
		q.setParameters(idOrden);
		return (Cita_afiliado) q.executeUnique();
		}
}
