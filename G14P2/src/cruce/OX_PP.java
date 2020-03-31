package cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cromosoma.Cromosoma;

public class OX_PP implements Cruce{

	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		// TODO Auto-generated method stub
		
		int size = in1.getFenotipo().size();
		Random aleatorio = new Random();
		int totalCambios = aleatorio.nextInt(size / 2) + 1;
		
		ArrayList<Integer> hijos1 = new ArrayList<Integer>(totalCambios);
		ArrayList<Integer> hijos2 = new ArrayList<Integer>(totalCambios);
		ArrayList<Integer> indicesCambiados = new ArrayList<Integer>(totalCambios);
		ArrayList<Integer> hijo1 = new ArrayList<>(size);
		ArrayList<Integer> hijo2 = new ArrayList<>(size);
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		int nuevoIndice = -1;
		int totalAñadidos = 0;
		
		// En este while llenamos el array que contiene el valor de los indices cambiados
		
		while(totalAñadidos < totalCambios) {
			
			boolean repetido = false;
			nuevoIndice = aleatorio.nextInt(size);
			
			if(totalAñadidos > 0) {
				
				for(int i = 0 ; i < totalAñadidos; i++) {
					
					if ( indicesCambiados.get(i) == nuevoIndice)
					{	
						repetido = true;
						break;
					}
					
				}
				
			}
			
			if(!repetido && totalAñadidos < totalCambios) {
				
				indicesCambiados.add(nuevoIndice);
				totalAñadidos ++;
				
			}
				
		}
		
		
		Collections.sort(indicesCambiados);
		
		for(Integer pos : indicesCambiados){
			
			hijos1.add(in2.getFenotipo().get(pos));
			hijos2.add(in1.getFenotipo().get(pos));
			
			}
		
		final int ultimoIndice = indicesCambiados.get( indicesCambiados.size() -1 );
		int marca=0;
		
		for (int i = 0; i < size; i++){
			
			hijo1.add(-1);
			hijo2.add(-1);
			
		}
		
		int numCambio = 0;
		int k = (ultimoIndice + 1) % size;
		marca = k;
	 
	    int aux;
	    
		while (k != ultimoIndice){
			
			if ((aux = encuentra( k, indicesCambiados )) != -1){
				
				hijo1.set(k, hijos1.get(aux));
				
				if (marca == k) marca = (marca + 1) % size;
				
				k = (k + 1) % size;
				
			}
			
			else if (marca != ultimoIndice){
				
				if (encuentra(in1.getFenotipo().get(marca), hijos1 ) != -1){
					do{
					marca = (marca + 1) % size;
					}while((marca != ultimoIndice) && (encuentra( marca, indicesCambiados ) != -1));
				} 
				else {
					
					hijo1.set( k, in1.getFenotipo().get(marca) );
					k = (k + 1) % size;
					
					do {
						marca = (marca + 1) % size;
					}while((encuentra( marca, indicesCambiados ) != -1) && (marca != ultimoIndice));
					
				}
			} 
			
			else{
				
				while((aux = encuentra( hijos2.get(numCambio), hijos1 )) != -1) numCambio++;
				
				hijo1.set(k, hijos2.get(numCambio));
				k = (k + 1) % size;
				numCambio++;
			}
			
		}
		
		hijo1.set(ultimoIndice, hijos1.get(encuentra( ultimoIndice,indicesCambiados )));
		
		numCambio = 0;
		k = (ultimoIndice + 1) % size;
	    marca = k;
	   
	    
	    while (k != ultimoIndice){
	    	
	    	if ((aux = encuentra( k,indicesCambiados)) != -1){
	    		
	    		hijo2.set(k, hijos2.get(aux));
	    		
				if (marca == k) marca = (marca + 1) % size;
				
				k = (k + 1) % size;
			}
	    	
			else if (marca != ultimoIndice){
				
				if (encuentra( in2.getFenotipo().get(marca),hijos2 ) != -1){
					
					do{
						marca = (marca + 1) % size;
					}while( (marca != ultimoIndice) && ( encuentra( marca,indicesCambiados ) != -1) );
				} 
			
				else{
					
					hijo2.set(k, in2.getFenotipo().get(marca));
					k = (k + 1) % size;
					
					do {
						marca = (marca + 1) % size;
					}while( (marca != ultimoIndice) && (encuentra( marca, indicesCambiados ) != -1) );
				}
			} 
			else{
				
				while((aux = encuentra( hijos1.get(numCambio), hijos2 )) != -1) numCambio++;
				
				hijo2.set(k, hijos1.get(numCambio));
				k = (k + 1) % size;
				numCambio++;
				
			}
		}
	    
	    hijo2.set(ultimoIndice, hijos2.get(encuentra( ultimoIndice, indicesCambiados )));

		in1.setFenotipo(hijo1);
		in2.setFenotipo(hijo2);
		nuevosCromosomas[0] = in1;
		nuevosCromosomas[1] = in2;
		
		return nuevosCromosomas;
	}
	
	private int encuentra( int valor, ArrayList<Integer> listaEnteros){
		
		int posicion= -1; // Devuelve la posicion si lo encuentra, si no devuelve uno
		
		for (int i = 0; i < listaEnteros.size(); i++){
			
			if (valor == listaEnteros.get(i)){
				posicion = i;
				return posicion;
			}
		}
		return posicion;
	}

}
