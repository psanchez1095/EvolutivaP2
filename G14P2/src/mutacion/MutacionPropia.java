package mutacion;

import java.util.Random;

import cromosoma.Cromosoma;

public class MutacionPropia { //SemiIntercambio

	int probMut;
	int tamPoblacion;
	Cromosoma[] poblacion;
	
	public MutacionPropia (double probMut, int tamPoblacion, Cromosoma[] poblacion) {
		this.probMut = (int)(this.probMut*100);
		this.tamPoblacion = tamPoblacion;
		this.poblacion = poblacion;
	}
	
	
	
	public int mutar() {
		
		Random rn = new Random();
		int pos1, pos2, numMut = 0;
		for (int i = 0; i < this.tamPoblacion; ++i) {
			
			Random rnn = new Random();
			if(rnn.nextInt() < probMut) {
				
				numMut++;
				for(int j = 0; j < probMut; ++j) {
					
					pos1 = rn.nextInt(this.tamPoblacion);
					pos2 = rn.nextInt(this.tamPoblacion);
					
					poblacion[i].getFenotipo().set(pos1, poblacion[i].getFenotipo().get(pos2));
					
				}	
			}
			
		}
		return numMut;
	}
	
		
}
