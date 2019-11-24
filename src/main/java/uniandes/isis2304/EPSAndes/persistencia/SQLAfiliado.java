package uniandes.isis2304.EPSAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Afiliado;
import uniandes.isis2304.EPSAndes.negocio.Orden;

public class SQLAfiliado {

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
	public SQLAfiliado(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}
	public long adicionarAfiliado(PersistenceManager pm,long id ,String estadoSalud , Date fechaNacimiento , String recetaActual , long idOrden , long idUsuario , long idEps  )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaAfiliado() + "(id,estadosalud, fechanacimiento,recetaactual,idorden,idusuarioafiliado,ideps)" );
		q.setParameters(id,estadoSalud,fechaNacimiento,recetaActual,idOrden,idUsuario ,idOrden );
		 return (long) q.executeUnique();
	}
	public long eliminarAfiliado(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaAfiliado() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}

	public Afiliado darAfiliadoPorId(PersistenceManager pm, long idAfiliado) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAfiliado() + " WHERE id = ?");
		q.setResultClass(Afiliado.class);
		q.setParameters(idAfiliado);
		return (Afiliado) q.executeUnique();
	}
}
