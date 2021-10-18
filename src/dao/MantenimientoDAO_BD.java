package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionBD;
import dominio.Mantenimiento;

public class MantenimientoDAO_BD implements MantenimientoDAO {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;

	public Mantenimiento nuevoMantenimiento(Mantenimiento m, Integer estacion) throws SQLException {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement(
				"INSERT INTO mantenimiento (id_mantenimiento, inicio, fin, observaciones, id_estacion) VALUES (?, ?, ?, ?, ?)");
		pstm.setInt(1, m.getId());
		pstm.setDate(2, (Date) m.getInicio());
		pstm.setDate(3, (Date)m.getFin());
		pstm.setString(4, m.getObservaciones());
		pstm.setInt(5, estacion);

		pstm.executeUpdate();

		return m;
	}
	
	public void eliminarMantenimiento(Integer est) throws SQLException {
		con = ConnectionBD.conectar();
		

		try {
			
			pstm = con.prepareStatement("DELETE FROM mantenimiento WHERE id_estacion = ?");
			pstm.setInt(1, est);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(con != null) try { con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}

}
