package uniandes.isis2304.EPSAndes.negocio;

public class ExamenDiagnostico implements VOExamenDiagnostico{
	
	private long id;
	private String muestras;
	private String resultados;
	
	/**
	 * @param id
	 * @param capacidad
	 * @param horariosAtencion
	 * @param muestras
	 * @param resultados
	 */
	public ExamenDiagnostico(long id,  String muestras, String resultados) {
		
		this.id = id;
		this.muestras= muestras;
		this.resultados = resultados;
		
	}
	public long getID()
	{
	  return id;
	}

	/**
	 * @return the muestras
	 */
	public String getMuestras() {
		return muestras;
	}

	/**
	 * @return the resultados
	 */
	public String getResultados() {
		return resultados;
	}

	/**
	 * @param muestras the muestras to set
	 */
	public void setMuestras(String muestras) {
		this.muestras = muestras;
	}

	/**
	 * @param resultados the resultados to set
	 */
	public void setResultados(String resultados) {
		this.resultados = resultados;
	}

}
