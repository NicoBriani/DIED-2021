package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Linea;
import util.ConnectionBD;

public class LineaDAO_BD implements LineaDAO {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	private ResultSetMetaData rsm;
	DefaultTableModel dtm;

	@Override
	public Linea nuevaLinea(Linea l) {

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("INSERT INTO linea (nombre_linea, color, estado) VALUES (?, ?, ?)");
			pstm.setString(1, l.getNombre());
			pstm.setString(2, l.getColor());
			pstm.setString(3, l.getEstado());
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

		return l;

	}

	public void llenarTabla(JTable tabla) throws Exception {
		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select nombre_linea ,color, estado from linea");
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

	public Linea editarLinea(Linea l) {
		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("UPDATE linea SET color = ?, estado = ? WHERE nombre_linea = ?");

			pstm.setString(1, l.getColor());
			pstm.setString(2, l.getEstado());
			pstm.setString(3, l.getNombre());
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

		return l;

	}

	public void eliminarLinea(Linea l) {
		con = ConnectionBD.conectar();

		try {
			Class.forName("org.postgresql.Driver");

			pstm = con.prepareStatement("DELETE FROM linea WHERE nombre_linea = ?");
			pstm.setString(1, l.getNombre());
			pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	public List<String> getIdLineas() {

		List<String> lineas = new ArrayList<String>();

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("SELECT nombre_linea FROM linea WHERE estado = ?");
			pstm.setString(1, "Activa");
			rs = pstm.executeQuery();
			while (rs.next()) {
				lineas.add(rs.getString("nombre_linea"));
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

		Collections.sort(lineas);
		return lineas;
	}

	public void llenarTablaParaBusqueda(JTable tabla, String variable, String textCampo) throws Exception {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select * from linea where " + variable + " = ? ");
		pstm.setString(1, textCampo);
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

	public void limpiarTabla(JTable tabla) {

		dtm = (DefaultTableModel) tabla.getModel();

		for (int i = dtm.getRowCount() - 1; i >= 0; i--)
			dtm.removeRow(i);

	}

}
