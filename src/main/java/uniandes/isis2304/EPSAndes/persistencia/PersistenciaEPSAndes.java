

package uniandes.isis2304.EPSAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.EPSAndes.negocio.Afiliado;
import uniandes.isis2304.EPSAndes.negocio.Campaña;
import uniandes.isis2304.EPSAndes.negocio.Cita;
import uniandes.isis2304.EPSAndes.negocio.Exigente;
import uniandes.isis2304.EPSAndes.negocio.IPS;
import uniandes.isis2304.EPSAndes.negocio.Medico;
import uniandes.isis2304.EPSAndes.negocio.Orden;
import uniandes.isis2304.EPSAndes.negocio.Rol;
import uniandes.isis2304.EPSAndes.negocio.Servicio;
import uniandes.isis2304.EPSAndes.negocio.ServiciosN;
import uniandes.isis2304.EPSAndes.negocio.Usuario;
import uniandes.isis2304.EPSAndes.negocio.cantidadCItas;
import uniandes.isis2304.EPSAndes.negocio.demanda;


/**
 * Clase para el manejador de persistencia del proyecto EPSAndes Traduce la
 * información entre objetos Java y tuplas de la base de datos, en ambos
 * sentidos Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase)
 * para comunicarse de manera correcta con la base de datos Se apoya en las
 * clases SQLAdministrador, SQLGerente, SQLAfiliado, SQLEPS, SQLUsuario, SQLMedico, SQLIPS,
 * SQLRecepcionista, SQLOrden, SQLServicio, SQLConsulta, SQLTrabajan, SQLUrgencia, 
 * SQLRol, SQLHospitalizacion, SQLRemision, SQLControl, SQLTerapia, 
 * SQLProcedimientoEspecializado y SQLExamenDiagnostico
 * ; que son las que realizan el acceso a la base de datos
 * 
 */
public class PersistenciaEPSAndes {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaEPSAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una
	 * consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaEPSAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las
	 * transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su
	 * orden: Secuenciador,  Administrador, Gerente, Afiliado, EPS, Usuario, Medico, IPS,
	 * Recepcionista, Orden, Servicio, Consulta, Trabajan, Urgencia, 
	 * Rol, Hospitalizacion, Remision, Control, Terapia, 
	 * ProcedimientoEspecializado y ExamenDiagnostico
	 */
	private List<String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a
	 * PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	//----------------------tablasParranderos----------------------inicio
	
	
	
	//----------------------tablasEPSAndes---------------------inicio
	private SQLAdministrador sqlAdministrador;
	private SQLAfiliado sqlAfiliado;
	private SQLCita sqlCita;
	private SQLConsulta sqlConsulta;
	private SQLControl sqlControl;
	private SQLEPS sqlEPS;
	private SQLExamenDiagnostico sqlExamenDiagnostico;
	private SQLGerente sqlGerente;
	private SQLHospitalizacion sqlHospitalizacion;
	private SQLIPS sqlIPS;
	private SQLMedico sqlMedico;
	private SQLOrden sqlOrden;
	private SQLProcedimientoEspecializado sqlProcedimientoEspecializado;
	private SQLRecepcionista sqlRecepcionista;
	private SQLRemision sqlRemision;
	private SQLRol sqlRol;
	private SQLServicio sqlServicio;
	private SQLTerapia sqlTerapia;
	private SQLTrabajan sqlTrabajan;
	private SQLUrgencia sqlUrgencia;
	private SQLUsuario sqlUsuario;
	private SQLCampaña sqlCampaña;
	private SQLOrganizadorCampaña sqlOrganizadorCampaña;
	private SQLCita_afiliado sqlCita_afiliado;
	
	//----------------------tablasEPSAndes---------------------fin

