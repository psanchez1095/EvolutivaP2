package cruce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import cromosoma.Cromosoma;

public class CO implements Cruce{
	
	@Override
	public Cromosoma[] cruzar(Cromosoma cromo1, Cromosoma cromo2){
		// TODO Auto-generated method stub
		
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		List<Integer> linkedList1 = new LinkedList<>();
		List<Integer> linkedList2 = new LinkedList<>();
		List<Integer> linkedListFinal1 = new LinkedList<>(); // list con valores de 0 a size - 1 consecutivos
		List<Integer> linkedListFinal2 = new LinkedList<>(); // list con valores de 0 a size - 1 consecutivos
		List<Integer> linkedValues1 = new ArrayList<>(); // indica en que posicion de linkedListFinal se encuentra el valor de in1 en dicha posición
		List<Integer> linkedValues2 = new ArrayList<>();
		ArrayList<Integer> fenotipoSon1 = new ArrayList<>();
		ArrayList<Integer> fenotipoSon2 = new ArrayList<>();
		
		//in1              3 4 5 2 1
		//linkedListFinal1 1 2 3 4 5
		//linkedValues1    2 3 4 1 0
		
		for (int i = 0; i < cromo1.getFenotipo().size(); i++) {
			// Inicializamos las linked list con valores de 0 a size - 1 consecutivos
			linkedList1.add(i);
			linkedList2.add(i);
			linkedListFinal1.add(i);
			linkedListFinal2.add(i);
			
		}
		
		for (int j = 0; j < cromo1.getFenotipo().size(); j++){
			
			linkedValues1.add(extraerPos(linkedList1, cromo1.getFenotipo().get(j)));
			linkedValues2.add(extraerPos(linkedList2, cromo2.getFenotipo().get(j)));
			
		}
		
		int aleatorio = new Random().nextInt( cromo1.getFenotipo().size() - 1);
		
		for (int j = 0; j <  cromo1.getFenotipo().size(); j++){
			
			if (j > aleatorio){
				
				fenotipoSon1.add(linkedListFinal1.get(linkedValues2.get(j)));
				fenotipoSon2.add(linkedListFinal2.get(linkedValues1.get(j)));
				
			} else {
				
				fenotipoSon1.add(linkedListFinal1.get(linkedValues1.get(j)));
				fenotipoSon2.add(linkedListFinal2.get(linkedValues2.get(j)));
				
			}
			
		}
		
		cromo1.setFenotipo(fenotipoSon1);
		cromo2.setFenotipo(fenotipoSon2);
		nuevosCromosomas[0] = cromo1;
		nuevosCromosomas[1] = cromo2;
		
		return nuevosCromosomas;
		
	}
	
	private int extraerPos(List<Integer> list, int extraido){
		
		int posExtraccion = 0;
		
		for (int i= 0; i < list.size(); i++){
			
			if (list.get(i) == extraido)
			{
				list.remove(i);
				posExtraccion = i;
				break;
			}
			
		}
		
		return posExtraccion;
	}

}
