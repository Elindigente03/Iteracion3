package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Rol;
import uniandes.isis2304.EPSAndes.negocio.Usuario;

public class SQLUsuario {

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
	public SQLUsuario (PersistenciaEPSAndes pp) {
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPOBEBIDA a la base de
	 * datos de Parranderos
	 * 
	 * @param pm           - El manejador de persistencia
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @param nombre       - El nombre del tipo de bebida
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarUsuario(PersistenceManager pm, long idRol, String email, String nombre, long numeroDocumento, String rol, String tipoDocumento ) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + "(id, email, nombre, numeroDocumento, rol, tipoDocumento) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idRol, email, nombre, numeroDocumento, rol, tipoDocumento);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE BEBIDA de la base de
	 * datos de Parranderos, por su nombre
	 * 
	 * @param pm               - El manejador de persistencia
	 * @param nombreTipoBebida - El nombre del tipo de bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorNombre(PersistenceManager pm, String nombreUsuario) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario() + " WHERE nombre = ?");
		q.setParameters(nombreUsuario);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE BEBIDA de la base de
	 * datos de Parranderos, por su identificador
	 * 
	 * @param pm           - El manejador de persistencia
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorId(PersistenceManager pm, long idUsuario) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario() + " WHERE id = ?");
		q.setParameters(idUsuario);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE
	 * BEBIDA de la base de datos de Parranderos, por su identificador
	 * 
	 * @param pm           - El manejador de persistencia
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TIPOBEBIDA que tiene el identificador dado
	 */
	public Usuario darUsuarioPorId(PersistenceManager pm, long idUsuario) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE id = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(idUsuario);
		return (Usuario) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE
	 * BEBIDA de la base de datos de Parranderos, por su nombre
	 * 
	 * @param pm               - El manejador de persistencia
	 * @param nombreTipoBebida - El nombre del tipo de bebida
	 * @return El objeto TIPOBEBIDA que tiene el nombre dado
	 */
	public List<Rol> darRolesPorNombre(PersistenceManager pm, String nombreRol) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol() + " WHERE nombre = ?");
		q.setResultClass(Rol.class);
		q.setParameters(nombreRol);
		return (List<Rol>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE
	 * BEBIDA de la base de datos de Parranderos
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOBEBIDA
	 */
	public List<Rol> darRoles(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol());
		q.setResultClass(Rol.class);
		return (List<Rol>) q.executeList();
	}

}
