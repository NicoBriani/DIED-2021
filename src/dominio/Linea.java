package dominio;

import enumClasses.EstadoLinea;

public class Linea {

	private String nombre;
	private String color;
	private EstadoLinea estado;

	public Linea(String nom, String col) {
		this.nombre = nom;
		this.color = col;
		this.estado = EstadoLinea.Activa;
	}

	public Linea() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstado() {
		if (this.estado == EstadoLinea.Activa)
			return "Activa";
		else
			return "Inactiva";
	}

	public void setEstado(String l) {

		if (l == "Activa")
			this.estado = EstadoLinea.Activa;
		else
			this.estado = EstadoLinea.Inactiva;
	}

}
