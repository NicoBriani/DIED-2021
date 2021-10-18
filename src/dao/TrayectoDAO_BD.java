package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dominio.Estacion;
import dominio.Trayecto;
import util.ConnectionBD;

public class TrayectoDAO_BD {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	private ResultSetMetaData rsm;
	DefaultTableModel dtm;
	Estacion origen = new Estacion();
	Estacion destino = new Estacion();

	public Trayecto getTrayecto(Trayecto trayecto) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"SELECT id_trayecto, origen, destino, distanciat, duraciont, costot, linea from trayecto WHERE linea = ?");
			pstm.setString(1, trayecto.getIdLinea());
			rs = pstm.executeQuery();
			while (rs.next()) {
				origen.setId(rs.getInt("origen"));
				destino.setId(rs.getInt("destino"));

				trayecto.setId(rs.getInt("id_trayecto"));
				trayecto.setOrigen(origen);
				trayecto.setDestino(destino);
				trayecto.setCostoT(rs.getDouble("costot"));
				trayecto.setDistanciaT(rs.getDouble("distanciat"));
				trayecto.setDuracionT(rs.getInt("duraciont"));
				trayecto.setIdLinea(rs.getString("linea"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return trayecto;

	}

	public Integer generarIdTrayecto() throws SQLException {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select id_trayecto from trayecto ");
		rs = pstm.executeQuery();
		rsm = rs.getMetaData();
		ArrayList<Object[]> idsTrayectos = new ArrayList<>();
		while (rs.next()) {
			Object[] filas = new Object[rsm.getColumnCount()];

			for (int i = 0; i < filas.length; i++)
				filas[i] = rs.getObject(i + 1);

			idsTrayectos.add(filas);
		}
		return idsTrayectos.size() + 1;
	}

	public Trayecto nuevoTrayecto(Trayecto t) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"INSERT INTO trayecto (id_trayecto, origen, destino, distanciat, duraciont, costot, linea) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pstm.setInt(1, t.getId());
			pstm.setInt(2, t.getOrigen().getId());
			pstm.setInt(3, t.getDestino().getId());
			pstm.setDouble(4, t.getDistanciaT());
			pstm.setInt(5, t.getDuracionT());
			pstm.setDouble(6, t.getCostoT());
			pstm.setString(7, t.getIdLinea());

			pstm.executeUpdate();
			System.out.println("Datos cargados");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (pstm != null)
					pstm.close();

				if (con != null)
					con.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return t;

	}

	public Trayecto editarTrayecto(Trayecto t) {
		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"UPDATE trayecto SET destino = ?, distanciat = ?, duraciont = ?, costot = ? WHERE id_trayecto = ?");

			pstm.setInt(1, t.getDestino().getId());
			pstm.setDouble(2, t.getDistanciaT());
			pstm.setInt(3, t.getDuracionT());
			pstm.setDouble(4, t.getCostoT());
			pstm.setInt(5, t.getId());

			pstm.executeUpdate();
			System.out.println("Datos cargados");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return t;

	}

	public Integer buscarTrayectoDuracion(String origen, String destino) {

		Integer trayecto = 100;

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"select MIN(duraciont) FROM (SELECT duraciont from trayecto WHERE origen = ? and destino = ?) as Menor");

			pstm.setInt(1, Integer.parseInt(origen));
			pstm.setInt(2, Integer.parseInt(destino));
			rs = pstm.executeQuery();
			while (rs.next()) {
				trayecto = rs.getInt("MIN");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return trayecto;
	}

	public Double buscarTrayectoCosto(String origen, String destino) {

		Double trayecto = 100.0;

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"select MIN(costot) FROM (SELECT costot from trayecto WHERE origen = ? and destino = ?) as Menor");

			pstm.setInt(1, Integer.parseInt(origen));
			pstm.setInt(2, Integer.parseInt(destino));
			rs = pstm.executeQuery();
			while (rs.next()) {
				trayecto = rs.getDouble("MIN");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return trayecto;
	}

	public Double buscarTrayectoDistancia(String origen, String destino) {

		Double trayecto = 100.0;

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"select MIN(distanciat) FROM (SELECT distanciat from trayecto WHERE origen = ? and destino = ?) as Menor");

			pstm.setInt(1, Integer.parseInt(origen));
			pstm.setInt(2, Integer.parseInt(destino));
			rs = pstm.executeQuery();
			while (rs.next()) {
				trayecto = rs.getDouble("MIN");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return trayecto;
	}
	
	
public void eliminarTPorOrigen(Integer ori) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("DELETE FROM trayecto WHERE origen = ?");
			pstm.setInt(1, ori);
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
	
	public void eliminarTPorDestino(Integer des) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			
			pstm = con.prepareStatement("DELETE FROM trayecto WHERE destino = ?");
			pstm.setInt(1, des);
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
	
	
public void eliminarTPorLinea(String linea) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			
			pstm = con.prepareStatement("DELETE FROM trayecto WHERE linea = ?");
			pstm.setString(1, linea);
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
