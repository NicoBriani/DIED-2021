package dao;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;

import dominio.Ruta;

public interface RutaDAO {
	
	public Integer generarIdRuta() throws SQLException;
	public Ruta nuevaRuta(Ruta ruta);
	public void llenarTabla(JTable tabla, String idLinea) throws Exception;
	public void limpiarTabla (JTable tabla);
	public List<Integer> getIdEstaciones(String lineaS);
	public List<Ruta> buscarTodas();
	public void eliminarRutaPorOrigen(Integer ori) throws SQLException;
	public void eliminarRutaPorDestino(Integer des) throws SQLException;
	public void eliminarRutaPorLinea(String linea) throws SQLException;
}
