package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLMedico {

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
	public SQLMedico (PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarMedico(PersistenceManager pm,long id, String especialidad , String numeroRegistro , long idUsuario )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaMedico() + "(id,especialidad , numeroregistr,idusuario)" );
		q.setParameters(id,especialidad,numeroRegistro,idUsuario);
		 return (long) q.executeUnique();
	}
	public long eliminarConsulta(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaMedico() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
}
