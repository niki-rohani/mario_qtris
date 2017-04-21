package ressource.mdls;

import ressource.pieces.Objet4;

/**
 * 
 * @author Danti(dot)
 * Classe heritant de Matrice, Elle correspond a un modele des pieces.
 * une piece est une matrice, elle contient en plus les information tel que la taille du tableau en fonction de l'etat
 */
public class BlocModel extends Matrice {
	
	/** maxState : Corresponds au nombre d'etat maximum */
	protected int maxState;
	/** int state : Corresponds a l'etat de la piece */
	protected int state;
	
	/**
	 * 
	 * @param heigth : correspond a la hauteur du tableau
	 * @param width : correspond a la largeur
	 * @param x : position en nombre de ligne de la piece
	 * @param y : position en nombre de co
	 * @param m : matrice de points de la piece
	 * @param stateC : hauteur du tableau en fonction de l'etat
	 * @param stateL : largeur du tableau en fonction de l'etat
	 * @param state : etat
	 * @param maxState : etat maximum
	 */
	public BlocModel (int heigth, int width, int x, int y, int[][] m, int state, int maxState) {
		super (heigth, width,x,y,m);
		this.state = state;
		this.maxState = maxState;
	}
	
	
	public BlocModel() {
		// TODO Auto-generated constructor stub
	}


	public int getState() {
		return state;
	}
	
	
	public int[][] getMatrice() {
		int [] mt = null;
		int [][] m = null;
		
		m = new int [(int) Math.sqrt(heigth)][(int) Math.sqrt(heigth)];
		
		mt = matrice[state-1].clone();
		

		
	
		
		for (int i = 0; i < heigth; i++) {
			m[((i%(int) Math.sqrt(heigth)))][(int) ( i / Math.sqrt(heigth)  )] =
					mt[i];
		}
		
		
		/*for (int j=1; j<i;j++) {
			int [][] mt = new int [l][c];
			for (int z=0; z<c; z++ )
				for (int k=0; k<l; k++ ){
						mt[Math.abs(k-(l-1))]
								[z] =
						m[z][k];
				}
			m = mt.clone();
			int t = l;
			l = c;
			c = t;
		}*/
		
		
		return m;
	
	}
	
	public void restart() {
		x = 0;
		y = 0;
		state = 1;
	}
	
	public int getWidth() {
		return (int)Math.sqrt(heigth);
	}
	
	public int getHeigth() {
		return (int)Math.sqrt(heigth);
	}

	
	/**
	 *  Permet de tourner la piece dans un sens
	 */
	public void nextState() {
		if (this.state == this.maxState) 
			this.state = 1;
		else
			this.state = this.state + 1;
	}
	
	
	
	
	
	public BlocModel copy() {
		BlocModel copy = new BlocModel (heigth, width, x, y, matrice, state, maxState);
		return copy;
	}
		
	public String toString() {
		String toString = "Mod STATE = " + state + " \n" ;
		toString = toString + "X : " + x + " Y : " + y + "\n";
		for (int i=0; i<getHeigth(); i++ ){
			for (int j=0 ; j<getWidth(); j++ ){
				if (this.getMatrice() [j][i] >= 1)
				toString += "O";
				else
				toString += ".";
				
			}
		toString +="\n";
		}
		return toString;
	}


	public void rotate() {
		nextState();
	}
	
	public int maxL() {
		int r = 0;
		int rr = 0;
		for (int i=0;i<getMatrice().length;i++) {
			for (int j = 0; j<getWidth(); j++) {
			// La case de la pièce est-elle remplie ?
			if (getMatrice()[j][i] !=0) 
					     rr = rr+1;		
			
			}
			r = Math.max(r, rr);
			rr = 0;
			}
		return r;
	}
	
	public BlocModel fictif (Matrice board) {
		BlocModel m = new Objet4(board.width);
		m = copy();
		while(true){
		if(board.bloque_bas(m))
			return m;
		m.addVectorPosition(0, 1);
		}
	
	}
	
	public void set(BlocModel m) {
		super.set(m);
		maxState = m.maxState();
		state = m.state;
	}
	
	public int maxState() {
		return maxState;
	}
	
	public int state() {
		return state;
	}
}
