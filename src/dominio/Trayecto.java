package dominio;

public class Trayecto {

	private Integer id;
	private Estacion origen;
	private Estacion destino;
	private Double distanciaT;
	private Integer duracionT;
	private Double costoT;
	private String idLinea;

	public Trayecto(Integer id, Estacion origen, Estacion destino, Double distanciaT, Integer duracionT, Double costoT,
			String idLinea) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.distanciaT = distanciaT;
		this.duracionT = duracionT;
		this.costoT = costoT;
		this.idLinea = idLinea;
	}

	public Trayecto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Double getDistanciaT() {
		return distanciaT;
	}

	public void setDistanciaT(Double distanciaT) {
		this.distanciaT = distanciaT;
	}

	public Integer getDuracionT() {
		return duracionT;
	}

	public void setDuracionT(Integer duracionT) {
		this.duracionT = duracionT;
	}

	public Double getCostoT() {
		return costoT;
	}

	public void setCostoT(Double costoT) {
		this.costoT = costoT;
	}

	public String getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(String idLinea) {
		this.idLinea = idLinea;
	}

}
