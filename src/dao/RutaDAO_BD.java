package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Estacion;
import dominio.Ruta;
import util.ConnectionBD;

public class RutaDAO_BD implements RutaDAO {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	private ResultSetMetaData rsm;
	DefaultTableModel dtm;

	@Override

	public Integer generarIdRuta() throws SQLException {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select id_ruta from ruta ");
		rs = pstm.executeQuery();
		rsm = rs.getMetaData();
		ArrayList<Object[]> idsRuta = new ArrayList<>();
		while (rs.next()) {
			Object[] filas = new Object[rsm.getColumnCount()];

			for (int i = 0; i < filas.length; i++)
				filas[i] = rs.getObject(i + 1);

			idsRuta.add(filas);
		}
		return idsRuta.size() + 1;
	}

	public Ruta nuevaRuta(Ruta ruta) {
		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement(
					"INSERT INTO ruta (id_ruta, origen, destino, distanciakm, duraciontiempominutos, capacidad, estado, costoenpesos, linea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstm.setInt(1, ruta.getId());
			pstm.setInt(2, ruta.getOrigen().getId());
			pstm.setInt(3, ruta.getDestino().getId());
			pstm.setDouble(4, ruta.getDistancia());
			pstm.setInt(5, ruta.getDuracionViaje());
			pstm.setInt(6, ruta.getCantMaxPasajeros());
			pstm.setString(7, ruta.getEstado());
			pstm.setDouble(8, ruta.getCosto());
			pstm.setString(9, ruta.getLineaId());
			pstm.executeUpdate();
			System.out.println("Datos cargados");
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

		return ruta;
	}

	public void llenarTabla(JTable tabla, String idLinea) throws Exception {

		con = ConnectionBD.conectar();

		pstm = con.prepareStatement("select origen, destino from ruta where linea = ?");
		pstm.setString(1, idLinea);
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

	public List<Integer> getIdEstaciones(String lineaS) {

		List<Integer> estaciones = new ArrayList<Integer>();

		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("SELECT origen FROM ruta WHERE linea = ?");
			pstm.setString(1, lineaS);
			rs = pstm.executeQuery();
			while (rs.next()) {
				estaciones.add(rs.getInt("origen"));
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

	public List<Ruta> buscarTodas() {

		List<Ruta> listaR = new ArrayList<Ruta>();

		con = ConnectionBD.conectar();

		EstacionDAO edao = new EstacionDAO_BD();

		try {
			pstm = con.prepareStatement(
					"SELECT id_ruta, origen, destino, distanciakm, duraciontiempominutos, capacidad, estado, costoenpesos, linea from ruta");
			rs = pstm.executeQuery();
			while (rs.next()) {
				Ruta ruta = new Ruta();
				Estacion origen = new Estacion();
				Estacion destino = new Estacion();

				origen.setId(rs.getInt("origen"));
				destino.setId(rs.getInt("destino"));
				ruta.setOrigen(edao.getEstacion(origen));
				ruta.setDestino(edao.getEstacion(destino));
				ruta.setDistancia(rs.getDouble("distanciakm"));
				ruta.setDuracionViaje(rs.getInt("duraciontiempominutos"));
				ruta.setCantMaxPasajeros(rs.getInt("capacidad"));
				ruta.setEstado(rs.getString("estado"));
				ruta.setCosto(rs.getDouble("costoenpesos"));
				ruta.setLineaId(rs.getString("linea"));
				listaR.add(ruta);
			}
			System.out.println("Datos cargados");
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

		return listaR;

	}
	
public void eliminarRutaPorOrigen(Integer ori) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			pstm = con.prepareStatement("DELETE FROM ruta WHERE origen = ?");
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
	
	public void eliminarRutaPorDestino(Integer des) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			
			pstm = con.prepareStatement("DELETE FROM ruta WHERE destino = ?");
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
	
public void eliminarRutaPorLinea(String linea) throws SQLException {
		
		con = ConnectionBD.conectar();

		try {
			
			pstm = con.prepareStatement("DELETE FROM ruta WHERE linea= ?");
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
