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

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el
 * concepto BAR de Parranderos Nótese que es una clase que es sólo conocida en
 * el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil {
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
	public SQLUtil(PersistenciaEPSAndes pp) {
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * 
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval(PersistenceManager pm) {
		//Query q = pm.newQuery(SQL, "SELECT " + pp.darSeqParranderos() + ".nextval FROM DUAL");
		Query q = pm.newQuery(SQL, "SELECT " + pp.darSeqEPSAndes() + ".nextval FROM DUAL");
		
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL
	 * ORDEN ES IMPORTANTE
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en
	 *         las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA, TIPOBEBIDA, BEBEDOR y
	 *         BAR, respectivamente
	 */
	public long[] limpiarEPSAndes(PersistenceManager pm) {
	

		Query qAdministrador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministrador());
		Query qAfiliado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAfiliado());
		Query qCita = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita());
		Query qConsulta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConsulta());
		Query qControl = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaControl());
		Query qEPS = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEPS());
		Query qExamenDiagnostico = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaExamenDiagnostico());
		Query qGerente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGerente());
		Query qHospitalizacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHospitalizacion());
		Query qIPS = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaIPS());
		Query qMedico = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMedico());
		Query qOrden = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrden());
		Query qProcedimientoEspecializado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProcedimientoEspecializado());
		Query qRecepcionista = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecepcionista());
		Query qRemision = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRemision());
		Query qRol = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol());
		Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
		Query qTerapia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTerapia());
		Query qTrabajan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTrabajan());
		Query qUrgecia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUrgencia());
		Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());

		
		
		
		
		long administradorEliminados= (long) qAdministrador.executeUnique();
		long afiliadoEliminados= (long) qAfiliado.executeUnique();
		long citaEliminados= (long) qCita.executeUnique();
		long consultaEliminados= (long) qConsulta.executeUnique();
		long controlEliminados= (long) qControl.executeUnique();
		long epsEliminados= (long) qEPS.executeUnique();
		long examenDiagnosticoEliminados= (long) qExamenDiagnostico.executeUnique();
		long GerenteEliminados= (long) qGerente.executeUnique();
		long HospitalizacionEliminados= (long) qHospitalizacion.executeUnique();
		long ipsEliminados= (long) qIPS.executeUnique();
		long medicoEliminados= (long) qMedico.executeUnique();
		long ordenEliminados= (long) qOrden.executeUnique();
		long procedimientoEspecializadoEliminados= (long) qProcedimientoEspecializado.executeUnique();
		long recepcionistaEliminados= (long) qRecepcionista.executeUnique();
		long remisionEliminados= (long) qRemision.executeUnique();
		long rolEliminados= (long) qRol.executeUnique();
		long servicioEliminados= (long) qServicio.executeUnique();
		long terapiaEliminados= (long) qTerapia.executeUnique();
		long trabajanEliminados= (long) qTrabajan.executeUnique();
		long urgenciaEliminados= (long) qUrgecia.executeUnique();
		long usuarioEliminados= (long) qUsuario.executeUnique();
	
		return new long[] { administradorEliminados,afiliadoEliminados,citaEliminados,consultaEliminados,controlEliminados,epsEliminados,examenDiagnosticoEliminados,GerenteEliminados,
				HospitalizacionEliminados,ipsEliminados,medicoEliminados,ordenEliminados,procedimientoEspecializadoEliminados,recepcionistaEliminados,remisionEliminados,
				rolEliminados,servicioEliminados,terapiaEliminados,trabajanEliminados,urgenciaEliminados,usuarioEliminados};
	
	}

}
