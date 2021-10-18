package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import dominio.Boleto;
import util.ConnectionBD;

public class BoletoDAO_BD implements BoletoDAO {

	static Connection con = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	DefaultTableModel dtm;

	@Override
	public Boleto nuevoBoleto(Boleto b) {

		con = ConnectionBD.conectar();

		try {

			pstm = con.prepareStatement("INSERT INTO "
					+ "boleto (nroboleto, correocliente, nombrecliente, fecha, origen, destino, costo ) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pstm.setInt(1, b.getNroBoleto());
			pstm.setString(2, b.getCorreo());
			pstm.setString(3, b.getNombre());
			pstm.setString(4, b.getFecha());
			pstm.setInt(5, b.getOrigen());
			pstm.setInt(6, b.getDestino());
			pstm.setDouble(7, b.getCosto());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

}
