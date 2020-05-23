package algoritmoGenetico;

import cruce.*;
import funcion.*;
import mutacion.Heuristica;
import mutacion.Insercion;
import mutacion.Intercambio;
import mutacion.Inversion;
import mutacion.MutacionPropia;
import cromosoma.Cromosoma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	double probabilidadCruce, probabilidadMutacion, probabilidadUniforme, elitismo;
	String precision;
	boolean booleanElite;
	//Valores para escribir la grafica.
	double[] mediasGeneracion;
	double[] mejoresGeneracion;
	double[] mejoresAbsolutos;
	double[] peoresAbsolutos;
	
	double mediaGeneracion;
	double mejorGeneracion;
	double mejorAbsoluto;
	
	Funcion1 funcion;
	private Cromosoma elMejor; //Mejor cromosoma encontrado hasta ahora.
	private Cromosoma elPeor;
	int pos_mejorGeneracion;   //Posicion del mejor cromosoma de la generacion
	
	private int[][] flujos;
	private int[][] distancias;
	private int size;
	int numMutaciones = 0, numCruzes = 0,numSelecionados = 0;
	

	public AlgoritmoGenetico(TipoMutacion tMut, TipoSeleccion tSel, TipoCruce tCrux,
							 int tPob, int nGeneracs, 
							 double pCruce, double pMutacion, double pUnif, String prec,
							 double elit) {
		funcion=new Funcion1();
		this.generacionActual = 0;
		this.mutacion = tMut;
		this.tipo_seleccion = tSel;
		this.tipo_cruce = tCrux;
		this.numGeneraciones = nGeneracs;
		//this.numGenes = nGenes;
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
		this.peoresAbsolutos = new double[nGeneracs];
		
		
	}
	
	public void inicializaPoblacion() {
		
		leerFich(precision);
		
		for(int i = 0; i < tamPoblacion; i++) {
			this.poblacion[i] = new Cromosoma(size);
		}
		
        this.elMejor = this.duplicarCromosoma(this.poblacion[0]);
        this.elPeor = this.duplicarCromosoma(this.poblacion[size - 1]);
        fitness(elMejor);
        fitness(elPeor);
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
		//int pos_fitness_best = 0;
		fitness_best = 100000;
		
		
		for(int i = 0; i < this.poblacion.length; i++) {
			fitness = fitness(this.poblacion[i]);
			sum_fitness += fitness;
		}
		
		ordenarPoblacion(0, this.tamPoblacion-1);

		
		fitness_best = this.poblacion[0].getFitness();
		
		//pos_fitness_best = (int)(elitismo*100.0d);
		if(fitness_best < elMejor.getFitness()) {
			elMejor = this.duplicarCromosoma(this.poblacion[0]); 
		}
		/////Esto es lo del peor
		for(int i = 0; i < this.poblacion.length; i++) {
			if(this.poblacion[i].getFitness() > elPeor.getFitness()) {
				elPeor = this.duplicarCromosoma(this.poblacion[i]); 
			}
		}
		 
		
		double puntuacion = 0, puntuacion_acu = 0;
		
		for(int i = 0; i < this.tamPoblacion; i++) {
			puntuacion = fitness(this.poblacion[i]) / sum_fitness;
			puntuacion_acu += puntuacion;
			this.poblacion[i].setPuntuacion(puntuacion);
			this.poblacion[i].setPuntAcumulada(puntuacion_acu);
		}
				
		this.mediasGeneracion[this.generacionActual] = sum_fitness / (int)this.poblacion.length;
		this.mejoresGeneracion[this.generacionActual] = fitness_best;
		this.mejoresAbsolutos[this.generacionActual] = this.elMejor.getFitness();
		this.peoresAbsolutos[this.generacionActual] = this.elPeor.getFitness();
	}
	
	public void seleccionaPoblacion() {
		
		switch(tipo_seleccion) {
		case ESTOCASTICO: 
			EstocasticoUniversal estocastUniv = new EstocasticoUniversal(this.poblacion, this.tamPoblacion);
			this.numSelecionados += estocastUniv.seleccionEstocastico(this.mutacion, this.numGenes);
			break;
		case RULETA: 
			Ruleta ruleta = new Ruleta(this.poblacion, this.tamPoblacion);
			this.numSelecionados += ruleta.seleccionRuleta();
			break;
		case TORNEO:
			Torneos torneos = new Torneos(this.poblacion, this.tamPoblacion);
			this.numSelecionados += torneos.seleccionTorneos(this.mutacion, this.numGenes);
			break;
		case RANKING: 
			Ranking ranking = new Ranking(this.poblacion, this.tamPoblacion, 2);
			this.numSelecionados += ranking.seleccionRanking();
			break;
		case TRUNCAMIENTO10: 
			truncamiento tr= new truncamiento(this.poblacion, this.tamPoblacion, true);
			this.numSelecionados += tr.seleccionaTruncamiento();
			break;
		case TRUNCAMIENTO50: 
			truncamiento tru= new truncamiento(this.poblacion, this.tamPoblacion, false);
			this.numSelecionados += tru.seleccionaTruncamiento();
			break;
		case SELPROPIA: 
			SelPropia slp= new SelPropia(this.poblacion, this.tamPoblacion);
			this.numSelecionados += slp.seleccionPropia();
			break;
		default:
			Ruleta r = new Ruleta(this.poblacion, this.tamPoblacion);
			this.numSelecionados += r.seleccionRuleta();
			break;
		}
	}
	

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
		case CRUCEPROPIO:
			cruce = new CrucePropio();
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

				this.numCruzes += this.tamPoblacion/2-1;
				Cromosoma[] hijos = cruce.cruzar(padre1.duplicarCromosoma(size), padre2.duplicarCromosoma(size));
				
				this.poblacion[padre1IndividuoIndex] = hijos[0];
				this.poblacion[padre2IndividuoIndex] = hijos[1];
				
			}
		}

		
		
		
	
	
	
	public void mutaPoblacion() {
		switch(mutacion) {
		case INSERCION:
				Insercion ins = new Insercion(probabilidadMutacion, tamPoblacion, poblacion);
				this.numMutaciones += ins.mutar();
			break;
		case INTERCAMBIO:
				Intercambio inte = new Intercambio(probabilidadMutacion, tamPoblacion, poblacion);
				this.numMutaciones += inte.mutar();
			break;
		case INVERSION:
				Inversion inv = new Inversion(probabilidadMutacion, tamPoblacion, poblacion);
				this.numMutaciones += inv.mutar();
			break;
		case HEURISTICA:
			Heuristica heu = new Heuristica(probabilidadMutacion, tamPoblacion, poblacion, flujos, distancias);
			this.numMutaciones += heu.mutar();
		break;
		case PROPIA:
			MutacionPropia prp = new MutacionPropia(probabilidadMutacion, tamPoblacion, poblacion);
			this.numMutaciones += prp.mutar();
		break;
		default:
			Insercion inse = new Insercion(probabilidadMutacion, tamPoblacion, poblacion);
			this.numMutaciones += inse.mutar();
		break;
		}
			
			
	}
	
	public void seleccionaElite() {
    	int num_elites = (int) (this.tamPoblacion * this.elitismo);
    	if (num_elites > this.tamPoblacion) num_elites = this.tamPoblacion;
    	this.ordenarPoblacion(0, this.tamPoblacion-1);
    	this.elite.clear();
    	
    	for (int i = 0 ; i < num_elites; i++)
    		this.elite.add(duplicarCromosoma(this.poblacion[i]));
	}
	
	public void incluyeElite() {
		int aux = 0;
    	this.ordenarPoblacion(0, this.tamPoblacion-1);
		for(int i = this.tamPoblacion-1; i > this.tamPoblacion-this.elite.size()-1 ; i--) {
			this.poblacion[i] = elite.get(aux);
			aux++;
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
    
    public String getPrecision() {
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
	public Cromosoma getPeor() {
		return duplicarCromosoma(this.elPeor);
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
			File file = new File("Ficheros/"+filepath); 
		    Scanner sc;
			try {
				sc = new Scanner(file);
				size = Integer.parseInt(sc.nextLine().trim().split("\\s+")[0]);
				sc.nextLine(); 
				distancias = leerMatrix(sc);
				sc.nextLine(); 
				flujos = leerMatrix(sc);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}    
	}

		public double[] getPeoresAbsolutos() {
			return peoresAbsolutos;
		}

		public void setPeoresAbsolutos(double[] peoresAbsolutos) {
			this.peoresAbsolutos = peoresAbsolutos;
		}


		public int getNumMutaciones() {
			return numMutaciones;
		}

		public void setNumMutaciones(int numMutaciones) {
			this.numMutaciones = numMutaciones;
		}

		public int getNumCruzes() {
			return numCruzes;
		}

		public void setNumCruzes(int numCruzes) {
			this.numCruzes = numCruzes;
		}

		public int getNumSelecionados() {
			return numSelecionados;
		}

		public void setNumSelecionados(int numSelecionados) {
			this.numSelecionados = numSelecionados;
		}

		

}
