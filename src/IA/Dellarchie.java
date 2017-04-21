package IA;

import java.util.ArrayList;

import ressource.GameState;

public class Dellarchie {


	private static double x1=1,x2=1,x3=1,x4=1,x5=4,x6=1;
	
	
	public static double calcul (GameState state) {
		double e = state.numberLine() * state.numberCell();
		double l = state.dernierCoupHauteur();
		double dr = state.getBoard().transitionHorizontale();
		double dc = state.getBoard().transitionVerticale();
		double L = state.getBoard().trou();
		double W = 0;
		ArrayList r = state.getBoard().puits();
		
		for (int k = 0; k < r.size(); k++)
			W = W + (Integer) ( r.get(k) );
		
		if (l > state.getBoard().getHeigth()-4)
			l = 4000000;
	 	
		return ((-(x1))*l)+(x2*e)-(x3*dr)-(x4*dc)-(x5*L)-(x6*W);
	}

	
	
}
