package uniandes.isis2304.EPSAndes.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public long adicionarCitaAfiliada(PersistenceManager pm, long id,long citaId ,long afiliadoId, long idServicio, Date dia, int efectuado )
	{
		Query q = pm.newQuery(SQL , "INSERT INTO" + pp.darTablaCita_afiliado() + "(id, citaId, afiliadoId, idServicio, dia, efectuado)" );
		q.setParameters(id, citaId, afiliadoId, idServicio, dia, efectuado);
		 return (long) q.executeUnique();
	}
	public long cambiarEstadoAEfectuada(PersistenceManager pm, long id) {
		//Query q = pm.newQuery()
		return 0;
	}
	public long eliminarCitaAfiliada(PersistenceManager pm,long id )
	{
		Query q = pm.newQuery(SQL , "DELETE FROM" + pp.darTablaCita_afiliado() + "where id = ?" );
		q.setParameters(id);
		 return (long) q.executeUnique();
	}
	
	
	public Cita_afiliado darCitaAfiliadaPorId(PersistenceManager pm, long idOrden) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCita_afiliado() + " WHERE id = ?");
		
		q.setParameters(idOrden);
		return (Cita_afiliado) q.executeUnique();
		}
	public List<Integer> darIndicesServicios(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT count(*) FROM "+ pp.darTablaCita_afiliado()+ " GROUP BY idServicio ORDER BY idServicio");
		
		return (List<Integer>) q.executeList();
	}
	public long datIdCitaPrestada(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "SELECT citaId FROM "+pp.darTablaCita_afiliado()+ " WHERE id =?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	public ArrayList<Object> darServiciosDeAfiliado(PersistenceManager pm, long idAfiliado){
		Query q = pm.newQuery(SQL, "SELECT idServicio FROM " +pp.darTablaCita_afiliado()+ " WHERE afiliadoId=?");
		
		q.setParameters(idAfiliado);
		return (ArrayList<Object>) q.executeList();
	}
	public ArrayList<Object> darServiciosPorRangoFecha(PersistenceManager pm, Date fecha1, Date fecha2){
		Query q = pm.newQuery(SQL, "SELECT isServicio FROM "+ pp.darTablaCita_afiliado()+ " WHERE dia BETWEEN ? AND ?");
		q.setResultClass(List.class);
		q.setParameters(fecha1,fecha2);
		return (ArrayList<Object>) q.executeList();
	}
	public ArrayList<Date> darDemandaSemanal(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT maximo FROM (SELECT MAX(dia) maximo, count(*) conteo  FROM "+pp.darTablaCita_afiliado()+" GROUP BY TO_CHAR(dia, ‘IW’) ORDER BY conteo DESC) WHERE ROWNUM <= 5");
		
		return (ArrayList<Date>) q.executeList();
		
	}
	public ArrayList<Date> darDemandaMensual(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT maximo FROM (SELECT MAX(dia) maximo, count(*) conteo  FROM "+pp.darTablaCita_afiliado()+" GROUP BY TO_CHAR(dia, ‘MM/YY’) ORDER BY conteo DESC) WHERE ROWNUM <= 5");
		
		return (ArrayList<Date>) q.executeList();
	}
	public ArrayList<Date> darMenorDemandaSemanal(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT maximo FROM (SELECT MAX(dia) maximo, count(*) conteo  FROM "+pp.darTablaCita_afiliado()+" GROUP BY TO_CHAR(dia, ‘IW’) ORDER BY conteo ) WHERE ROWNUM <= 5");
		
		return (ArrayList<Date>) q.executeList();
		
	}
	public ArrayList<Date> darMenorDemandaMensual(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT maximo FROM (SELECT MAX(dia) maximo, count(*) conteo  FROM "+pp.darTablaCita_afiliado()+" GROUP BY TO_CHAR(dia, ‘MM/YY’) ORDER BY conteo ) WHERE ROWNUM <= 5");
		
		return (ArrayList<Date>) q.executeList();
	}
	public ArrayList<Date> darMayorActividad(PersistenceManager pm){
		Query q = pm.newQuery(SQL,"SELECT maximo FROM (SELECT MAX(dia) maximo, count(*) conteo  FROM "+pp.darTablaCita_afiliado()+" WHERE efectuado=1 GROUP BY TO_CHAR(dia, ‘IW’) ORDER BY conteo DESC) WHERE ROWNUM <= 5");
		
		return (ArrayList<Date>) q.executeList();
	}
}
