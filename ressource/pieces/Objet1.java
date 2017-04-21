package ressource.pieces;

import ressource.mdls.BlocModel;

public class Objet1 extends BlocModel {

	
	// PIECE  I
	private static int maxState = 3;
	private static int C = maxState;
	private static int L = 16;

	private static int state = 1;
	private static int x =  1;
	private static int[][] serial = {{x,0,0,0,x,0,0,0,x,0,0,0,x,0,0,0},{x,x,x,x,0,0,0,0,0,0,0,0,0,0,0,0},{0,x,0,0,0,x,0,0,0,x,0,0,0,x,0,0}};	
	
	public Objet1(int width) {
		super (L,C,width-4,0,serial,state,maxState);
		}

	
}