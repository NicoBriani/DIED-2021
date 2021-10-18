package dominio;

import java.util.Date;

public class Mantenimiento {

	private Integer id;
	private Date inicio;
	private Date fin;
	private String observaciones;

	public Mantenimiento(Integer id, Date inicio, Date fin, String observaciones) {
		super();
		this.id = id;
		this.inicio = inicio;
		this.fin = fin;
		this.observaciones = observaciones;
	}

	public Mantenimiento() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date fechaInicio) {
		this.inicio = fechaInicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fechaFin) {
		this.fin = fechaFin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
