package dominio;

public class Boleto {

	private Integer nroBoleto;
	private String correo;
	private String nombre;
	private String fecha;
	private Estacion origen;
	private Estacion destino;
	private Trayecto camino;
	private Double costo;

	public Boleto() {

	}

	public Boleto(Integer nroBoleto, String correo, String nombre, String fecha, Estacion origen, Estacion destino,
			Trayecto camino, Double costo) {
		super();
		this.nroBoleto = nroBoleto;
		this.correo = correo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
		this.costo = costo;
	}

	public Integer getNroBoleto() {
		return nroBoleto;
	}

	public void setNroBoleto(Integer nroBoleto) {
		this.nroBoleto = nroBoleto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String date) {

		this.fecha = date;
	}

	public Integer getOrigen() {
		return origen.getId();
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Integer getDestino() {
		return destino.getId();
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Trayecto getCamino() {
		return camino;
	}

	public void setCamino(Trayecto camino) {
		this.camino = camino;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

}
