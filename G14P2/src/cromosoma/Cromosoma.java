	package cromosoma;

import java.util.ArrayList;
import java.util.Collections;


public class Cromosoma implements Comparable<Cromosoma>{
	
	ArrayList<Integer> fenotipo;  // mejor List<Integer>
	int longitud; //array de longitudes de cada gen del cromosoma
	//ArrayList<Double> fenotipo; 
    double fitness;
    double puntuacion;
    double punt_Acumulada;

    
    public Cromosoma(int longitud) {	
		this.fenotipo = new ArrayList<Integer>(); // esto es el genotipo , List<Integer> mejor
		this.longitud = longitud;
		this.fitness = 0.0;
		this.puntuacion = 0.0;
		this.punt_Acumulada = 0.0;
		generarGen();
	}
    
    private void generarGen() {
    	fenotipo = new ArrayList<Integer>();
		for (int i = 0; i < longitud; i++)
			fenotipo.add(i);
		Collections.shuffle(fenotipo);
    }
   public Cromosoma duplicarCromosoma(int size) {
	    Cromosoma nuevo = new Cromosoma(size) ;

	    nuevo.setFitness(this.getFitness());
	    nuevo.setFenotipo(this.getFenotipo());
	    nuevo.setPuntAcumulada(this.getPuntAcumulada());
	                
	    return nuevo;
	    }
    
    public ArrayList<Integer> getFenotipo(){
    	return this.fenotipo;
    } 
     
    public void setFenotipo(ArrayList<Integer> cromosoma) {
    	this.fenotipo = cromosoma;
    }

    
    
    public double getFitness() {
        return this.fitness;
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getPuntuacion() {
        return this.puntuacion;
    }
    
    public void setPuntuacion(double puntuacion) {
    	this.puntuacion = puntuacion;
    }
    
    public double getPuntAcumulada() {
        return this.punt_Acumulada;
    }
    
    public void setPuntAcumulada(double punt_Acumulada) {
        this.punt_Acumulada = punt_Acumulada;
    }
    
    public int getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(int longitud) {
    	this.longitud = longitud;
    }

    public double bin2dec(boolean[] genotipo) { // Cambia una cadena de booleanos de binario a decimal
        double total = 0;
        int j = 0;
        int bool;
        for (int i = genotipo.length - 1; i >= 0; i--) {
            if (genotipo[i]) 
                bool = 1;
            else 
                bool = 0;
            total += bool * Math.pow(2, j);
            j++;
        }
        return total;
    }
    
    
    
    //creo que esta mal   ?????????
	public double bin2float(boolean[] genotipo) { // // Cambia una cadena de booleanos de binario a decimal
		String geno = "";
		for(int i = 0; i < genotipo.length; i++) 
			geno += genotipo[i];
		
		int intBits = Integer.parseInt(geno, 2);
		float myFloat = Float.intBitsToFloat(intBits);
		return myFloat;
		//Integer.toBinaryString(i)
	}

    public int compareTo(Cromosoma cromosoma) {
    	return (int) (this.getFitness() - cromosoma.getFitness());
    }



	
	

}

