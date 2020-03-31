package algoritmoGenetico;

import cruce.*;
import funcion.*;
import mutacion.Heuristica;
import mutacion.Insercion;
import mutacion.Intercambio;
import mutacion.Inversion;
import cromosoma.Cromosoma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cromosoma.*;
import seleccion.*;
import utils.*;

public class AlgoritmoGenetico {
	
	Cromosoma[] poblacion; //Poblacion total //Probamos con list
	ArrayList<Cromosoma> elite; //Elite seleccionada
	
	//Parametros del algoritmo.
	TipoMutacion mutacion;
	public TipoMutacion getFuncion() {
		return mutacion;
	}

	TipoSeleccion tipo_seleccion;
	TipoCruce tipo_cruce;
	int tamPoblacion, numGeneraciones, numGenes, generacionActual;
	double probabilidadCruce, probabilidadMutacion, probabilidadUniforme, precision, elitismo;
	boolean booleanElite;
	//Valores para escribir la grafica.
	double[] mediasGeneracion;
	double[] mejoresGeneracion;
	double[] mejoresAbsolutos;

	double mediaGeneracion;
	double mejorGeneracion;
	double mejorAbsoluto;
	
	private Cromosoma elMejor; //Mejor cromosoma encontrado hasta ahora.
	int pos_mejorGeneracion;   //Posicion del mejor cromosoma de la generacion
	
	private int[][] flujos;
	private int[][] distancias;
	private int size;
	
	public AlgoritmoGenetico(TipoMutacion tMut, TipoSeleccion tSel, TipoCruce tCrux,
							 int tPob, int nGeneracs, int nGenes, 
							 double pCruce, double pMutacion, double pUnif, double prec,
							 double elit) {

		this.generacionActual = 0;
		this.mutacion = tMut;
		this.tipo_seleccion = tSel;
		this.tipo_cruce = tCrux;
		this.numGeneraciones = nGeneracs;
		this.numGenes = nGenes;
		this.tamPoblacion = tPob;
		this.probabilidadCruce = pCruce;
		this.probabilidadMutacion = pMutacion;
		this.probabilidadUniforme = pUnif;
		this.precision = prec;
		this.elitismo = elit;
		
		this.poblacion = new Cromosoma[tPob];
		this.elite = new ArrayList<Cromosoma>();
		
		this.booleanElite = this.elitismo == 0.0 ? false : true; 
		// false en caso de que sea 0.0 y true en caso contrario
		
		this.mediasGeneracion = new double[nGeneracs];
		this.mejoresGeneracion = new double[nGeneracs];
		this.mejoresAbsolutos = new double[nGeneracs];
		
		
	}
	
	public void inicializaPoblacion() {
		
		leerFich(" ");
		
		for(int i = 0; i < tamPoblacion; i++) {
			this.poblacion[i] = new Cromosoma(size);
		}
		
        this.elMejor = this.duplicarCromosoma(this.poblacion[0]);
        fitness(elMejor);
        this.generacionActual = 0;

	}
	
	public double fitness(Cromosoma cromosoma) {
		double valor = 0.0;
		//cromosoma.calcularFenotipo();
		ArrayList<Integer> x = cromosoma.getFenotipo();
		
		valor = Funcion1.evalua(flujos, distancias, x);
		
		cromosoma.setFitness(valor);
		return valor;
	}

	public void evaluaPoblacion() {
		double fitness, fitness_best, sum_fitness = 0;
		int pos_fitness_best = 0;
		fitness_best = 100000;
		
		
		
		for(int i = 0; i < this.poblacion.length-1; i++) {
			fitness = fitness(this.poblacion[i]);

			if((fitness < fitness_best)) {
				fitness_best = fitness;
				pos_fitness_best = i; 
			}
				sum_fitness += fitness;
		}
		double puntuacion = 0, puntuacion_acu = 0;
		
		for(int i = 0; i < this.tamPoblacion; i++) {
			puntuacion = fitness(this.poblacion[i]) / sum_fitness;
			puntuacion_acu += puntuacion;
			this.poblacion[i].setPuntuacion(puntuacion);
			this.poblacion[i].setPuntAcumulada(puntuacion_acu);
		}
		
		
		if(elMejor.getFitness() > this.poblacion[pos_fitness_best].getFitness()) {
			elMejor = this.duplicarCromosoma(this.poblacion[pos_fitness_best]); 
		} 

		
		
		this.mediasGeneracion[this.generacionActual] = sum_fitness / this.tamPoblacion;
		this.mejoresGeneracion[this.generacionActual] = fitness_best;
		this.mejoresAbsolutos[this.generacionActual] = this.elMejor.getFitness();
		
	}
	
