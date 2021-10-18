package estructura;

import java.util.ArrayList;
import java.util.List;

import dao.RutaDAO;
import dao.RutaDAO_BD;
import dominio.Ruta;


public class Grafo {

	public ArrayList<ArrayList<Ruta>> crearGrafo(){
		
		
		RutaDAO rdao = new RutaDAO_BD();
		List<Ruta> rutas = rdao.buscarTodas();
		int N = 1; 
		for(Ruta r : rutas) {
			N = Math.max(N, r.getOrigen().getId()+1);
			N = Math.max(N, r.getDestino().getId()+1);
		}
		ArrayList<ArrayList<Ruta>> grafo = new ArrayList<ArrayList<Ruta>>();
		
		//for(int i=0; i<rdao.buscarTodas().size(); i++) 
			grafo.add((ArrayList<Ruta>) rdao.buscarTodas());
		
		return grafo;
		
		
	}
}
