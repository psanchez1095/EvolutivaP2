package mutacion;

import java.util.Random;

import cromosoma.Cromosoma;

public class Intercambio {

	int probMut;
	int tamPoblacion;
	Cromosoma[] poblacion;
	
	public Intercambio (double probMut, int tamPoblacion, Cromosoma[] poblacion) {
		this.probMut = (int)(this.probMut*100);
		this.tamPoblacion = tamPoblacion;
		this.poblacion = poblacion;
	}
	
	
	public void mutar() {
		Random rn = new Random();
		int pos1, pos2, aux;
		for (int i = 0; i < this.tamPoblacion; ++i) {
			for(int j = 0; j < probMut; ++j) {
				pos1 = rn.nextInt(this.tamPoblacion);
				pos2 = rn.nextInt(this.tamPoblacion);
				
				aux = poblacion[i].getFenotipo().get(pos1);
				poblacion[i].getFenotipo().set(pos1, poblacion[i].getFenotipo().get(pos2));
				poblacion[i].getFenotipo().set(pos2, aux);
			}
		}
	}
	
	
}
