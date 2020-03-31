package cruce;

import java.util.ArrayList;
import java.util.Random;
import cromosoma.Cromosoma;

public class OX implements Cruce { //Cruce por orden

	@Override
	public Cromosoma[] cruzar(Cromosoma cromo1, Cromosoma cromo2) {
		
		int size = cromo1.getFenotipo().size();
		ArrayList<Integer> hijo1 = new ArrayList<>(size);
		ArrayList<Integer> hijo2 = new ArrayList<>(size);
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		Random rand = new Random();
		
		int primerCorte = rand.nextInt(size - 1);
		int segundoCorte = rand.nextInt(size - 1);
		
		if (segundoCorte < primerCorte){
			int aux = primerCorte;
			primerCorte = segundoCorte;
			segundoCorte = aux;
		}
		
		int tamanoCorte = segundoCorte - primerCorte + 1;
		int[] cromo1Cambiados = new int[tamanoCorte];
		int[] cromo2Cambiados = new int[tamanoCorte];
		
		for (int i = 0; i < size; i++){
			
			if (i >= primerCorte && i <= segundoCorte){
				
				cromo1Cambiados[i - primerCorte] = cromo2.getFenotipo().get(i);
				hijo1.add(cromo2.getFenotipo().get(i));
				cromo2Cambiados[i - primerCorte] = cromo1.getFenotipo().get(i);
				hijo2.add(cromo1.getFenotipo().get(i));
				
			}
			else{
				hijo1.add(-1);
				hijo2.add(-1);
			}
			
		}
		Cromosoma cromoFor = new Cromosoma(cromo1.getLongitud());
		int[] cromo1CambiadosFor = new int[tamanoCorte];
		int[] cromo2CambiadosFor = new int[tamanoCorte];
		ArrayList<Integer> hijoFor = new ArrayList<>(size);
		
		for(int vuelta = 0; vuelta < nuevosCromosomas.length ; vuelta++){
		
			if(vuelta == 0) {
			cromoFor = cromo1;
			hijoFor = hijo1;
			cromo1CambiadosFor = cromo1Cambiados;
			cromo2CambiadosFor = cromo2Cambiados;
			}
			else{
			cromoFor = cromo2;
			hijoFor = hijo2;
			cromo1CambiadosFor = cromo2Cambiados;
			cromo2CambiadosFor = cromo1Cambiados;
		
		
		}
			
		int nuevaPos = (segundoCorte + 1) % size;
		int i = nuevaPos;
		
		while (nuevaPos != primerCorte){
			
			if (i != primerCorte){
				
				if (elementInArray( cromoFor.getFenotipo().get(i), cromo1CambiadosFor)){
					i = (i + 1) % size;
				} 
				
				else {
					
					hijoFor.set(nuevaPos, cromoFor.getFenotipo().get(i));
					nuevaPos = (nuevaPos + 1) % size;
					i = (i + 1) % size;
					
				}
			} 
			else{
				for (int j = 0; j < tamanoCorte; j++){
					if (!elementInArray(cromo2CambiadosFor[j], cromo1CambiadosFor)){
						
						hijoFor.set(nuevaPos, cromo2CambiadosFor[j]);
						nuevaPos = (nuevaPos + 1) % size;
						
					}
				}
			}
		}

		if(vuelta == 0) {
			cromo1  = cromoFor;
			hijo1 = hijoFor ;
			cromo1Cambiados = cromo1CambiadosFor;
			cromo2Cambiados=cromo2CambiadosFor;
		}
		
		else{
			cromo2 = cromoFor;
			hijo2=hijoFor;
			cromo2Cambiados = cromo1CambiadosFor;
			cromo1Cambiados = cromo2CambiadosFor;
		}
		
		}

		cromo1.setFenotipo(hijo1);
		cromo2.setFenotipo(hijo2);
		nuevosCromosomas[0] = cromo1;
		nuevosCromosomas[1] = cromo2;
		
		return nuevosCromosomas;
	}
	
	private boolean elementInArray( int valor,int[] array ){
		
		for (int i = 0; i < array.length; i++)
		{
			if ( array[i] == i ) return true;	
		}
		
		return false;
		
	}

}