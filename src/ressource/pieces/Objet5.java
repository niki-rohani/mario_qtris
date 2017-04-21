package ressource.pieces;

import ressource.mdls.BlocModel;

public class Objet5 extends BlocModel {

	
	// PIECE N INV
	
	private static int C = 2;
	private static int L = 16;
	private static int state = 1;    
	private static int maxState = 2; 
	private static int x = 5;
	private static int[][] serial = { {0,x,0,0,x,x,0,0,5,0,0,0,0,0,0,0},{0,0,0,0,x,x,0,0,0,x,x,0,0,0,0,0} }; 
	
	
	
	
	public Objet5(int width) {
		super (L,C,width/2,0,serial,state,maxState);
		}

	
}