package dominio;

import java.util.Date;

import enumClasses.EstadoEstacion;

public class Estacion {

	private Integer id;
	private String nombre;
	private String apertura;
	private String cierre;
	private EstadoEstacion estado;
	Date ultimoMantenimiento = null;
	
	public Estacion() {

	}

	public Estacion(Integer id, String nombre, String apertura, String cierre, EstadoEstacion estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apertura = apertura;
		this.cierre = cierre;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApertura() {
		return apertura;
	}

	public void setApertura(String apertura) {
		this.apertura = apertura;
	}

	public String getCierre() {
		return cierre;
	}

	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

	public String getEstado() {

		if (this.estado == EstadoEstacion.Mantenimiento)
			return "Mantenimiento";
		else
			return "Operativa";

	}

	public void setEstado(String e) {

		if (e == "Operativa")
			this.estado = EstadoEstacion.Operativa;
		if (e == "Mantenimiento")
			this.estado = EstadoEstacion.Mantenimiento;

	}

	public static int compare(Estacion x, Estacion y) {
		
		return (x.ultimoMantenimiento.compareTo(y.ultimoMantenimiento));
		
	}
	
	public Date getUltimoMantenimiento() {
		return this.ultimoMantenimiento;
	}

	public void setUltimoMantenimiento(Date ultimoMantenimiento) {
		this.ultimoMantenimiento = ultimoMantenimiento;
	}
	
	
}
