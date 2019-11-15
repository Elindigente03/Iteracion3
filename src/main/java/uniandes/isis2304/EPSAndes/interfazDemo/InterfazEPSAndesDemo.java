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

package uniandes.isis2304.EPSAndes.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.interfazApp.PanelDatos;

import uniandes.isis2304.EPSAndes.negocio.VOGustan;
import uniandes.isis2304.EPSAndes.negocio.VOIPS;
import uniandes.isis2304.EPSAndes.negocio.VOMedico;
import uniandes.isis2304.EPSAndes.negocio.VOOrden;
import uniandes.isis2304.EPSAndes.negocio.VORol;
import uniandes.isis2304.EPSAndes.negocio.VOServicio;
import uniandes.isis2304.EPSAndes.negocio.VOUsuario;
import uniandes.isis2304.EPSAndes.negocio.EPSAndes;
import uniandes.isis2304.EPSAndes.negocio.Rol;
import uniandes.isis2304.EPSAndes.negocio.VOAfiliado;
import uniandes.isis2304.EPSAndes.negocio.VOCampaña;
import uniandes.isis2304.EPSAndes.negocio.VOCita;

/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazEPSAndesDemo extends JFrame implements ActionListener {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazEPSAndesDemo.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json";

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren
	 * utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private EPSAndes EPSAndes;

	/*
	 * **************************************************************** Atributos de
	 * interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/*
	 * **************************************************************** Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazEPSAndesDemo() {
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame();
		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		EPSAndes = new EPSAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
	}

	/*
	 * **************************************************************** Métodos para
	 * la configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicación, a partir de un archivo JSON o
	 * con valores por defecto si hay errores.
	 * 
	 * @param tipo       - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado NULL si hay
	 *         un error en el archivo.
	 */
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		} catch (Exception e) {
			//			e.printStackTrace ();
			log.info("NO se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null,
					"No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame() {
		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuración por defecto");
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuración indicada en el archivo de configuración");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * 
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(JsonArray jsonMenu) {
		// Creación de la barra de menús
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);

				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
	}

	//--------------------------Comienzo de los métodos necesarios para RF1--

	public void registrarRolesUsuario()
	{
		try {
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			boolean errorRoles = false;
			List<Rol> roles= EPSAndes.registrarRolesUsuario();
			if (roles == null) {
				roles = EPSAndes.registrarRolesUsuario();
				errorRoles = true;
			}
			List<VORol> lista = EPSAndes.darVORoles();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Registro de roles";
			resultado += "\n\n************ registrando roles ************ \n";
			if (errorRoles) {
				resultado += "*** Exception registrando los roles !!\n";
				resultado += "*** Es probable que un rol ya existiera y hay restricción de UNICIDAD sobre el nombre del rol\n";
				resultado += "*** Revise el log de parranderos para más detalles\n";
			}
			resultado += "Adicionado los roles \n";
			resultado += "\n\n************ Inserciones Ejecutadas ************ \n";
			resultado += "\n" + listarRoles(lista);

			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	// inicio --------------------------------------------------------------------------------------	
	/**
	 * Demostración de creación, consulta y borrado de Tipos de Bebida Muestra la
	 * traza de la ejecución en el panelDatos
	 * 
	 * Pre: La base de datos está vacía Post: La base de datos está vacía
	 */
	public void registrarUsuarioDemo() 
	{
		try
		{
			String nombreTipo = JOptionPane.showInputDialog(this, "Nombre del Usuario",	"Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
			String email = JOptionPane.showInputDialog(this, "Email del Usuario",	"Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
			String numeroDocumento = JOptionPane.showInputDialog(this, "numero de documento del Usuario",	"Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
			String rol = JOptionPane.showInputDialog(this, "Rol Usuario",	"Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
			String TipoDocumento = JOptionPane.showInputDialog(this, "tipo de documento",	"Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
			long numDoc = Long.parseLong(numeroDocumento);


			VOUsuario u =EPSAndes.adicionarUsuario(email,nombreTipo,numDoc,rol,TipoDocumento);
			if(nombreTipo != null )
			{
				if(u == null)
				{
					throw new Exception("no sepuedo insertar el usuario con nombre: " + nombreTipo);
				}
				String resultado = "en adicionarUsuario\n\n";
				resultado += "usuario adicionado exitosamente";
				resultado += "\n operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}	

			else 
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}


		}catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}


	//final ----------------------------------------------------------------------------------
	//--------------------------final de los métodos necesarios para los RF1--
	/**
	 * Demostración de creación, consulta y borrado de Tipos de Bebida Muestra la
	 * traza de la ejecución en el panelDatos
	 * 
	 * Pre: La base de datos está vacía Post: La base de datos está vacía
	 */
	public void registrarIPS()
	{
		try
		{
			String nombreIPS = JOptionPane.showInputDialog(this, "Nombre de la IPS",	"Adicionar IPS", JOptionPane.QUESTION_MESSAGE);
			String idEps = JOptionPane.showInputDialog(this, "Id de la eps a la que pertenece",	"Adicionar IPS", JOptionPane.QUESTION_MESSAGE);
			String localizacion = JOptionPane.showInputDialog(this, "localizacion de la Ips",	"Adicionar IPS", JOptionPane.QUESTION_MESSAGE);
			long idE = Long.parseLong(idEps);


			VOIPS u =EPSAndes.adicionarIPS(idE,localizacion,nombreIPS);
			if(nombreIPS != null )
			{
				if(u == null)
				{
					throw new Exception("no sepuedo insertar la IPS con nombre: " + nombreIPS);
				}
				String resultado = "en adicionarIPS\n\n";
				resultado += "IPS adicionado exitosamente";
				resultado += "\n operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}	

			else 
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}


		}catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}	




	}


	public void registrarMedico()
	{
		try
		{
			String especialidad = JOptionPane.showInputDialog(this, "Especialidad del Medico",	"Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
			String numeroRegistro = JOptionPane.showInputDialog(this, "numero de registro del medico",	"Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
			String idUsuario = JOptionPane.showInputDialog(this, "numero de usuario del medico",	"Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
			long idU = Long.parseLong(idUsuario);


			VOMedico u =EPSAndes.adicionarMedico(especialidad,numeroRegistro,idU);
			if(numeroRegistro != null )
			{
				if(u == null)
				{
					throw new Exception("no sepuedo insertar el Medico con numero de registro: " + numeroRegistro);
				}
				String resultado = "en adicionarMedico\n\n";
				resultado += "Medico adicionado exitosamente";
				resultado += "\n operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}	

			else 
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}


		}catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void adicionarAfiliado()
	{
		try
		{
			String estadoSalud = JOptionPane.showInputDialog(this, "Estado de salud del afiliado",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String fechaNacimiento = JOptionPane.showInputDialog(this, "fecha de nacimiento",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String recetaActual = JOptionPane.showInputDialog(this, "receta actual del Afiliado",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String idOrden = JOptionPane.showInputDialog(this, "numero de orden",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String idUsuario = JOptionPane.showInputDialog(this, "id de usuario",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String idEps = JOptionPane.showInputDialog(this, "id EPS",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);
			String idCita = JOptionPane.showInputDialog(this, "id Cita",	"Adicionar Afiliado", JOptionPane.QUESTION_MESSAGE);

			long idO = Long.parseLong(idOrden);
			long idU = Long.parseLong(idUsuario);
			long idE = Long.parseLong(idEps);
			long idC = Long.parseLong(idCita);


			VOAfiliado u =EPSAndes.adicionarAfiliado(estadoSalud,fechaNacimiento,recetaActual,idO,idU,idE,idC);
			if(idUsuario != null )
			{
				if(u == null)
				{
					throw new Exception("no sepuedo insertar el Medico con numero de registro: " + idU);
				}
				String resultado = "en adicionarAfiliado\n\n";
				resultado += "afiliado adicionado exitosamente";
				resultado += "\n operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}	

			else 
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}


		}catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void registrarServicioIPS ()
	{
		try {
			String capacidad = JOptionPane.showInputDialog(this, "capacidad del servicio",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog(this, "nombre del servicio",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);
			String idIps = JOptionPane.showInputDialog(this, "id de la IPs que presta el servicio",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);
			long capa = Long.parseLong(capacidad);
			long idI = Long.parseLong(idIps);

			VOServicio u =EPSAndes.adicionarServicio(capa,nombre,idI);
			if(nombre != null )
			{
				if(u == null)
				{
					throw new Exception("no sepuedo insertar el Medico con numero de registro: " + nombre);
				}
				String resultado = "en adicionarServicioIPS\n\n";
				resultado += "Servicio adicionado exitosamente";
				resultado += "\n operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}	

			else 
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}


		}catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

public void registrarCitaafiliado()
{
	//TODO falta parte del medico
	try {
		String idCita = JOptionPane.showInputDialog(this, "id de la Cita",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);
		String idAfiliado = JOptionPane.showInputDialog(this, "id del Afiliado",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);

		long idC = Long.parseLong(idCita);
		long idA = Long.parseLong(idAfiliado);

		VOAfiliado u =EPSAndes.registrarCitaAfiliado(idC, idA);
		if(idCita != null )
		{
			if(u == null)
			{
				throw new Exception("no sepuedo insertar la cita para el afiliado con numero de registro: " + idAfiliado);
			}
			String resultado = "en adicionarOrden\n\n";
			resultado += "orden adicionada exitosamente";
			resultado += "\n operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}	

		else 
		{
			panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
		}


	}catch (Exception e)
	{
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
}
public void realizarReserva()
{

	//TODO disponibilidad
	try {
		String idCita = JOptionPane.showInputDialog(this, "id de la Cita",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);
		String idAfiliado = JOptionPane.showInputDialog(this, "id del Afiliado",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);

		long idC = Long.parseLong(idCita);
		long idA = Long.parseLong(idAfiliado);

		VOAfiliado u =EPSAndes.realizarReserva(idA, idC);
		if(idCita != null )
		{
			if(u == null)
			{
				throw new Exception("no sepuedo insertar la cita para el afiliado con numero de registro: " + idAfiliado);
			}
			String resultado = "en adicionarOrden\n\n";
			resultado += "orden adicionada exitosamente";
			resultado += "\n operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}	

		else 
		{
			panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
		}


	}catch (Exception e)
	{
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
}
public void registrarAsistencia()
{
	try {
		String idCita = JOptionPane.showInputDialog(this, "id de la Cita",	"adicionar Servicio IPS", JOptionPane.QUESTION_MESSAGE);

		long idC = Long.parseLong(idCita);
		

		VOCita u =EPSAndes.registrarAsistencia(idC);
		if(idCita != null )
		{
			if(u == null)
			{
				throw new Exception("no sepuedo comprobar la asistencia de la cita : " + idC);
			}
			String resultado = "en adicionarOrden\n\n";
			resultado += "orden adicionada exitosamente";
			resultado += "\n operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}	

		else 
		{
			panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
		}


	}catch (Exception e)
	{
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
 // rf9
}
//rf 1 it 
public void registrarCampaña()
{
	try {
		String idOrganizador = JOptionPane.showInputDialog(this, "id del Organizador de la campaña",	"adicionar Campaña", JOptionPane.QUESTION_MESSAGE);
		String nombreCampaña = JOptionPane.showInputDialog(this, "nombre de la campaña",	"adicionar Campaña", JOptionPane.QUESTION_MESSAGE);

		LinkedList<Long> idOrden = new LinkedList<>();
        JOptionPane.showConfirmDialog(this, "ahora va a insertar los id de lso servicios que desea agregar a la campaña si quiere terminar esta captura de datos escriba X");
        boolean x = false;
        while (!x)
        {
    		String id = JOptionPane.showInputDialog(this, "id del servicio",	"adicionar Campaña", JOptionPane.QUESTION_MESSAGE);
    		if(id.equalsIgnoreCase("X") )
    		{
    		long idO = Long.parseLong(id);
    		idOrden.add(idO);
    		}
    		else 
    		{
    		   x= true;
    		}
        }
        long idOC = Long.parseLong(idOrganizador);
		VOCampaña u =EPSAndes.registrarCampaña(idOC, nombreCampaña, idOrden);
		if(nombreCampaña != null )
		{
			if(u == null)
			{
				throw new Exception("no sepuedo agregar la campaña : " + nombreCampaña);
			}
			String resultado = "en adicionarOrden\n\n";
			resultado += "orden adicionada exitosamente";
			resultado += "\n operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}	

		else 
		{
			panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
		}


	}catch (Exception e)
	{
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
}
public void cancelarServiciosCampaña()
{
	try {
		String idCampaña = JOptionPane.showInputDialog(this, "id  de la campaña",	"cancelar Servicios Campaña", JOptionPane.QUESTION_MESSAGE);

		String idOrganizador = JOptionPane.showInputDialog(this, "id del Organizador de la campaña",	"cancelar Servicios Campaña", JOptionPane.QUESTION_MESSAGE);
		
		String nombreCampaña = JOptionPane.showInputDialog(this, "nombre de la campaña",	"cancelar Servicios Campaña", JOptionPane.QUESTION_MESSAGE);

		LinkedList<Long> idOrden = new LinkedList<>();
        JOptionPane.showConfirmDialog(this, "ahora va a insertar los id de los servicios que desea cancelar a la campaña si quiere terminar esta captura de datos escriba X");
        boolean x = false;
        while (!x)
        {
    		String id = JOptionPane.showInputDialog(this, "id del servicio",	"cancelar Servicios Campaña", JOptionPane.QUESTION_MESSAGE);
    		if(id.equalsIgnoreCase("X") )
    		{
    		long idO = Long.parseLong(id);
    		idOrden.add(idO);
    		}
    		else 
    		{
    		   x= true;
    		}
        }
        long idOC = Long.parseLong(idOrganizador);
        long idC =Long.parseLong(idCampaña);
		VOCampaña u =EPSAndes.cancelarServiciosCampaña(idC,idOC, nombreCampaña, idOrden);
		if(nombreCampaña != null )
		{
			if(u == null)
			{
				throw new Exception("no se puedieron eliminar los servicios de la campaña : " + nombreCampaña);
			}
			String resultado = "en adicionarOrden\n\n";
			resultado += "orden adicionada exitosamente";
			resultado += "\n operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}	

		else 
		{
			panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
		}


	}catch (Exception e)
	{
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
}
public void deshabilitarServiciosSalud()
{
}
public void habilitarServiciosSalud()
{
	
}



	/*
	 * **************************************************************** Métodos
	 * administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos() {
		mostrarArchivo("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus() {
		mostrarArchivo("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogParranderos() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogDatanucleus() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de
	 * parranderos Muestra en el panel de datos el número de tuplas eliminadas de
	 * cada tabla
	 */
	public void limpiarBD() {
		try {
			// Ejecución de la demo y recolección de los resultados
			long eliminados[] = EPSAndes.limpiarEPSAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados[0] + " administrador eliminados\n";
			resultado += eliminados[1] + " afiliado eliminados\n";
			resultado += eliminados[2] + " cita eliminados\n";
			resultado += eliminados[3] + " consulta eliminadas\n";
			resultado += eliminados[4] + " control eliminados\n";
			resultado += eliminados[5] + " EPS eliminados\n";
			resultado += eliminados[6] + " Examen diagnostico eliminados\n";
			resultado += eliminados[7] + " Gerente eliminados\n";
			resultado += eliminados[8] + " Hozpitalizacion eliminados\n";
			resultado += eliminados[9] + " IPS eliminados\n";
			resultado += eliminados[10] + " Medico eliminados\n";
			resultado += eliminados[11] + " Orden eliminados\n";
			resultado += eliminados[12] + " Procedimiento Especializado eliminados\n";
			resultado += eliminados[13] + " Recepcionista eliminados\n";
			resultado += eliminados[14] + " Recepcion eliminados\n";
			resultado += eliminados[15] + " Rol eliminados\n";
			resultado += eliminados[16] + " Servicio eliminados\n";
			resultado += eliminados[17] + " Terapia eliminados\n";
			resultado += eliminados[18] + " Trabajan eliminados\n";
			resultado += eliminados[19] + " Urgencia eliminados\n";
			resultado += eliminados[20] + " Usuario eliminados\n";

			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral() {
		mostrarArchivo("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual() {
		mostrarArchivo("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD() {
		mostrarArchivo("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD() {
		mostrarArchivo("data/EsquemaParranderos.txt");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef() {
		mostrarArchivo("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc() {
		mostrarArchivo("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe() {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
	}

	/*
	 * **************************************************************** Métodos
	 * privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida:
	 * una línea por cada tipo de bebida
	 * 
	 * @param lista - La lista con los Roles de bebida
	 * @return La cadena con una líea para cada tipo de bebida recibido
	 */
	private String listarRoles(List<VORol> lista) {
		String resp = "Los roles existentes son:\n";
		int i = 1;
		for (VORol tb : lista) {
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e,
	 * haciendo énfasis en las excepcionsde JDO
	 * 
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es
	 *         javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * 
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) {
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * 
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
			bw.write("");
			bw.close();
			return true;
		} catch (IOException e) {
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * 
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo(String nombreArchivo) {
		try {
			Desktop.getDesktop().open(new File(nombreArchivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * **************************************************************** Métodos de
	 * la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos
	 * de negocio Invoca al método correspondiente según el evento recibido
	 * 
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		String evento = pEvento.getActionCommand();
		try {
			Method req = InterfazEPSAndesDemo.class.getMethod(evento);
			req.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * **************************************************************** Programa
	 * principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * 
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main(String[] args) {
		try {

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazEPSAndesDemo interfaz = new InterfazEPSAndesDemo();
			interfaz.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
