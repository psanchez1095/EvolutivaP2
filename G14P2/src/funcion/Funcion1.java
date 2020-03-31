package funcion;

import java.util.ArrayList;

import cromosoma.Cromosoma;

public class Funcion1 {
	public static int evalua(int[][] f, int[][] d, ArrayList<Integer> fenotipo) {
		//ArrayList<Integer> fenotipo = cr.getFenotipo();
		int total = 0;
		for (int i = 0; i < fenotipo.size()-1; i++)
		{
			for (int j = 0; j < fenotipo.size()-1; j++)
			{
				total += d[i][j] * f[fenotipo.get(i)][fenotipo.get(j)];
			}
		}
		return total;
	}
}
