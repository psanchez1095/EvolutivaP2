package cruce;

import java.util.ArrayList;
import java.util.Random;
import cromosoma.Cromosoma;

public class PMX implements Cruce{
	
	//Se encarga del mapeo del intercambio
	
	
	public Cromosoma[] cruzar(Cromosoma cromo1, Cromosoma cromo2)
	{
		
		Cromosoma nuevosCromosomas[] = new Cromosoma[2]; 
		Random aleatorio = new Random();
		int primerCorte = aleatorio.nextInt(cromo1.getFenotipo().size() - 1);
		int segundoCorte = aleatorio.nextInt(cromo1.getFenotipo().size() - 1);
		
		if(segundoCorte < primerCorte){ //Aseguramos que secondCut este a la derecha de firstCut
			int aux = primerCorte;
			primerCorte = segundoCorte;
			segundoCorte = aux;
			
		}
		
		int tamanoCorte = segundoCorte - primerCorte + 1;
		
		int[] in1Cambiados = new int[tamanoCorte];
		int[] in2Cambiados = new int[tamanoCorte];
		
		ArrayList<Integer> hijo1 = new ArrayList<>(cromo1.getFenotipo().size());
		ArrayList<Integer> hijo2 = new ArrayList<>(cromo1.getFenotipo().size());
	
		for (int i = primerCorte; i <= segundoCorte; i++){
			
			in1Cambiados[i - primerCorte] = cromo2.getFenotipo().get(i);
			in2Cambiados[i - primerCorte] = cromo1.getFenotipo().get(i);
			
		}
		
		for(int i = 0; i < cromo1.getFenotipo().size(); i++){
			
			if (i >= primerCorte && i <= segundoCorte){ 
				
				hijo1.add(in1Cambiados[i - primerCorte]);
				hijo2.add(in2Cambiados[i - primerCorte]); 
				
			} 
			else{
				
				hijo1.add(cromo1.getFenotipo().get(i)); 
				hijo2.add(cromo2.getFenotipo().get(i));
				boolean boolIni1Cambiado = false; 
				boolean boolIni2Cambiado = false;
				
				for (int j = 0; j < tamanoCorte; j++){
					
					// Para que solo haya un cambio he creado los boolean
					
					if(!boolIni1Cambiado && hijo1.get(i) == in1Cambiados[j]){
						
						hijo1.set(i, getEquivalente(hijo1.get(i), in1Cambiados, in2Cambiados));
						boolIni1Cambiado = true;
						
					}

					if(!boolIni2Cambiado && hijo2.get(i) == in2Cambiados[j]){
						
						hijo2.set(i, getEquivalente(hijo2.get(i), in2Cambiados, in1Cambiados));
						boolIni2Cambiado = true;
						
					}
					
				}
			}
		}
		
		cromo1.setFenotipo(hijo1);
		cromo2.setFenotipo(hijo2);
		nuevosCromosomas[0] = cromo1;
		nuevosCromosomas[1] = cromo2;
		
		return nuevosCromosomas;
		
	}
	
	private int getEquivalente(int valor, int[] arrayCromo1Cambiados, int[] arrayCromo2Cambiados){
		
		int value = valor;
		
		for(int i=0 ;i < arrayCromo1Cambiados.length ;i++) {
			
			if(arrayCromo1Cambiados[i] == value){ 
				
				value = arrayCromo2Cambiados[i];
				i =  -1 ;
				
				
			}
		}
		
		return value;
		
	}

}