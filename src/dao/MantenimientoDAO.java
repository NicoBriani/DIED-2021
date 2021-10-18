package dao;

import java.sql.SQLException;

import dominio.Mantenimiento;

public interface MantenimientoDAO {

	public Mantenimiento nuevoMantenimiento(Mantenimiento m, Integer estacion) throws SQLException;
	public void eliminarMantenimiento(Integer est) throws SQLException;
}
