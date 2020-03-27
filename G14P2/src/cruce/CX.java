package cruce;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class CX implements Cruce {

	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		// TODO Auto-generated method stub
		
		int size = in1.getFenotipo().size();
		boolean finCiclo = false;
		int posi = 0;
		ArrayList<Boolean> tomados = new ArrayList<>(size); // array auxiliar para marcar posiciones ya elegidas		
		ArrayList<Integer> genotipoSon1 = new ArrayList<>(size); // array para guardar el hijo 1 generado
		ArrayList<Integer> genotipoSon2 = new ArrayList<>(size); // array para guardar el hijo 2 generado
		
		/*
		System.out.println(genotipo1);
		System.out.println(genotipo2);
		System.out.println();*/
		
		/* inicializamos el array de posiciones tomadas a false */
		/* inicializamos los array de hijos a valor = size */
		for (int i = 0; i< size; i++) {
			tomados.add(i, false);
			genotipoSon1.add(i, size);
			genotipoSon2.add(i, size);
		}
					
		/* Ciclo */		
		genotipoSon1.set(0,in1.getFenotipo().get(0));
		genotipoSon2.set(0,in2.getFenotipo().get(0));
		tomados.set(in1.getFenotipo().get(0), true);
		
		// busco posición del omólogo
		posi = buscarPosicion(in2.getFenotipo().get(0), 1, in1,in2);
			
		// mientras que no sea un valor ya tomado		
		while(!finCiclo) {
			
			if(tomados.get(in1.getFenotipo().get(posi)) == true) {
				finCiclo = true;			
			}else{
				genotipoSon1.set(posi,in1.getFenotipo().get(posi));
				genotipoSon2.set(posi, in2.getFenotipo().get(posi));
				tomados.set(in1.getFenotipo().get(posi), true);
				posi = buscarPosicion(in2.getFenotipo().get(posi), 1,in1,in2);
			}		
		}

		/* recorremos el array donde estamos generando los hijos, en las posiciones '== size', 
		 * no se ha modificado el gen, se escribe en esa posición el valor del padre contrario */		
		for (int i = 0; i< size; i++) {			
			if (genotipoSon1.get(i) == size) {
				genotipoSon1.set(i, in2.getFenotipo().get(i));
				genotipoSon2.set(i, in1.getFenotipo().get(i));
			}			
		}
				
		//System.out.println(genotipoSon1);
		//System.out.println(genotipoSon2);
		
		Cromosoma newIndividuos[] = new Cromosoma[2];
		in1.setFenotipo(genotipoSon1);
		in2.setFenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
	}

	
	/* Devuelve la posición de un gen dentro del genotipo */
	private int buscarPosicion(int gen, int numPadre,Cromosoma cromo1, Cromosoma cromo2) {
		int posi = 0;
			
		// Busca en el padre 1 o en el 2
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
