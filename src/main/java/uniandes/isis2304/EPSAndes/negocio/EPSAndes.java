package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.EPSAndes.persistencia.PersistenciaEPSAndes;



public class EPSAndes 
{
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(EPSAndes.class.getName());

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaEPSAndes pp;

	/**
	 * los roles de usuario
	 */
	private List<String> rolesDeUsuario ;
	/**
	 * El constructor por defecto
	 */
	
	public EPSAndes() {
		pp = PersistenciaEPSAndes.getInstance();
		rolesDeUsuario = new LinkedList<String>();
		rolesDeUsuario.add("administrador");
		rolesDeUsuario.add("afiliado");
		rolesDeUsuario.add("gerente");
		rolesDeUsuario.add("medico");
		rolesDeUsuario.add("recepcionista");
		}
	
	public EPSAndes(JsonObject tableConfig) {
		pp = PersistenciaEPSAndes.getInstance (tableConfig);
		rolesDeUsuario = new LinkedList<String>();
		rolesDeUsuario.add("administrador");
		rolesDeUsuario.add("afiliado");
		rolesDeUsuario.add("gerente");
		rolesDeUsuario.add("medico");
		rolesDeUsuario.add("recepcionista");
	}
	public void cerrarUnidadPersistencia() {
		pp.cerrarUnidadPersistencia();
	}
	
	public List<Rol> registrarRolesUsuario()
	{
		return pp.registrarRolesUsuario(rolesDeUsuario);
		
	}
	
	public Rol adicionarRol(String nombre) {
		log.info("Adicionando Rol: " + nombre);
		Rol rol= pp.adicionarRol(nombre);
		log.info("Adicionando Rol: " + rol);
		return rol;
	}
	
	public long eliminarRolPorNombre(String nombre) {
		log.info("Eliminando Rol por nombre: " + nombre);
		long resp = pp.eliminarRolPorNombre(nombre);
		log.info("Eliminando Rol por nombre: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	public long eliminarRolPorId(long idRol) {
		log.info("Eliminando Rol por id: " + idRol);
		long resp = pp.eliminarRolPorId(idRol);
		log.info("Eliminando Rol por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	public List<Rol> darRoles() {
		log.info("Consultando Roles");
		List<Rol> roles= pp.darRoles();
		log.info("Consultando Roles: " + roles.size() + " existentes");
		return roles;
	}
	public List<VORol> darVORoles() {
		log.info("Generando los VO de Roles");
		List<VORol> voRoles= new LinkedList<VORol>();
		List<Rol> lalista = pp.darRoles();
		for (Rol tb : pp.darRoles()) {
			voRoles.add(tb);
		}
		log.info("Generando los VO de Tipos de bebida: " + voRoles.size() + " existentes");
		return voRoles;
	}
	public Rol darRolPorNombre(String nombre) {
		log.info("Buscando Rol por nombre: " + nombre);
		List<Rol> tb = pp.darRolPorNombre(nombre);
		return !tb.isEmpty() ? tb.get(0) : null;
	}
	public Usuario adicionarUsuario(String email,String nombre , long numDoc , String rol , String tipoDoc) 
	{
		log.info("Adicionanda Usuario: " + nombre);
		Usuario user = pp.adicionarUsuario(email,nombre,numDoc,rol,tipoDoc);
		log.info("Adicionando Usuario: " + user);
		return user;
	}
	
	public IPS adicionarIPS(long idEps, String localizacion ,String nombre)
	{
		log.info("Adicionando IPS: " + nombre);
		IPS user = pp.adicionarIPS(idEps ,localizacion,nombre);
		log.info("Adicionando Usuario: " + user);
		return user;
	}
	
	public Medico adicionarMedico(String numeroRegistro, String especialidad ,long idUsuario)
	{
		log.info("Adicionando Medico: " + numeroRegistro);
		Medico user = pp.adicionarMedico(numeroRegistro ,especialidad,idUsuario);
		log.info("Adicionando Medico: " + user);
		return user;
	}
	
	public Afiliado adicionarAfiliado(String estadoSalud,String fechaNacimiento, String recetaActual ,long idO ,long idU ,long idE , long idC)
	{
		log.info("Adicionando Afiliado: " + idU);
		Date fecha = Date.valueOf(fechaNacimiento);
		Afiliado user = pp.adicionarAfiliado(estadoSalud ,fecha,recetaActual,idO,idU,idE,idC);
		log.info("Adicionando Medico: " + user);
		return user;
	}
	public Servicio adicionarServicio(long capacidad ,String nombre , long idIPS)
	{
		log.info("Adicionando Servicio: " + nombre);
		Servicio user = pp.adicionarServicio(capacidad ,nombre,idIPS);
		log.info("Adicionando Servicio: " + user);
		return user;	
	}
	public Afiliado registrarCitaAfiliado(long idCita,long idAfiliado )
	{
		log.info("Adicionando Cita: " + idCita);
		Afiliado user = pp.registrarCitaAfiliado(idCita ,idAfiliado);
		log.info("Adicionando Cita: " + user);
		return user;
	}
	public Afiliado realizarReserva(long asisitio,long idServicio)
	{
		log.info("Reservando Cita: " + idServicio);
		Afiliado user = pp.reservaCita(asisitio , idServicio);
		log.info("Adicionando Cita: " + user);
		return user;
	}
	public Cita registrarAsistencia(long idCita)
	{
		log.info("Reservando Cita: " + idCita);
		Cita user = pp.registrarAsistencia(idCita);
		log.info("Adicionando Cita: " + user);
		return user;
	}
	//RF 2 iteracion 
	public Campaña registrarCampaña(Long idOrganizador , String nombreCampaña , LinkedList<Long> idOrden)
	{
		log.info("adicionandoCampaña: " + nombreCampaña);
		Campaña user = pp.registrarCampaña(idOrganizador, nombreCampaña, idOrden);
		log.info("Adicionando Cita: " + user);
		return user;
	}
	public Campaña cancelarServiciosCampaña(Long idCampaña, Long idOrganizador , String nombreCampaña , LinkedList<Long> idOrden)
	{
		log.info("eliminando Servicios Campaña: " + nombreCampaña);
		Campaña user = pp.cancelarServiciosCampaña(idCampaña,idOrganizador, nombreCampaña, idOrden);
		log.info("Adicionando Cita: " + user);
		return user;
	}
	public void deshabilitarServiciosSalud()
	{
	}
	public void habilitarServiciosSalud()
	{
		
	}
	/*
	 * **************************************************************** 
	 * Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de
	 * Parranderos
	 * 
	 * @return Un arreglo con 23 números que indican el número de tuplas borradas en
	 *         las tablas 
	 */
	public long[] limpiarEPSAndes() {
		log.info("Limpiando la BD de EPSAndes");
		long[] borrrados = pp.limpiarEPSAndes();
		log.info("Limpiando la BD de EPSAndes: Listo!");
		return borrrados;
	}
}
