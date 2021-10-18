package dao;

import java.util.List;

import javax.swing.JTable;

import dominio.Linea;

public interface LineaDAO {

	public Linea nuevaLinea (Linea l);
	public void llenarTabla(JTable tabla) throws Exception;
	public Linea editarLinea (Linea l);
	public void eliminarLinea (Linea l);
	public void llenarTablaParaBusqueda(JTable tabla, String variable, String textCampo) throws Exception;
	public void limpiarTabla (JTable tabla);
	public List<String> getIdLineas();
	
}
