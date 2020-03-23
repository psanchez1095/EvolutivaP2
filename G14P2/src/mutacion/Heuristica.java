package mutacion;

import cromosoma.Cromosoma;

public class Heuristica {

	int probMut;
	int tamPoblacion;
	Cromosoma[] poblacion;
	int[][] f;
	int [][]d;
	
	
	public Heuristica (double probMut, int tamPoblacion, Cromosoma[] poblacion, int[][] f, int [][]d) {
		this.probMut = (int)(this.probMut*100);
		this.tamPoblacion = tamPoblacion;
		this.poblacion = poblacion;
		this.f = f;
		this.d = d;
	}
	
	
	public void mutar() {
		
		int insertos = this.tamPoblacion/probMut;
		for (int i = 0; i < this.tamPoblacion; ++i) {
			for(int j = 0; j < insertos; ++j) {
				
			}
		}
	}
}
