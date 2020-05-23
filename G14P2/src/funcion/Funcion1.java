package funcion;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class Funcion1 {
	
	
	public static int evalua(int[][] f, int[][] d, ArrayList<Integer> fenotipo) {
		//ArrayList<Integer> fenotipo = cr.getFenotipo();
		int total = 0;
		for (int i = 0; i < fenotipo.size(); i++)
		{
			for (int j = 0; j < fenotipo.size(); j++)
			{
				total += d[i][j] * f[fenotipo.get(i)][fenotipo.get(j)];
			}
		}
		return total;
	}
}
