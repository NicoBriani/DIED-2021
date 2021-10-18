package estructura;

import java.util.ArrayList;
import java.util.LinkedList;

import dominio.Estacion;
import dominio.Ruta;


public class CalculoFlujoMax {
	
	public static ArrayList<ArrayList<Ruta>> grafoValido(int idEstacionDestino, ArrayList<ArrayList<Ruta>> grafo){
		int N = grafo.size();
		ArrayList<ArrayList<Ruta>> ret = new ArrayList<ArrayList<Ruta>>();
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(idEstacionDestino);
		Boolean[] visited = new Boolean[N];
		for(int i=0; i<N; i++) visited[i] = false;
		for(int i=0; i<N; i++) ret.add(new ArrayList<Ruta>());
		visited[idEstacionDestino] = true;
		while(!q.isEmpty()) {
			int aux = q.getLast();
			q.pollLast();
			for(Ruta nxt : grafo.get(aux)) ret.get(aux).add(nxt);
			for(Ruta nxt : grafo.get(aux)) {
				if(!visited[nxt.getDestino().getId()]) {
					visited[nxt.getDestino().getId()] = true;
					q.push(nxt.getDestino().getId());
				}
			}
		}
		return ret;
	}
	
	/*
	public static long maxFlow(int idEstacionOrigen, int idEstacionDestino, ArrayList<ArrayList<Ruta>> grafo) {
	
		int N = grafo.size();
		int f, Mf = 0;
		int INF = 100000000; 
		int SRC = N, SNK = N+1;
		N+=2;
		int[] p = new int[N];
		for(int i = 0; i<N; i++) p[i] = 0; 
		grafo.add(new ArrayList<Ruta>()); 
		grafo.add(new ArrayList<Ruta>()); 
		Estacion e = new Estacion();
		e.setId(src);
		grafo.get(SRC).add(new Ruta(e, INF));
		Estacion e2 = new Estacion();
		e2.setId(SNK);
		grafo.get(snk).add(new Ruta(e2, INF));
		do {
			f=0;
			Boolean[] used = new Boolean[N];
			for(int i=0; i<N; i++) used[i] = false;
			LinkedList<Integer> q = new LinkedList<Integer>();
			q.add(SRC);
			for(int i = 0; i<N; i++) p[i] = -1;
			while(!q.isEmpty()) {
				int u = q.getLast();
				q.pollLast();
				if(u==SNK) break;
				for(Ruta r : grafo.get(u)) {
					if(r.getCantMaxPasajeros() > 0 && !used[r.getDestino().getId()]) {
						used[r.getDestino().getId()] = true;
						q.push(r.getDestino().getId());
						p[r.getDestino().getId()] = u;
					}
				}
			}
			class Local{
				int f;
				public void aumentar(int v, int minE){
					if(v==SRC) f=minE;
					else if(p[v] != -1) {
						int index = 0;
						for(Ruta r : grafo.get(p[v])) {
							if(r.getDestino().getId() != v) index++;
							else break;
						}
						if(index == grafo.get(p[v]).size()) {
							Estacion est = new Estacion();
							est.setId(v);
							grafo.get(p[v]).add(new Ruta(est, 0));
						}
						aumentar(p[v], Math.min(minE, grafo.get(p[v]).get(index).getCantMaxPasajeros()));
						grafo.get(p[v]).get(index).changeFlow(-f);
						index = 0;
						for(Ruta r : grafo.get(v)) {
							if(r.getDestino().getId() != p[v]) index++;
							else break;
						}
						if(index == grafo.get(v).size()) {
							Estacion est = new Estacion();
							est.setId(p[v]);
							grafo.get(v).add(new Ruta(est, 0));
						}
						grafo.get(v).get(index).changeFlow(f);	
					}
				}
			}
			Local aux = new Local();
			aux.f = f;
			aux.aumentar(SNK, INF);
			Mf+=aux.f;
			f=aux.f;
		} while(f > 0);
		return Mf;
		
		
	}
	
	public long getMaxFlow(int idEstacionOrigen, int idEstacionDestino, ArrayList<ArrayList<Ruta>> grafo) {

		
		return maxFlow(idEstacionOrigen,idEstacionDestino, grafoValido(idEstacionOrigen, grafo));
	}
	*/

}
