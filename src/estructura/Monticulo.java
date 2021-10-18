package estructura;

import java.util.ArrayList;
import java.util.PriorityQueue;

import dominio.Estacion;

public class Monticulo {
	
	public static Estacion ultMantenimiento(ArrayList<Estacion> estaciones) {
		PriorityQueue<Estacion> monticulo = new PriorityQueue<Estacion>((x,y)->Estacion.compare(x,y));
		int N = estaciones.size();

		for(int i=0; i<N; i++) monticulo.add(estaciones.get(i));
	
		return monticulo.remove();
		
	}
	

}
