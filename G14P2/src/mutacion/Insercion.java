package mutacion;

import java.util.ArrayList;
import java.util.Random;

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
	
	
	public int mutar() {
		
		int insertos = this.probMut/this.tamPoblacion, numMut = 0;
		for (int i = 0; i < this.tamPoblacion; ++i) {
			Random rn = new Random();
			if(rn.nextInt(this.tamPoblacion) < probMut) {
				numMut++;
				for(int j = 0; j < insertos; ++j)
					desplazar(this.poblacion[i].getFenotipo(), insertos*j, (insertos*j)+(probMut-1));
			}
		}
		return numMut;
	}
	
	private void desplazar(ArrayList<Integer> c, int ini, int fin) {
		int aux = c.get(fin);
		for (int i = fin-1; i > ini ; --i) {
			c.set(i+1, c.get(i));
		}
		c.set(ini, aux);
	}
	
}
