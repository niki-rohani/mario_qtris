package IA;

import graphiqueInterface.VueTetris;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ressource.GameState;
import ressource.mdls.BlocModel;
import ressource.mdls.Matrice;
import ressource.mdls.Player;

public class StratIA extends StratInterface {



	private boolean trouve = false;

	private int [][] coup = {{0,0,1},{1,0,1},{-1,0,1}, {0,1,1},{0,-1,0} };

	private BlocModel pieceCurrent;

	private int XtoGo, YtoGo;

	private int rotationToGo;

	public static int firstPiece = 0;

	private double maxNote = -10000000;
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getAction(GameState state) {
		
	

		if (!trouve) {
			pieceCurrent = state.getPiece();

			rotationToGo = 0;
			XtoGo = 0  ;


			// Test de la piece dans toute les postions X, et tou rotation
			for(int i=0; i<state.getBoard().getWidth() ; i++) {

				// Copie de state
				GameState s = new GameState();
				s = state.copy();

				// Position de la piece en x = i;
				s.getPiece().setPosition(  i , state.getPiece().getY());

				GameState temp;

				int k = 0;


				// Rotation de la piece dans toute les rotations
				for (int j = 0; j < s.getPiece().maxState(); j++ ) {
					if (i + s.getPiece().maxL() < s.getBoard().getWidth() ) {
						// Test de la piece dans la position X = i et r = j
						temp = calculStateCoup(s, coup[0]);
						double n = Dellarchie.calcul(temp);

						// Si la note max est inf a n, on met n dans maxNote et on modifie les coordonnées ou aller
						if (maxNote != n) {
							if (Math.max(maxNote, n)==n) {
								if (go(state, s.getPiece().getX(), j)) {
									XtoGo = s.getPiece().getX();
									YtoGo = s.getPiece().fictif(s.getBoard()).getY();
									rotationToGo = j;

									maxNote = n;
								}
							}
						}
						k = k + 1;
					}
					s.getPiece().nextState();
				}
			}
			trouve = true;

		


 		if (state.getHoldNb() == 0) {
 			for(int i=0; i<state.getBoard().getWidth() ; i++) {
 				GameState s = new GameState();
 				s = state.copy();
 				if (state.getHold() != null)
 					s.setPiece(state.getHold().copy());
 				else {
 					s.NextPiece();
 				}
 
 				s.getPiece().setPosition(  i , 0);
 				GameState temp;
 
 				int k = 0;
 
 				for (int j = 0; j < 4; j++ ) {
 					if (i + s.getPiece().maxL() < s.getBoard().getWidth() ) {
 						temp = calculStateCoup(s, coup[0]);
 						double n = Dellarchie.calcul(temp);
 
 
 						if (maxNote != n) {
 							if (Math.max(maxNote, n)==n) {
 								
 								if (go(state, i, j)) {
 									XtoGo = -1000;
 									YtoGo = -1000;
 									rotationToGo = -10000;
 
 
 									maxNote = -1000000;
 									trouve = false;
 									System.out.println(trouve);
 									break;
 
 								}
 							}
 						}
 						k = k + 1;
 					}
 					s.getPiece().nextState();
 				}
 				if (maxNote < -10000)
 					break;
 			}
 		}

	

		}
	

	if (firstPiece==0) {
		pieceCurrent = state.getPiece();
		firstPiece++;
	}
	if (!state.getPiece().equals(pieceCurrent)) {
		trouve = false;
		maxNote = -100000;
	}



	return	 coup [ getActionTo(state) ]  ;




}

public GameState calculStateCoup(GameState state, int [] coup) {
	GameState s = new GameState(); 
	s = state.copy();
	if (s.getBoard().equals(state.getBoard()))
		System.exit(0);
	s.computeF(coup);
	s.computeF2(coup);
	return s;
}



public void meilleurChemin (ArrayList<Double> score, Noeud feuille, ArrayList <ArrayList <Integer>> chemin) {
	feuille.nbVide(score, new ArrayList<Integer>  () , chemin);
}

public int getActionTo (GameState s) {

	if (XtoGo == -1000 && YtoGo == -1000) {
		
		trouve = false ;
		return 4;
	}
	if (rotationToGo>0) {
		rotationToGo = rotationToGo - 1;
		return 3;
	}
	else if (s.getPiece().getX()>XtoGo)
		return 2;
	else if (s.getPiece().getX()  < XtoGo )
		return 1;

	else 
		return 0;
}



public boolean go (GameState s, int X, int r) {
	int rest = s.getPiece().fictif(s.getBoard()).getY() - s.getPiece().getY();
	int nbA = s.getPiece().getX() - X;
	nbA = Math.abs(nbA);
	if (rest < 3 && r>0)
		return false;
	else {
		nbA = nbA + r;
		if (nbA > rest)
			return false;
		return true;
	}


}

}
