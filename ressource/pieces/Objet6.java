package ressource.pieces;

import ressource.mdls.BlocModel;

public class Objet6 extends BlocModel {

	
	// PIECE L INV
	
	private static int C = 4;
	private static int L = 16;

	private static int state = 1;
	private static int maxState = 4;
	private static int x =  1;
	
	private static int[][] serial = { {0,x,0,0,0,x,0,0,x,x,0,0,0,0,0,0},{0,0,0,0,x,x,x,0,0,0,x,0,0,0,0,0},{x,x,0,0,x,0,0,0,x,0,0,0,0,0,0,0},{0,0,0,0,x,0,0,0,x,x,x,0,0,0,0,0}}; 
	
	
	public Objet6(int width) {
		super (L,C,(width/2)-2,0,serial,state,maxState);
		}

	
}
