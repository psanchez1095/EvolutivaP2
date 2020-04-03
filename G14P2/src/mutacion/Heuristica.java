package mutacion;

import java.util.ArrayList;
import java.util.Random;

import cromosoma.Cromosoma;

public class Heuristica {

	int probMut;
	int tamPoblacion;
	Cromosoma[] poblacion;
	Cromosoma in;
	int[][] f;
	int [][]d;
	private ArrayList<Integer> valMut;
	private ArrayList<Integer> posMut;
	private ArrayList<Integer> genotipo;
	private ArrayList<Integer> bestGenotipo;
	private double bestFitness;
	private int tamGen;
	static int genes = 3;// genes a mutar 
	int[] nuevaCombinacion = new int[genes];
	static boolean[] cambiados = new boolean[genes];
	
	public Heuristica (double probMut, int tamPoblacion, Cromosoma[] poblacion, int[][] f, int [][]d) {
		
		this.probMut = (int)(this.probMut*100.0d);
		this.tamPoblacion = tamPoblacion;
		this.poblacion = poblacion;
		this.f = f;
		this.d = d;
	}
	
	public int mutar() {
		int numMut = 0;
		for (int i = 0; i < tamPoblacion ; ++i) {
			Random rn = new Random();
			if(rn.nextInt() < probMut) {
				numMut++;
				poblacion[i].setFenotipo(mutarCromosoma(poblacion[i]));
			}
		}
		return numMut;
	}
	
	public ArrayList<Integer> mutarCromosoma (Cromosoma indv) {
		try {
		genotipo = indv.getFenotipo();
		tamGen = genotipo.size();	
		ArrayList<ArrayList<Integer>> mtx;
		bestFitness = 1000000;
		int aux;
		bestGenotipo = new ArrayList<Integer>(indv.getLongitud());
		valMut = new ArrayList<>(genes);
		posMut = new ArrayList<>(genes);
			
		//Cargamos los arrays
		for(int i = 0; i < genes; i++) {
			
			int p = (int) (Math.random()*(tamGen-1));
			
			if(!valMut.contains(genotipo.get(p))) { 
				valMut.add(genotipo.get(p));
				posMut.add(p);
			}
			
			else i--;	
			
		}
		
		// hasta aqui bien
//		int vuelta = 0;
//		crearFenotiposReflex(valMut, nuevaCombinacion,vuelta);
		
		mtx = permute(valMut);
		
		for (int i = 0; i < mtx.size() ; ++i) {
			//preparar el fenotipo
			for(int j = 0; j < posMut.size(); ++j) {
				genotipo.set(posMut.get(j), mtx.get(i).get(j));
			}
			//calculamos el fitness
			aux = resultadoHeuristica(genotipo);
			if (aux < bestFitness)bestGenotipo = genotipo;
		}
		}catch(Exception e) {}
		return bestGenotipo;
		
	}
	
	 public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
			// Validación de entrada
			if( a == null )
			    throw new IllegalArgumentException( "La entrada no puede ser nula" );
			        
			ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>();

			if( a.size() == 1 ){
			    toReturn.add( new ArrayList<Integer>(a) );
			    return toReturn;
			}
			
			// Bucle en todos los elementos. 
			for( int i = 0 ; i < a.size(); i++ ){
			    int current = a.get( i );
			    ArrayList<Integer> tmp = new ArrayList<Integer>(a);
			    tmp.remove( i );

			   // Llamada recursiva con subconjunto 
			   ArrayList<ArrayList<Integer>> res = permute(tmp);
			       
			   // Crear el resultado de la sub lista devuelta
			   for( int j = 0 ; j < res.size() ; j++ ){
			       ArrayList<Integer> toAdd = new ArrayList<Integer>();
			       toAdd.add( current );
			       toAdd.addAll( res.get( j ) );
			       
			       toReturn.add( toAdd );
			   }
			}
			   
			// devolver los resultados
			return toReturn;
		    }
		
		
	
	private int resultadoHeuristica(ArrayList<Integer> fenotipo) {
		
		int total = 0;
		
		for (int i = 0; i < fenotipo.size(); i++){
			
			for (int j = 0; j < fenotipo.size(); j++)
				total += d[i][j] * f[fenotipo.get(i)][fenotipo.get(j)];
			
		}
		
		return total;
		
	}
	
//	public void crearFenotiposReflex(ArrayList<Integer> valMut, int[] nuevaCombinacion, int numeroVuelta) {
//		
//		if (numeroVuelta != genes) {
//			
//			for (int i = 0; i < valMut.size(); i++){
//				
//				if (cambiados[i] == true) {}
//				
//				else { 
//					
//					cambiados[i] = true;
//					nuevaCombinacion[genes] = valMut.get(i);
//					crearFenotiposReflex(valMut, nuevaCombinacion, numeroVuelta + 1);
//					cambiados[i] = false;
//					
//				}
//			}
//			
//		}
//		else{
//			for (int i = 0; i< numeroVuelta; i++) genotipo.set(posMut.get(i), nuevaCombinacion[i]);
//
//			in.setFenotipo(genotipo);
//			
//			if(in.getFitness() <= bestFitness) {
//				
//				bestGenotipo = new ArrayList<>(genes);
//				bestFitness = in.getFitness();
//				for(int j = 0; j < genes; j++) bestGenotipo.add(genotipo.get(j));	
//				
//			}
//		}
//		
//	}

	
}