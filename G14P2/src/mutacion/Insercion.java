package mutacion;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class Insercion {

	int probMut;
	int tamPoblacion;
	Cromosoma[] poblacion;
	
	public Insercion (double probMut, int tamPoblacion, Cromosoma[] poblacion) {
		this.probMut = (int)(this.probMut*100);
		this.tamPoblacion = tamPoblacion;
		this.poblacion = poblacion;
	}
	
	
	public void mutar() {
		
		int insertos = this.probMut/this.tamPoblacion;
		for (int i = 0; i < this.tamPoblacion; ++i) {
			for(int j = 0; j < insertos; ++j)
				desplazar(this.poblacion[i].getFenotipo(), insertos*j, (insertos*j)+(probMut-1));
		}
		
	}
	
	private void desplazar(ArrayList<Integer> c, int ini, int fin) {
		int aux = c.get(fin);
		for (int i = fin-1; i > ini ; --i) {
			c.set(i+1, c.get(i));
		}
		c.set(ini, aux);
	}
	
}
