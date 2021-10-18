package dominio;


public class Ruta {

	private Integer id;
	private Estacion origen;
	private Estacion destino;
	private Double distancia;
	private Integer duracionViaje;
	private Integer cantMaxPasajeros;
	private String estado;
	private Double costo;
	private String lineaId;
	long[] datos = new long[1];

	public Ruta(Integer id, Estacion origen, Estacion destino, Double distancia, Integer duracionViaje,
			Integer cantMaxPasajeros, String estado, Double costo, String lineaId) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.duracionViaje = duracionViaje;
		this.cantMaxPasajeros = cantMaxPasajeros;
		this.estado = estado;
		this.costo = costo;
		this.lineaId = lineaId;
	}

	public Ruta(Estacion destino, Integer maxPasajeros) {
		super();
		this.destino = destino;
		this.cantMaxPasajeros=maxPasajeros;
		this.datos[0]=maxPasajeros;
	}
	public Ruta() {
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

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getDuracionViaje() {
		return duracionViaje;
	}

	public void setDuracionViaje(Integer duracionViaje) {
		this.duracionViaje = duracionViaje;
	}

	public Integer getCantMaxPasajeros() {
		return cantMaxPasajeros;
	}

	public void setCantMaxPasajeros(Integer cantMaxPasajeros) {
		this.cantMaxPasajeros = cantMaxPasajeros;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getLineaId() {
		return lineaId;
	}

	public void setLineaId(String lineaId) {
		this.lineaId = lineaId;
	}

	public void changeFlow(int delta) {
		this.cantMaxPasajeros += delta;
		this.datos[0] += delta;
	}

}
