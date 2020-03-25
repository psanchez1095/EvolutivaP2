package cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import cromosoma.Cromosoma;

public class PMX implements Cruce{
	
	//Se encarga del mapeo del intercambio
	
	
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2)
	{
		Random rand = new Random();
		
		ArrayList<Integer> genotipo1 = in1.getFenotipo();
		ArrayList<Integer> genotipo2 = in2.getFenotipo();
		
		int primerCorte = rand.nextInt(genotipo1.size() - 1);
		int segundoCorte = rand.nextInt(genotipo1.size() - 1);
		
		if (segundoCorte < primerCorte) //Aseguramos que secondCut este a la derecha de firstCut
		{
			int aux = primerCorte;
			primerCorte = segundoCorte;
			segundoCorte = aux;
		}
		

		int tamañoCorte = segundoCorte - primerCorte + 1;
		
		int[] in1Cambiados = new int[tamañoCorte];
		int[] in2Cambiados = new int[tamañoCorte];
		
		ArrayList<Integer> genotipoSon1 = new ArrayList<>(genotipo1.size());
		ArrayList<Integer> genotipoSon2 = new ArrayList<>(genotipo1.size());
	
		for (int i = primerCorte; i <= segundoCorte; i++)
		{
			
			in1Cambiados[i - primerCorte] = genotipo2.get(i);
			in2Cambiados[i - primerCorte] = genotipo1.get(i);
			
		}
		
		for (int i = 0; i < genotipo1.size(); i++)
		{
			if (i >= primerCorte && i <= segundoCorte) { 
				//Si pertenecen al corte, se pone el del corte directamente
				
				genotipoSon1.add(in1Cambiados[i - primerCorte]);
				genotipoSon2.add(in2Cambiados[i - primerCorte]); 
				
			} else {
				
				genotipoSon1.add(genotipo1.get(i)); 
				genotipoSon2.add(genotipo2.get(i));
				boolean boolIni1Cambiado = false; 
				boolean boolIni2Cambiado = false;
				
				for (int j = 0; j < tamañoCorte; j++) //Si coincide con los intercambiados...
				{
					// Para que solo haya un cambio he creado los boolean
					if (!boolIni1Cambiado && genotipoSon1.get(i) == in1Cambiados[j]) //...Se coloca la pareja del coincidente
					{
						genotipoSon1.set(i, getCambiado(genotipoSon1.get(i), in1Cambiados, in2Cambiados));
						boolIni1Cambiado = true;
					}

					if (!boolIni2Cambiado && genotipoSon2.get(i) == in2Cambiados[j])
					{
						genotipoSon2.set(i, getCambiado(genotipoSon2.get(i), in2Cambiados, in1Cambiados));
						boolIni2Cambiado = true;
					}
				}
			}
		}
		
		Cromosoma newIndividuos[] = new Cromosoma[2];
		in1.setFenotipo(genotipoSon1);
		in2.setFenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		
		return newIndividuos;
		
	}
	
	private int getCambiado(int toExchange, int[] origSon, int[] otherSon)
	{
		int res = toExchange;
		
		for(int i=0 ;i < origSon.length ;i++) {
			
			if (origSon[i] == res)
			{
				res = otherSon[i];
				i = 0;
			}
		}
		
		return res;
		
	}

}