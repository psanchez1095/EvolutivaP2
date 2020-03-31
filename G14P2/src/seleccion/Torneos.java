package seleccion;

import cromosoma.Cromosoma;
import utils.TipoMutacion;

public class Torneos {
	private Cromosoma[] pob, nuevaPob;
	private int tamPob;
	
	public Torneos (Cromosoma[] pob, int tamPob) {
		this.pob = pob;
		this.nuevaPob = new Cromosoma[tamPob];
		this.tamPob = tamPob;
	}
	

	public void seleccionTorneos(TipoMutacion tFunc, double prec, int nGenes) {
        int posicionElegido = -1;
        double [] fitnessIndiv = new double[tamPob];
        for (int i = 0; i < this.tamPob; i++) {
            //calculamos el total
        	fitnessIndiv[i] = this.pob[i].getFitness();
        }
        
        for (int i = 0; i < this.tamPob; i++) {
        	int aleatorio = (int) (Math.random() * this.tamPob);
        	double mejor = fitnessIndiv[aleatorio];
        	posicionElegido = aleatorio;
                
            for (int j = 1; j < tamPob; j++) {
                aleatorio = (int) (Math.random() * this.tamPob);
                if (compararFitness(fitnessIndiv[aleatorio], mejor, tFunc) != mejor) {
                    mejor = fitnessIndiv[aleatorio];
                    posicionElegido = aleatorio;
                }
            }
            nuevaPob[i] = duplicarCromosoma(this.pob[posicionElegido], tFunc, prec, nGenes);
        }
        this.pob = nuevaPob;

	}
	
	
	
	private double compararFitness(double uno, double otro, TipoMutacion tFunc) {
		if(uno < otro) return uno;
			  else return otro;
	}
	
	private Cromosoma duplicarCromosoma(Cromosoma c, TipoMutacion tipoFuncion, double precision, int numGenes) {
	    Cromosoma nuevo = new Cromosoma(c.getLongitud());
    	nuevo.setFenotipo(c.getFenotipo());
        nuevo.setFitness(c.getFitness());
        nuevo.setPuntAcumulada(c.getPuntAcumulada());
        nuevo.setPuntuacion(c.getPuntuacion());
	    return nuevo;
	}
}
