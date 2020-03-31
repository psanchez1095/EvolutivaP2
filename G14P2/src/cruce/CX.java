package cruce;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class CX implements Cruce {

	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		// TODO Auto-generated method stub
		
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		int size = in1.getFenotipo().size();
		boolean finCiclo = false;
		int posi = 0;
		ArrayList<Boolean> tomados = new ArrayList<>(size); 	
		ArrayList<Integer> hijo1 = new ArrayList<>(size); 
		ArrayList<Integer> hijo2 = new ArrayList<>(size); 
		
		for(int i = 0; i< size; i++){
			tomados.add(i, false);
			hijo1.add(i, size);
			hijo2.add(i, size);
		}
						
		hijo1.set(0,in1.getFenotipo().get(0));
		hijo2.set(0,in2.getFenotipo().get(0));
		tomados.set(in1.getFenotipo().get(0), true);
		
		//posición del equivalente
		posi = buscarPosicion(in2.getFenotipo().get(0), 1, in1,in2);
			
		
		while(!finCiclo) {
			
			if(tomados.get(in1.getFenotipo().get(posi)) == true) {
				finCiclo = true;			
			}else{
				hijo1.set(posi,in1.getFenotipo().get(posi));
				hijo2.set(posi, in2.getFenotipo().get(posi));
				tomados.set(in1.getFenotipo().get(posi), true);
				posi = buscarPosicion(in2.getFenotipo().get(posi), 1,in1,in2);
			}		
		}

		for (int i = 0; i< size; i++) {			
			if (hijo1.get(i) == size) {
				hijo1.set(i, in2.getFenotipo().get(i));
				hijo2.set(i, in1.getFenotipo().get(i));
			}			
		}
				
		in1.setFenotipo(hijo1);
		in2.setFenotipo(hijo2);
		nuevosCromosomas[0] = in1;
		nuevosCromosomas[1] = in2;
		
		return nuevosCromosomas;
	}

	/* Devuelve la posición de un gen dentro del genotipo */
	private int buscarPosicion(int gen, int numPadre,Cromosoma cromo1, Cromosoma cromo2){
		
		int posi = 0;
			
		if(numPadre == 1) {		
			while (cromo1.getFenotipo().get(posi) != gen )
				posi++;			
		}else {		
			while (cromo2.getFenotipo().get(posi) != gen )
				posi++;
		}
								
		return posi;
	}

}
