package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Estacion;
import gui.Estaciones;
import util.ConnectionBD;

public class EstacionDAO_BD implements EstacionDAO {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	private ResultSetMetaData rsm;
	DefaultTableModel dtm;

	@Override
	public Estacion nuevaEstacion(Estacion e) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("INSERT INTO "
					+ "estacion (id_estacion, nombre_estacion, apertura, cierre, estado) VALUES (?, ?, ?, ?, ?)");
			pstm.setInt(1, e.getId());
			pstm.setString(2, e.getNombre());
			pstm.setString(3, e.getApertura());
			pstm.setString(4, e.getCierre());
			pstm.setString(5, e.getEstado());
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

		return e;

	}

	@Override
	public void llenarTabla(JTable tabla) throws Exception {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select id_estacion ,nombre_estacion,apertura, cierre, estado from estacion");
		rs = pstm.executeQuery();
		rsm = rs.getMetaData();
		ArrayList<Object[]> datos = new ArrayList<>();
		while (rs.next()) {
			Object[] filas = new Object[rsm.getColumnCount()];
			for (int i = 0; i < filas.length; i++) {
				filas[i] = rs.getObject(i + 1);

			}
			datos.add(filas);
		}
		dtm = (DefaultTableModel) tabla.getModel();
		for (int i = 0; i < datos.size(); i++) {
			dtm.addRow(datos.get(i));
		}
	}

	public Estacion editarEstacion(Estacion e) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"UPDATE estacion SET nombre_estacion = ?, apertura = ?, cierre = ?, estado = ?, ultimoMantenimiento = ? WHERE id_estacion = ?");

			pstm.setString(1, e.getNombre());
			pstm.setString(2, e.getApertura());
			pstm.setString(3, e.getCierre());
			pstm.setString(4, e.getEstado());
			pstm.setInt(6, e.getId());
			pstm.setDate(5, (Date) e.getUltimoMantenimiento());
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

		return e;
	}

	public void eliminarEstacion(Estacion est) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("DELETE FROM estacion WHERE id_estacion = ?");
			pstm.setInt(1, est.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}

	public List<Integer> getIdEstaciones() {

		List<Integer> estaciones = new ArrayList<Integer>();

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("SELECT id_estacion FROM estacion WHERE estado = ?");
			pstm.setString(1, "Operativa");
			rs = pstm.executeQuery();
			while (rs.next()) {
				estaciones.add(rs.getInt("id_estacion"));
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

		Collections.sort(estaciones);
		return estaciones;
	}

	public Estacion getEstacion(Estacion estacion) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"SELECT id_estacion ,nombre_estacion,apertura, cierre, estado from estacion WHERE id_estacion = ?");
			pstm.setInt(1, estacion.getId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				estacion.setApertura(rs.getString("apertura"));
				estacion.setCierre(rs.getString("cierre"));
				estacion.setNombre(rs.getString("nombre_estacion"));
				estacion.setEstado(rs.getString("estado"));
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

		return estacion;

	}

	public void llenarTablaParaBusqueda(JTable tabla, String variable, String textCampo) throws Exception {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select id_estacion ,nombre_estacion,apertura, cierre, estado from estacion where "
				+ variable + " = ? ");

		if (variable == "id_estacion")
			pstm.setInt(1, Integer.parseInt(textCampo));
		else
			pstm.setString(1, textCampo);

		rs = pstm.executeQuery();
		rsm = rs.getMetaData();
		ArrayList<Object[]> datos = new ArrayList<>();
		while (rs.next()) {
			Object[] filas = new Object[rsm.getColumnCount()];
			for (int i = 0; i < filas.length; i++) 
				filas[i] = rs.getObject(i + 1);

			
			datos.add(filas);
		}
		dtm = (DefaultTableModel) tabla.getModel();
		for (int i = 0; i < datos.size(); i++) 
			dtm.addRow(datos.get(i));

		
	}

	public void limpiarTabla(JTable tabla) {

		dtm = (DefaultTableModel) tabla.getModel();

		for (int i = dtm.getRowCount() - 1; i >= 0; i--)
			dtm.removeRow(i);

	}
	
	
	public List<Estacion> getEstaciones() {

		List<Estacion> estaciones = new ArrayList<Estacion>();

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("SELECT * FROM estacion ");
			rs = pstm.executeQuery();
			while (rs.next()) {
				Estacion e = new Estacion();
				e.setApertura(rs.getString("apertura"));
				e.setCierre(rs.getString("cierre"));
				e.setEstado(rs.getString("estado"));
				e.setId(rs.getInt("id_estacion"));
				e.setNombre(rs.getString("nombre_estacion"));
				e.setUltimoMantenimiento(rs.getDate("ultimomantenimiento"));
				estaciones.add(e);
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
		
		return estaciones;
	}
	
	public Date getUltimoMantenimiento (Integer id) {
		
		con = ConnectionBD.conectar();
		Date retorno = null;
		try {
			pstm = con.prepareStatement("SELECT ultimomantenimiento FROM estacion WHERE id_estacion = ?");
			pstm.setInt(1,id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				retorno = (rs.getDate("ultimomantenimiento"));
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

		return retorno;
	
	}
}
