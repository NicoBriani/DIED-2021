package dao;

import java.sql.SQLException;

import dominio.Ruta;
import dominio.Trayecto;

public class TrayectoDAO {

	TrayectoDAO_BD tdao = new TrayectoDAO_BD();
	
	public void gestionarTrayecto (Ruta r) throws SQLException {
		
		Trayecto trayecto = new Trayecto();
		trayecto.setIdLinea(r.getLineaId());
		
		trayecto = tdao.getTrayecto(trayecto);
		
		if(trayecto.getId() == null)
			cargarDatos(trayecto,r);
			
		else completarDatos(trayecto,r);
		
	}
	
	public void completarDatos(Trayecto t, Ruta r) {
		
		t.setCostoT(t.getCostoT()+r.getCosto());
		t.setDistanciaT(t.getDistanciaT()+r.getDistancia());
		t.setDuracionT(t.getDuracionT()+r.getDuracionViaje());
		t.setDestino(r.getDestino());
		
		tdao.editarTrayecto(t);
	}
	
	public void cargarDatos(Trayecto t, Ruta r) throws SQLException {
		
		t.setId(tdao.generarIdTrayecto());
		t.setOrigen(r.getOrigen());
		t.setDestino(r.getDestino());
		t.setCostoT(r.getCosto());
		t.setDistanciaT(r.getDistancia());
		t.setDuracionT(r.getDuracionViaje());
		t.setIdLinea(r.getLineaId());
		
		tdao.nuevoTrayecto(t);
	}
}
