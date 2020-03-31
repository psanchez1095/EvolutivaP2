package cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cromosoma.Cromosoma;

public class OX_PP implements Cruce{

	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		// TODO Auto-generated method stub
		
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		int size = in1.getFenotipo().size();
		Random aleatorio = new Random();
		int totalCambios = aleatorio.nextInt(size / 2) + 1;
		
		ArrayList<Integer> hijos1 = new ArrayList<Integer>(totalCambios);
		ArrayList<Integer> hijos2 = new ArrayList<Integer>(totalCambios);
		ArrayList<Integer> indicesCambiados = new ArrayList<Integer>(totalCambios);
		
		int nuevoIndice = -1;
		int totalAnadidos = 0;
		
		// En este while llenamos el array que contiene el valor de los indices cambiados
		
		while(totalAnadidos < totalCambios) {
			
			boolean repetido = false;
			nuevoIndice = aleatorio.nextInt(size);
			
			if(totalAnadidos > 0) {
				
				for(int i = 0 ; i < totalAnadidos; i++) {
					
					if ( indicesCambiados.get(i) == nuevoIndice)
					{	
						repetido = true;
						break;
					}
					
				}
				
			}
			
			if(!repetido && totalAnadidos < totalCambios) {
				indicesCambiados.add(nuevoIndice);
				totalAnadidos ++;
			}
				
		}
		
		
		Collections.sort(indicesCambiados);
		
		for (Integer pos : indicesCambiados)
		{
			hijos1.add(in2.getFenotipo().get(pos));
			hijos2.add(in1.getFenotipo().get(pos));
		}
		
		ArrayList<Integer> genotipoSon1 = new ArrayList<>(size);
		ArrayList<Integer> genotipoSon2 = new ArrayList<>(size);
		
		int i=0;
		
		for (i = 0; i < size; i++)
		{
			genotipoSon1.add(-1);
			genotipoSon2.add(-1);
		}
		
		final int ultimoIndice = indicesCambiados.get( indicesCambiados.size() -1 );
		int k = (ultimoIndice + 1) % size;
	    i = k;
	    int j = 0;
	    int aux;
	    
		while (k != ultimoIndice)
		{
			if ((aux = encuentra( k, indicesCambiados )) != -1)
			{
				genotipoSon1.set(k, hijos1.get(aux));
				if (i == k)
					i = (i + 1) % size;
				k = (k + 1) % size;
			}
			else if (i != ultimoIndice)
			{
				if (encuentra(in1.getFenotipo().get(i), hijos1 ) != -1)
				{
					do {
					i = (i + 1) % size;
					} while ((encuentra( i, indicesCambiados ) != -1) && (i != ultimoIndice));
				} else {
					genotipoSon1.set( k, in1.getFenotipo().get(i) );
					k = (k + 1) % size;
					do {
						i = (i + 1) % size;
					} while ((encuentra( i, indicesCambiados ) != -1) && (i != ultimoIndice));
				}
			} else {
				while ((aux = encuentra( hijos2.get(j), hijos1 )) != -1)
				{
					j++;
				}
				genotipoSon1.set(k, hijos2.get(j));
				k = (k + 1) % size;
				j++;
			}
		}
		genotipoSon1.set(ultimoIndice, hijos1.get(encuentra( ultimoIndice,indicesCambiados )));

		k = (ultimoIndice + 1) % size;
	    i = k;
	    j = 0;
	    while (k != ultimoIndice)
		{
	    	if ((aux = encuentra( k,indicesCambiados)) != -1)
			{
				genotipoSon2.set(k, hijos2.get(aux));
				if (i == k)
					i = (i + 1) % size;
				k = (k + 1) % size;
			}
			else if (i != ultimoIndice)
			{
				if (encuentra( in2.getFenotipo().get(i),hijos2 ) != -1)
				{
					do {
					i = (i + 1) % size;
					} while ((encuentra( i,indicesCambiados ) != -1) && (i != ultimoIndice));
				} else {
					genotipoSon2.set(k, in2.getFenotipo().get(i));
					k = (k + 1) % size;
					do {
						i = (i + 1) % size;
					} while ((encuentra( i, indicesCambiados ) != -1) && (i != ultimoIndice));
				}
			} else {
				while ((aux = encuentra( hijos1.get(j), hijos2 )) != -1)
				{
					j++;
				}
				genotipoSon2.set(k, hijos1.get(j));
				k = (k + 1) % size;
				j++;
			}
		}
		genotipoSon2.set(ultimoIndice, hijos2.get(encuentra( ultimoIndice, indicesCambiados )));

		in1.setFenotipo(genotipoSon1);
		in2.setFenotipo(genotipoSon2);
		nuevosCromosomas[0] = in1;
		nuevosCromosomas[1] = in2;
		
		return nuevosCromosomas;
	}
	
	private int encuentra( int pos, ArrayList<Integer> listaEnteros)
	{
		int encontrado= -1; // Devuelve la posicion si lo encuentra, si no devuelve uno
		
		for (int i = 0; i < listaEnteros.size(); i++)
		{
			if (pos == listaEnteros.get(i)) {
				encontrado = i;
				return encontrado;
			}
		}
		return encontrado;
	}

}
