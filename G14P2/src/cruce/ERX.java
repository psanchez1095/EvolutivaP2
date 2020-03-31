package cruce;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class ERX implements Cruce {

	@Override
	public Cromosoma[] cruzar(Cromosoma in1, Cromosoma in2) {
		
		
		Cromosoma nuevosCromosomas[] = new Cromosoma[2];
		int size = in1.getFenotipo().size();
		ArrayList<Boolean> cambiados = new ArrayList<>(size); 	// para saber ciudades ya elegidas
		ArrayList<ArrayList<Integer>> conectividades = new ArrayList<>(size);// para la tabla
		ArrayList<Integer> hijo1 = new ArrayList<>(size); // array para guardar el hijo 1 generado
		ArrayList<Integer> hijo2 = new ArrayList<>(size); // array para guardar el hijo 2 generado
		
		for (int i = 0; i < size; i++) {// inicializa
			conectividades.add(null);
			cambiados.add(false);
		}

		
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> conexiones = new ArrayList<>(size);
			int ciudad = in1.getFenotipo().get(i);
			int posi1 = in1.getFenotipo().indexOf(ciudad);
			int posi2 = in2.getFenotipo().indexOf(ciudad);

			
			if (posi1 != 0) conexiones.add(in1.getFenotipo().get(posi1 - 1));
			if (posi1 != size - 1) conexiones.add(in1.getFenotipo().get(posi1 + 1));
			if (posi2 != 0 && !conexiones.contains(in2.getFenotipo().get(posi2 - 1))) conexiones.add(in2.getFenotipo().get(posi2 - 1));
			if (posi2 != size - 1 && !conexiones.contains(in2.getFenotipo().get(posi2 + 1))) conexiones.add(in2.getFenotipo().get(posi2 + 1));

			conectividades.set(ciudad, conexiones);
		}

		
		ArrayList<Integer> aux = new ArrayList<>(4);
		hijo1.add(in1.getFenotipo().get(0));
		cambiados.set(in1.getFenotipo().get(0), true);

		
		int intentos = size;

		for (int i = 0; i < size - 1 && intentos != 0; i++) {

			aux = conectividades.get(hijo1.get(i));// toma la conectividad de la ciudad 'i'
			int listasMenores[] = new int[4];// como máximo estará conectada con 4 ciudades
			int menor = 4;
			int m = 0;

			
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() <= menor && !cambiados.get(aux.get(j)) )
					menor = conectividades.get(aux.get(j)).size();
			}

			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() == menor && !cambiados.get(aux.get(j)) ) {
					listasMenores[m] = aux.get(j);
					m++;
				}
			}

			if (m > 1) {
				int take = (int) (Math.random() * m);
				hijo1.add(listasMenores[take]);
				cambiados.set(listasMenores[take], true);
			}
			
			else if (m == 1) {
				hijo1.add(listasMenores[0]);
				cambiados.set(listasMenores[0], true);
			}
			
			else {
				intentos--;
				hijo1 = new ArrayList<>(size);

				
				if (intentos == 0) {
					hijo1.add(null);
					hijo1 = in2.getFenotipo();
				}
				
				else {
					for (int j = 0; j < size; j++)
						cambiados.set(j, false);// limpia

					hijo1.add(in1.getFenotipo().get(0));
					cambiados.set(in1.getFenotipo().get(0), true);
					i = -1;
				}
			}
		}

		
		for (int j = 0; j < size; j++)
			cambiados.set(j, false);// limpiamos para el segundo hijo

		hijo2.add(in2.getFenotipo().get(0));
		cambiados.set(in2.getFenotipo().get(0), true);

		intentos = size;

		for (int i = 0; i < size - 1 && intentos != 0; i++) {

			aux = conectividades.get(hijo2.get(i));
			int listasMenores[] = new int[4];
			int menor = 4;
			int m = 0;

			
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() <= menor && !cambiados.get(aux.get(j)) )
					menor = conectividades.get(aux.get(j)).size();
			}

			
			for (int j = 0; j < aux.size(); j++) {

				if (conectividades.get(aux.get(j)).size() == menor && !cambiados.get(aux.get(j)) ) {
					listasMenores[m] = aux.get(j);
					m++;
				}
			}

			// elegimos una aleatoria si hay más de 1, y se añade
			if (m > 1) {
				int take = (int) (Math.random() * m);
				hijo2.add(listasMenores[take]);
				cambiados.set(listasMenores[take], true);
			}
			
			else if (m == 1) {
				hijo2.add(listasMenores[0]);
				cambiados.set(listasMenores[0], true);
			}
			
			else {
				intentos--;
				hijo2 = new ArrayList<>(size);
				
				if (intentos == 0) {
					hijo2.add(null);
					hijo2 = in1.getFenotipo();
				}
				
				else {
					for (int j = 0; j < size; j++)
						cambiados.set(j, false);// limpia

					hijo2.add(in2.getFenotipo().get(0));
					cambiados.set(in2.getFenotipo().get(0), true);
					i = -1;
				}
			}
		}
		
		in1.setFenotipo(hijo1);
		in2.setFenotipo(hijo2);
		nuevosCromosomas[0] = in1;
		nuevosCromosomas[1] = in2;

		return nuevosCromosomas;
	}

}
