package dao;


import java.sql.Date;
import java.util.List;
import javax.swing.JTable;


import dominio.Estacion;

public interface EstacionDAO {

	public Estacion nuevaEstacion (Estacion e);
	public void llenarTabla(JTable tabla) throws Exception;
	public Estacion editarEstacion (Estacion e);
	public void eliminarEstacion (Estacion e);
	public List<Integer> getIdEstaciones();
	public Estacion getEstacion (Estacion estacion);
	public void llenarTablaParaBusqueda(JTable tabla, String variable, String textCampo) throws Exception;
	public void limpiarTabla (JTable tabla);
	public List<Estacion> getEstaciones();
	public Date getUltimoMantenimiento (Integer id);
}