	public void seleccionaPoblacion() {
		
		switch(tipo_seleccion) {
		case ESTOCASTICO: 
			EstocasticoUniversal estocastUniv = new EstocasticoUniversal(this.poblacion, this.tamPoblacion);
			estocastUniv.seleccionEstocastico(this.mutacion, this.precision, this.numGenes);
			break;
		case RULETA: 
			Ruleta ruleta = new Ruleta(this.poblacion, this.tamPoblacion);
			ruleta.seleccionRuleta();
			break;
		case TORNEO:
			Torneos torneos = new Torneos(this.poblacion, this.tamPoblacion);
			torneos.seleccionTorneos(this.mutacion, this.precision, this.numGenes);
			break;	
		default:
			Ruleta r = new Ruleta(this.poblacion, this.tamPoblacion);
			r.seleccionRuleta();
			break;
		}
	}
	
	/**
	 * Cruza y muta la poblacion en funcion de probabilidadCruce y probabilidadMutacion.
	 */
	public void reproducePoblacion() {
		
		ArrayList<Cromosoma> poblacionCruce = new ArrayList<Cromosoma>();
		ArrayList<Integer>   indicePadres = new ArrayList<Integer>();
		Cruce cruce;
	
		switch(tipo_cruce) {
		case PMX:
			cruce = new PMX();
		case CO:
			cruce = new CO();
		case ERX:
			cruce = new ERX();
		case OX_PP:
			cruce = new OX_PP();
		case OX:
			cruce = new OX();
		case CX:
			cruce = new CX();
		default:
			cruce = new PMX();
		}
		
		for( int i =0; i <this.poblacion.length ;i++) {
			
			double valor = Math.random();
		
				if (valor <= this.probabilidadCruce) {
					poblacionCruce.add(this.poblacion[i]);
					indicePadres.add(i);
				}
		
		
			}
	
			Random rand = new Random();
			
			while (poblacionCruce.size() > 1)
			{
				int indicePadre1 = Math.abs(rand.nextInt() % poblacionCruce.size());
				Cromosoma padre1 = poblacionCruce.get(indicePadre1);
				int padre1IndividuoIndex = indicePadres.get(indicePadre1);
				
				indicePadres.remove(indicePadre1);
				poblacionCruce.remove(indicePadre1);

				int indicePadre2 = Math.abs(rand.nextInt() % poblacionCruce.size());
				Cromosoma padre2 = poblacionCruce.get(indicePadre2);
				int padre2IndividuoIndex = indicePadres.get(indicePadre2);
				
				indicePadres.remove(indicePadre2);
				poblacionCruce.remove(indicePadre2);

				
				Cromosoma[] hijos = cruce.cruzar(padre1.duplicarCromosoma(size), padre2.duplicarCromosoma(size));
				
				this.poblacion[padre1IndividuoIndex] = hijos[0];
				this.poblacion[padre2IndividuoIndex] = hijos[1];
				
			}
		}
		

	
	public void mutaPoblacion() {
		switch(mutacion) {
		case INSERCION:
				Insercion ins = new Insercion(probabilidadMutacion, tamPoblacion, poblacion);
				ins.mutar();
			break;
		case INTERCAMBIO:
				Intercambio inte = new Intercambio(probabilidadMutacion, tamPoblacion, poblacion);
				inte.mutar();
			break;
		case INVERSION:
				Inversion inv = new Inversion(probabilidadMutacion, tamPoblacion, poblacion);
				inv.mutar();
			break;
		case HEURISTICA:
			Heuristica heu = new Heuristica(probabilidadMutacion, tamPoblacion, poblacion, flujos, distancias);
			heu.mutar();
		break;
		default:
			Insercion in = new Insercion(probabilidadMutacion, tamPoblacion, poblacion);
			in.mutar();
		break;
		}
			
			
	}
	
