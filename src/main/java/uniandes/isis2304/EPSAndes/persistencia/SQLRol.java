/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Rol;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto TIPO DE BEBIDA de Parranderos Nótese que es una clase que es sólo
 * conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRol {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las
	 * sentencias de acceso a la base de datos Se renombra acá para facilitar la
	 * escritura de las sentencias
	 */
	private final static String SQL = PersistenciaEPSAndes.SQL;

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaEPSAndes pp;

	/*
	 * **************************************************************** Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * 
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLRol(PersistenciaEPSAndes pp) {
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
	public long adicionarRol(PersistenceManager pm, long idRol, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRol() + "(id, nombre) values (?, ?)");
		q.setParameters(idRol, nombre);
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
	public long eliminarRolPorNombre(PersistenceManager pm, String nombreRol) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol() + " WHERE nombre = ?");
		q.setParameters(nombreRol);
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
	public long eliminarRolPorId(PersistenceManager pm, long idRol) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol() + " WHERE id = ?");
		q.setParameters(idRol);
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
	public Rol darRolPorId(PersistenceManager pm, long idRol) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol() + " WHERE id = ?");
		q.setResultClass(Rol.class);
		q.setParameters(idRol);
		return (Rol) q.executeUnique();
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