	/*
	 * **************************************************************** Métodos del
	 * MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaEPSAndes() {
		pmf = JDOHelper.getPersistenceManagerFactory("EPSAndes");
	
		crearClasesSQL();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String>();
	
		
		//Denfinir nombres defaul de las tablas de la base de datos de EPSAndes
		tablas.add("EPSAndes_sequence");
		tablas.add("ADMINISTRADOR");
		tablas.add("AFILIADO");
		tablas.add("CITA");
		tablas.add("CONSULTA");
		tablas.add("CONTROL");
		tablas.add("EPS");
		tablas.add("EXAMEN_DIAGNOSTICO");
		tablas.add("GERENTE");
		tablas.add("HOSPITALIZACION");
		tablas.add("IPS");
		tablas.add("MEDICO");
		tablas.add("ORDEN");
		tablas.add("PROCEDIMIENTO_ESPECIALIZADO");
		tablas.add("RECEPCIONISTA");
		tablas.add("REMISION");
		tablas.add("ROL");
		tablas.add("SERVICIO");
		tablas.add("TERAPIA");
		tablas.add("TRABAJAN");
		tablas.add("URGENCIA");
		tablas.add("USUARIO");
		tablas.add("CAMPAÑA");
		tablas.add("ORGANIZADORcAMPAÑA");
		tablas.add("CITA_AFILIADO");
				
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json -
	 * Patrón SINGLETON
	 * 
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de
	 *                    la unidad de persistencia a manejar
	 */
	private PersistenciaEPSAndes(JsonObject tableConfig) {
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig); 
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón
	 *         SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance() {
		if (instance == null) {
			instance = new PersistenciaEPSAndes();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto
	 * tableConfig
	 * 
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón
	 *         SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance(JsonObject tableConfig) {
		if (instance == null) {
			instance = new PersistenciaEPSAndes(tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia() {
		pmf.close();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * 
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List<String> leerNombresTablas(JsonObject tableConfig) {
		JsonArray nombres = tableConfig.getAsJsonArray("tablas");
		//TODO VERTABCONFIG
		List<String> resp = new LinkedList<String>();
		for (JsonElement nom : nombres) {
			resp.add(nom.getAsString());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL() {
		
		sqlAdministrador = new SQLAdministrador(this);
		sqlAfiliado = new SQLAfiliado(this);
		sqlCita = new SQLCita(this);
		sqlConsulta = new SQLConsulta(this);
		sqlControl = new SQLControl(this);
		sqlEPS = new SQLEPS(this);
		sqlExamenDiagnostico = new SQLExamenDiagnostico(this);
		sqlGerente = new SQLGerente(this);
		sqlHospitalizacion = new SQLHospitalizacion(this);
		sqlIPS = new SQLIPS(this);
		sqlMedico = new SQLMedico(this);
		sqlOrden = new SQLOrden(this);
		sqlProcedimientoEspecializado = new SQLProcedimientoEspecializado(this);
		sqlRecepcionista = new SQLRecepcionista(this);
		sqlRemision = new SQLRemision(this);
		sqlRol = new SQLRol(this);
		sqlServicio = new SQLServicio(this);
		sqlTerapia = new SQLTerapia(this);
		sqlTrabajan = new SQLTrabajan(this);
		sqlUrgencia = new SQLUrgencia(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCampaña = new SQLCampaña(this);
		sqlOrganizadorCampaña = new SQLOrganizadorCampaña(this);
		sqlCita_afiliado = new SQLCita_afiliado(this);
		}

	
	public String darSeqEPSAndes() {
		return tablas.get(0);
	}
	public String darTablaAdministrador() {
		return tablas.get(1);
	}
	public String darTablaAfiliado() {
		return tablas.get(2);
	}
	public String darTablaCita() {
		return tablas.get(3);
	}
	public String darTablaConsulta() {
		return tablas.get(4);
	}
	public String darTablaControl() {
		return tablas.get(5);
	}
	public String darTablaEPS() {
		return tablas.get(6);
	}
	public String darTablaExamenDiagnostico() {
		return tablas.get(7);
	}
	public String darTablaGerente() {
		return tablas.get(8);
	}
	public String darTablaHospitalizacion() {
		return tablas.get(9);
	}
	public String darTablaIPS() {
		return tablas.get(10);
	}
	public String darTablaMedico() {
		return tablas.get(11);
	}
	public String darTablaOrden() {
		return tablas.get(12);
	}
	public String darTablaProcedimientoEspecializado() {
		return tablas.get(13);
	}
	public String darTablaRecepcionista() {
		return tablas.get(14);
	}
	public String darTablaRemision() {
		return tablas.get(15);
	}	
	public String darTablaRol() {
		return tablas.get(16);
	}
	public String darTablaServicio() {
		return tablas.get(17);
	}
	public String darTablaTerapia() {
		return tablas.get(18);
	}
	public String darTablaTrabajan() {
		return tablas.get(19);
	}
	public String darTablaUrgencia() {
		return tablas.get(20);
	}
	public String darTablaUsuario() {
		return tablas.get(21);
	}
		public String darTablaCampaña()
	{
		return tablas.get(22);
	}
	public String darTablaOrganizadorCampaña()
	{
		 return tablas.get(23);
	}
	public String darTablaCita_afiliado() {
		return tablas.get(24);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos Adiciona entradas
	 * al log de la aplicación
	 * 
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval() {
		long resp = sqlUtil.nextval(pmf.getPersistenceManager());
		log.trace("Generando secuencia: " + resp);
		return resp;
	}
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la
	 * Exception e, que da el detalle específico del problema encontrado
	 * 
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	
	//--------------------------Comienzo de los métodos necesarios para RF1--
	
	public List<Rol> registrarRolesUsuario(List<String> roles)
	{
		List <Rol> resp = new LinkedList<Rol>();
		for(String rolTemporal : roles)
		{
			Rol rolTemp = adicionarRol(rolTemporal);
			resp.add(rolTemp);
		}
		return resp;
		
	}
	/*
	 * **************************************************************** Métodos para
	 * manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Rol adicionarRol(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idRol = nextval();
			long tuplasInsertadas = sqlRol.adicionarRol(pm, idRol, nombre);
			tx.commit();

			log.trace("Inserción de rol: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Rol(idRol, nombre);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * TipoBebida, dado el nombre del tipo de bebida Adiciona entradas al log de la
	 * aplicación
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarRolPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlRol.eliminarRolPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * TipoBebida, dado el identificador del tipo de bebida Adiciona entradas al log
	 * de la aplicación
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarRolPorId(long idRol) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlRol.eliminarRolPorId(pm, idRol);
			tx.commit();
			return resp;
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * 
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de
	 *         la tabla TIPOBEBIDA
	 */
	public List<Rol> darRoles() {
		return sqlRol.darRoles(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida que tienen el
	 * nombre dado
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de
	 *         la tabla TIPOBEBIDA
	 */
	public List<Rol> darRolPorNombre(String nombre) {
		return sqlRol.darRolesPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un
	 * identificador dado
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla
	 *         TIPOBEBIDA con el identificador dado
	 */
	public Rol darRolPorId(long idRol) {
		return sqlRol.darRolPorId(pmf.getPersistenceManager(), idRol);
	}
	
	//--------------------------final de los métodos necesarios para los RF1--
	
	
	
	/*
	 * **************************************************************** Métodos para
	 * manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Usuario adicionarUsuario(String email,String nombre,long numDoc ,String rol ,String tipoDocumento) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idUsuario = nextval();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, idUsuario, email , nombre , numDoc, rol, tipoDocumento ); 
			tx.commit();

			log.trace("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Usuario(idUsuario, email, nombre, numDoc, rol,tipoDocumento);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public IPS adicionarIPS(long idEps , String localizacion , String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idIPS = nextval();
			
			long tuplasInsertadas = sqlIPS.adicionarIPS(pm, idIPS, idEps, localizacion, nombre) ;
			tx.commit();

			log.trace("Inserción de IPS: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new IPS(idIPS, idEps, localizacion, nombre);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public Medico adicionarMedico(String numeroRegistro , String especialidad , long idUsuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idMedico = nextval();
			
			long tuplasInsertadas = sqlMedico.adicionarMedico(pm,idMedico, numeroRegistro, especialidad, idUsuario) ;
			tx.commit();
			if(sqlUsuario.darUsuarioPorId(pm, idUsuario)== null)
			{
				throw new Exception();
			}
			log.trace("Inserción de Medico: " + numeroRegistro + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Medico(idMedico,numeroRegistro,especialidad,idUsuario);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public Afiliado adicionarAfiliado(String estadoSalud,Date fechaNacimiento, String recetaActual ,long idO ,long idU ,long idE) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idAfiliado = nextval();
			
			long tuplasInsertadas = sqlAfiliado.adicionarAfiliado(pm,idAfiliado, estadoSalud, fechaNacimiento, recetaActual,idO,idU,idE);
			tx.commit();
			if(sqlUsuario.darUsuarioPorId(pm, idU)== null)
			{
				throw new Exception();
			}
			log.trace("Inserción de Afiliado: " + idAfiliado + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Afiliado(idAfiliado,estadoSalud,fechaNacimiento,recetaActual,idO,idU,idE);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Servicio adicionarServicio(long capacidad ,String nombre , long idIPS)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idServicio = nextval();
			
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm,idServicio,capacidad,nombre,idIPS);
			tx.commit();
			if(sqlIPS.darIPSPorId(pm, idIPS)== null)
			{
				throw new Exception("la IPS no se encuentra en nuestro catalogo intente de nuevo");
			}
			log.trace("Inserción de Servicio: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Servicio(idServicio,capacidad,nombre,idIPS);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public Afiliado registrarCitaAfiliado(long id, long idCita,long idAfiliado, long idServicio, Date dia, int efectuado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			tx.commit();
			if(sqlAfiliado.darAfiliadoPorId(pm, idAfiliado)== null)
			{
				throw new Exception("el afiliado no se encuentra en nuestro catalogo intente de nuevo");
			}
			log.trace("Inserción de Servicio: " + idCita + ": ");
		
			sqlCita_afiliado.adicionarCitaAfiliada(pm, id, idCita, idAfiliado, idServicio, dia, efectuado);
			return sqlAfiliado.darAfiliadoPorId(pm, idAfiliado);
		} catch (Exception e) {
		 //        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public Afiliado reservaCita(long idReserva, long idCita , long idAfiliado, long idServicio, Date dia, int efectuado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			tx.commit();
			if (sqlAfiliado.darAfiliadoPorId(pm, idAfiliado)== null)
			{
				throw new Exception("el afiliado no se encuentra en nuestro catalogo intente de nuevo");
			}
			log.trace("Inserción de Servicio: " + idCita + ": ");
			sqlCita.cambiarEstadoCitaA(pm, idAfiliado);
			registrarCitaAfiliado(idReserva, idCita, idAfiliado, idServicio, dia, efectuado);
			return sqlAfiliado.darAfiliadoPorId(pm, idAfiliado);
		} catch (Exception e) {
		 //        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	public Cita registrarAsistencia(long idCita)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			if(sqlCita.darCitaPorId(pm, idCita)== null)
			{
				throw new Exception("la Cita no se encuentra en nuestro catalogo intente de nuevo");
			}
						
			sqlCita.cambiarEstadoCitaA(pm, idCita);
			tx.commit();
			log.trace("Inserción de Servicio: " + idCita + ". ");
			return sqlCita.darCitaPorId(pm, idCita);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	//rf1 it
	public Campaña registrarCampaña(long idOrganizador , String nombre , LinkedList<Long> servicios )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idCampaña = nextval();
			
			long tuplasInsertadas = sqlCampaña.adicionarCampaña(pm,idCampaña, idOrganizador, nombre) ;
			tx.commit();
			
			if(sqlOrganizadorCampaña.darOrganizadorCampañaPorId(pm, idOrganizador)== null)
			{
				throw new Exception("no existe un Organizador de campaña con ese ID");
			}
			for (Long long1 : servicios)
			{
				sqlOrden.cambiarestadoOrdenD(pm, long1, nombre);
			}
			
			log.trace("cracion de campaña: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Campaña(idCampaña,idOrganizador,nombre);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Campaña cancelarServiciosCampaña(long idCampaña,long idOrganizador , String nombre ,LinkedList<Long> servicios)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
		
			
			long tuplasInsertadas = sqlCampaña.adicionarCampaña(pm,idCampaña, idOrganizador, nombre) ;
			tx.commit();
			
			if(sqlOrganizadorCampaña.darOrganizadorCampañaPorId(pm, idOrganizador)== null)
			{
				throw new Exception("no existe un Organizador de campaña con ese ID");
			}
			if(sqlCampaña.darCampañaPorId(pm, idCampaña)== null)
			{
				throw new Exception("no existe una campaña con ese ID");
			}
			for (Long long1 : servicios)
			{
				sqlOrden.cambiarestadoOrdenH(pm, long1);
			}
			
			log.trace("cracion de campaña: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return sqlCampaña.darCampañaPorId(pm, idCampaña);
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	//req consulta 3
	public List<Integer> darInidiceUsoServicios(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			List<Integer> indicesOrdenados = sqlCita_afiliado.darIndicesServicios(pm);
			tx.commit();
			log.trace("Req de consulta 3: parte 1");
			return indicesOrdenados;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public List<Servicio> darServicios(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			List<Servicio> servicios = sqlServicio.darServicios(pm);
			tx.commit();
			log.trace("Req de consulta 3: parte 2");
			return servicios;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	//req consulta 4 (esta algo loco
	public ArrayList<Servicio> darServiciosPorRangoFechas(Date fecha1, Date fecha2){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			List<Object> idServicios = sqlCita_afiliado.darServiciosPorRangoFecha(pm, fecha1, fecha2);
			ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
			for (int i =0;i<idServicios.size();i++) {
				listaServicios.add(sqlServicio.darServicioPorId(pm, (long) idServicios.get(i)));
			}
			tx.commit();
			log.trace("Req de consulta 4: parte 1");
			return listaServicios;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	public ArrayList<Servicio> darServiciosPorCapacidad(long capacidad1, long capacidad2){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<Servicio> listaServicios = sqlServicio.darServiciosPorCapacidad(pm, capacidad1, capacidad2);
			tx.commit();
			log.trace("Req de consulta 4: parte 2");
			return listaServicios;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	public ArrayList<Servicio> darServiciosPorNombre(String nombre){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<Servicio> lista = sqlServicio.darServicioPorNombre(pm, nombre);
			tx.commit();
			log.trace("Req de consulta 4: parte 3");
			return lista;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public ArrayList<Servicio> darServiciosPorIps(long ips){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<Servicio> lista = sqlServicio.darServicioPorIPS(pm, ips);
			tx.commit();
			log.trace("Req de consulta 4: parte 4");
			return lista;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	//req consulta 5 /ez a la vez sirve pa la 4
	
	public ArrayList<Servicio> darServiciosDeAfiliado(long idAfiliado){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			List<Object> idServicios = sqlCita_afiliado.darServiciosDeAfiliado(pm, idAfiliado);
			ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
				
				
			
			for(int i=0; i<idServicios.size();i++) {
				listaServicios.add(sqlServicio.darServicioPorId(pm,(long) idServicios.get(i)));
			}
					
			tx.commit();
			log.trace("Req de consulta 4: ");
			return listaServicios;
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	//req consulta 6 (viajesote)
	/*
	 * Dada una unidad de tiempo (por ejemplo, semana o mes) y un servicio de salud10
	 * , considerando todo el tiempo de operación de EPSAndes, indicar cuáles fueron 
	 * las fechas de mayor demanda (mayor cantidad de servicios solicitados), las de
	 *  mayor actividad (mayor cantidad de servicios efectivamente prestados) y también 
	 *  las fechas de menor demanda.
	 */
	//rangoTemporal: Si es 1 entonces rango semanal, si es 0 es mensual
	public ArrayList<java.util.Date> darFechasMayorDemanda(int rangoTemporal){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<java.util.Date> lista  = new ArrayList<java.util.Date>();
			if(rangoTemporal==1) {
				 lista = sqlCita_afiliado.darDemandaSemanal(pm);
			}
			else {
				 lista = sqlCita_afiliado.darDemandaMensual(pm);
			}
			log.trace("Req de consulta 6: ");
			return lista;
			
			
			
			
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		
		
	}
	public ArrayList<java.util.Date> darFechasMenorDemanda(int rangoTemporal){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<java.util.Date> lista  = new ArrayList<java.util.Date>();
			if(rangoTemporal==1) {
				 lista = sqlCita_afiliado.darMenorDemandaSemanal(pm);
			}
			else {
				 lista = sqlCita_afiliado.darMenorDemandaMensual(pm);
			}
			log.trace("Req de consulta 6: ");
			return lista;
			
			
			
			
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		
		
	}
	public ArrayList<java.util.Date> darFechasMayorActividad(int rangoTemporal){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			ArrayList<java.util.Date> lista  = new ArrayList<java.util.Date>();
			if(rangoTemporal==1) {
				 lista = sqlCita_afiliado.darMayorActividadSemanal(pm);
				 
			}
			else {
				 lista = sqlCita_afiliado.darMayorActividadMensual(pm);
			}
			log.trace("Req de consulta 6: ");
			return lista;
			
			
			
			
		}
		catch(Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		
		
	}
/*
 * Se quiere conocer la información de los afiliados y el uso que han hecho de los servicios de salud disponibles.
Los criterios de consulta son los servicios utilizados, los tipos de servicio utilizados, las fechas (o rangos de
fecha) en los que los utilizaron, las IPS que los prestaron. Los resultados deben ser clasificados según los
criterios deseados por quien realiza la consulta. En la clasificación debe ofrecerse la posibilidad de
agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta como, por
ejemplo, por rango de fechas de nacimiento de los afiliados, por fecha y número de veces que se utilizó un
servicio, por tipo de servicio e IPS, etc. Esta operación está disponible para el gerente de la EPS y también para
los organizadores de campañas de salud.
NOTA: Para respetar la privacidad de los afiliados, el gerente de la EPS tiene acceso a toda la información,
mientras que los organizador
 */
	public void deshabilitarServiciosSalud()
	{
	}
	public void habilitarServiciosSalud()
	{
		
	}
	//requerimientos funcionales  consulta
	public List<ServiciosN> darCitas(Date inicial, Date finalf)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(SQL, " SELECT  IPS.NOMBRE, COUNT(Cita.ID)\r\n" + 
					"   FROM ("+this.darTablaIPS()+" INNER JOIN " + this.darTablaCita() +" ON IPS.ID = SERVICIO.IDIPS)  \r\n" + 
					"  WHERE (  SERVICIO.FECHA > ? AND SERVICIO.FECHA < ?); ");
			q.setResultClass(ServiciosN.class);
			q.setParameters(inicial ,finalf);
			tx.commit();
			log.info("se devolvieron los servicios prestados" );
			return (List<ServiciosN>) q.executeList();
			
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}
	public List<cantidadCItas> darCitas2(Date inicial, Date finalf)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(SQL, "  SELECT NOMBRE, COUNT(ID)\r\n" + 
					"   FROM" + this.darTablaCita() + "\r\n" + 
					"  WHERE (  FECHA > ? AND FECHA < ?)\r\n" + 
					"  ORDER BY COUNT(SERVICIOS) DESC\r\n" + 
					"  LIMIT 20; ");
			q.setResultClass(ServiciosN.class);
			q.setParameters(inicial ,finalf);
			tx.commit();
			log.info("se devolvieron los servicios prestados" );
			return (List<cantidadCItas>) q.executeList();
			
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}
	public List<Exigente> darExigentes()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(SQL, "select USUARIO.NOMBRE, USUARIO.ID , SERVICIO.NOMBRE ,servicio.id, count(servicioid)   \r\n" + 
					"from (("+ darTablaUsuario() +  "iner join"+ darTablaAfiliado()  +"  on afiliado.id = usuario.id) inerr join "+ darTablaCita() +" on cita.IDUSUARIO)  \r\n" + 
					"WHERE count(servicioid) >12  ");
			q.setResultClass(Exigente.class);
			tx.commit();
			log.info("se devolvieron los servicios prestados" );
			return (List<Exigente>) q.executeList();
			
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}
	public List<demanda> darDemanda()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(SQL, "  select servicioid   \r\n" + 
					"from  "+ darTablaCita() +"on cita.IDUSUARIO)  \r\n" + 
					"WHERE asistio = 1 and count(to_char(fecha,'MM/YY'))>3 \r\n" + 
					"Group by to_char(fecha,'MM/YY') ");
			q.setResultClass(ServiciosN.class);
			
			tx.commit();
			log.info("se devolvieron los servicios prestados" );
			return (List<demanda>) q.executeList();
			
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}
	public long[] limpiarEPSAndes() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long[] resp = sqlUtil.limpiarEPSAndes(pm);
			tx.commit();
			log.info("Borrada la base de datos");
			return resp;
		} catch (Exception e) {
			//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			
			return  new long[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

}
