package ressource.pieces;

import ressource.mdls.BlocModel;

public class Objet3 extends BlocModel {

	
	// PIECE L
	
	private static int C = 4;
	private static int L = 16; 
	private static int state = 1;    
	private static int maxState = 4; 
	private static int x = 3;
	private static int[][] serial = { {x,0,0,0,x,0,0,0,x,x,0,0,0,0,0,0},{0,0,0,0,0,0,x,0,x,x,x,0,0,0,0,0},{x,x,0,0,0,x,0,0,0,x,0,0,0,0,0,0},{0,0,0,0,x,x,x,0,x,0,0,0,0,0,0,0}}; 
	
	public Objet3(int width) {
		super (L,C,width/2,0,serial,state,maxState);
		}

	
}