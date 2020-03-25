package cruce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cromosoma.Cromosoma;

public class CO implements Cruce{
	
		
	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> genotipo1 = in1.getFenotipo();
		List<Integer> genotipo2 = in2.getFenotipo();
		int size = genotipo1.size();
		List<Integer> linkedList1 = new LinkedList<>();
		List<Integer> linkedList2 = new LinkedList<>();
		List<Integer> linkedListFinal1 = new LinkedList<>();
		List<Integer> linkedListFinal2 = new LinkedList<>();
		List<Integer> linkedValues1 = new ArrayList<>();
		List<Integer> linkedValues2 = new ArrayList<>();
		ArrayList<Integer> genotipoSon1 = new ArrayList<>();
		ArrayList<Integer> genotipoSon2 = new ArrayList<>();

		
		for (int i = 0; i < size; i++)
		{
			linkedList1.add(i);
			linkedList2.add(i);
			linkedListFinal1.add(i);
			linkedListFinal2.add(i);
		}
		//Transformamos las listas a las representaciones correctas
		for (int j = 0; j < size; j++)
		{
			linkedValues1.add(extraerValor(linkedList1, genotipo1.get(j)));
			linkedValues2.add(extraerValor(linkedList2, genotipo2.get(j)));
		}
		int random = new Random().nextInt(size - 1);
		
		for (int j = 0; j < size; j++)
		{
			if (j <= random) {
				genotipoSon1.add(linkedListFinal1.get(linkedValues1.get(j)));
				linkedListFinal1.remove((int) linkedValues1.get(j));
				genotipoSon2.add(linkedListFinal2.get(linkedValues2.get(j)));
				linkedListFinal2.remove((int) linkedValues2.get(j));
			} else {
				genotipoSon1.add(linkedListFinal1.get(linkedValues2.get(j)));
				linkedListFinal1.remove((int) linkedValues2.get(j));
				genotipoSon2.add(linkedListFinal2.get(linkedValues1.get(j)));
				linkedListFinal2.remove((int) linkedValues1.get(j));
			}
		}
		
		Cromosoma newIndividuos[] = new Cromosoma[2];
		in1.setFenotipo(genotipoSon1);
		in2.setFenotipo(genotipoSon2);
		newIndividuos[0] = in1;
		newIndividuos[1] = in2;
		return newIndividuos;
		
	}
	private int extraerValor(List<Integer> list, int extraido)
	{
		int posExtraccion = 0;
		
		for (int i= 0; i < list.size(); i++)
		{
			
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