	public void seleccionaElite() {
    	int num_elites = (int) (this.tamPoblacion * this.elitismo);
    	if (num_elites > this.tamPoblacion) num_elites = this.tamPoblacion;
    	this.ordenarPoblacion(0, this.tamPoblacion-1);
    	this.elite.clear();
    	
    	for (int i = 0 ; i < num_elites; i++)
    		this.elite.add(duplicarCromosoma(this.poblacion[this.tamPoblacion-i-1]));
    	
	}
	
	public void incluyeElite() {
		for(int i = 0; i < this.elite.size() ; i++) {		
			this.poblacion[i] = elite.get(i);
		}
	}
	
	public int getNumElites() {
		return (int) Math.ceil(this.getTamPoblacion() * this.elitismo / 100);
	}
	
	/**
	 * Devuelve true si se cumple la condicion de
	 * terminacion del bucle; false en c/c.
	 * @param generacion_actual
	 * @return
	 */
	public boolean terminado(int generacion_actual) {
		return generacion_actual >= this.numGeneraciones;
	}
	
	
    /**
     * Ordena de menor a mayor fitness
     * @param poblacion
     * @param izq
     * @param der
     */
    private void ordenarPoblacion(int izq, int der) {
    	 
        int i = izq;
        int j = der;
        
        Cromosoma pivote = this.poblacion[(i+j)/2];
        
        do {
            while (this.poblacion[i].getFitness() < pivote.getFitness()){
                i++;
            }
            while (this.poblacion[j].getFitness() > pivote.getFitness()){
                j--;
            }
            if (i<=j){
                Cromosoma aux = duplicarCromosoma(this.poblacion[i]);
                this.poblacion[i] = duplicarCromosoma(this.poblacion[j]);
                this.poblacion[j] = duplicarCromosoma(aux);
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            ordenarPoblacion(izq, j);
        }
        if (i<der){
            ordenarPoblacion(i, der);
        }
    }

	
    public boolean getBooleanElite() {
    	return this.booleanElite;
    }
    
    public int getTamPoblacion() {
    	return this.tamPoblacion;
    }
    
    public int getNumGeneraciones() {
    	return this.numGeneraciones;
    }
    
    public double getProbabilidadCruce() {
    	return this.probabilidadCruce;
    }
    
    public double getProbabilidadMutacion() {
    	return this.probabilidadMutacion;
    }
    
    public double getPrecision() {
    	return this.precision;
    }

	public double[] getMejoresAbsolutos() {
		return this.mejoresAbsolutos;
	}

	public double[] getMejoresGeneracion() {
		return this.mejoresGeneracion;
	}

	public double[] getMedias() {
		return this.mediasGeneracion;
	}

	public Cromosoma getMejor() {
		return duplicarCromosoma(this.elMejor);
	}

	public void aumentaGeneracion() {
		this.generacionActual++;
	}
	
	private Cromosoma duplicarCromosoma(Cromosoma c) {
	    Cromosoma nuevo = new Cromosoma(size) ;

	    nuevo.setFitness(c.getFitness());
	    nuevo.setFenotipo(c.getFenotipo());
	    nuevo.setPuntAcumulada(c.getPuntAcumulada());
	                
	    return nuevo;
	    }
	
	
		
		private int[][] leerMatrix(Scanner sc){
			int[][] res = new int[size][size];
			
			for (int i = 0; i < size; i++)
			{
				String[] numbers = sc.nextLine().trim().split("\\s+");
				for (int j = 0; j < numbers.length; j++)
				{
					res[i][j] = Integer.parseInt(numbers[j]); //No lo he mirado, a lo mejor este es distancias
				}
			}
			return res;
		}
		
		public void leerFich (String filepath)
		{
			File file = new File("archivos/" + filepath); 
		    Scanner sc;
			try {
				sc = new Scanner(file);
				size = Integer.parseInt(sc.nextLine().trim().split("\\s+")[0]);
				sc.nextLine(); //Linea en blanco
				distancias = leerMatrix(sc);
				sc.nextLine(); //Linea en blanco
				flujos = leerMatrix(sc);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}    
	}


		

}
