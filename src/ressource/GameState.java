package ressource;
import ressource.Matrice.GameBoard;
import ressource.mdls.BlocModel;
import ressource.mdls.Matrice;
import ressource.mdls.Player;
import ressource.pieces.Objet1;
import ressource.pieces.Objet2;
import ressource.pieces.Objet3;
import ressource.pieces.Objet4;
import ressource.pieces.Objet5;
import ressource.pieces.Objet6;
import ressource.pieces.Objet7;

import java.util.ArrayList;


public class GameState {

	private Matrice gameBoard=null;
	private ArrayList <BlocModel> list=null;
	private BlocModel piece=null;
	private Player player;
	private int nombreDeLigneDernierCoup=0;
	private int nombreDecelluleDernierPiece=0;
	private int hauteurDeDernierePiece=0;
	public int step = 0;
	public int step2 = 0;
	public int line = 0;
	
	
	private boolean isLine = false;
	private int nbPiece = 6;
	
	public int lastPieceX, lastPieceY;
	public BlocModel lastPiece;
	public boolean start = false;
	
	private int holdNb = 0;
	private BlocModel hold  ;
	
	private int level;
	private int speed;
	
	private boolean end = false;
	
	private int ligneAdverses = 0;
	private int ligneEnvoyer = 0  ;
	
	ArrayList <double[]> lignePiece = new ArrayList <double[]> ();

	
	
	
	public GameState () {
		gameBoard = new GameBoard();
		list = new ArrayList <BlocModel> ();
		piece = null;
		player = new Player();
		level = 1;
		speed = 60;
	}
	
	public GameState (Matrice gameBoard2, ArrayList <BlocModel> b, BlocModel c, Player d, int e, int f, int h) {
		gameBoard = gameBoard2;
		list = b;
		piece = c;
		player = d;
		nombreDecelluleDernierPiece = e;
		nombreDeLigneDernierCoup = f;
		hauteurDeDernierePiece = h;
		level = 1;
		speed = 30;
	}
	
	public void InitialiseList() {
		nombreDeLigneDernierCoup = 0;
		nombreDecelluleDernierPiece = 0;
		hauteurDeDernierePiece = 0;
		ArrayList <BlocModel> piece = new ArrayList <BlocModel> ();
		piece.add(new Objet1(this.gameBoard.getWidth()));
		piece.add(new Objet2(this.gameBoard.getWidth()));
	//	piece.add(new Objet3(this.gameBoard.getWidth()));
		piece.add(new Objet4(this.gameBoard.getWidth()));
		piece.add(new Objet5(this.gameBoard.getWidth()));
		piece.add(new Objet6(this.gameBoard.getWidth()));
		piece.add(new Objet7());
		
		for (int i = 0; i < 5 ; i++) {
			list.add( piece.get ( (int)  ( Math.random() * nbPiece  ) ) );
		}
		this.piece = piece.get ( (int)  Math.random() * nbPiece  );
		
		
	}
	
	public void restart() {
		end = false;
		gameBoard = new GameBoard();
		list = new ArrayList <BlocModel> ();
		piece = null;
		nombreDecelluleDernierPiece = 0;
		nombreDeLigneDernierCoup = 0;
		hauteurDeDernierePiece = 0;
		level = 1;
		speed = 30;
		InitialiseList();
		player = new Player() ;
		start = true;
	}
	
	public void LigneAdverse() {
		int ligneAdverse = ligneAdverses - ligneEnvoyer;
		ligneEnvoyer = ligneEnvoyer - ligneAdverses;
		ligneAdverses = ligneAdverse;
		if (ligneAdverses < 0)
			ligneAdverses = 0;
		if (ligneEnvoyer < 0)
			ligneEnvoyer = 0;
		if (nombreDeLigneDernierCoup > 1)
		ligneEnvoyer = nombreDeLigneDernierCoup-1;
		if (nombreDeLigneDernierCoup - 4 > - 1)
		ligneEnvoyer = ligneEnvoyer + 3;
	}
	
	
	/**
	 *  Methode qui place la piece suivante dans la pile
	 */
	public void NextPiece () {
		LigneAdverse();
		this.getBoard().addLine(ligneAdverses);
		ArrayList <BlocModel> piece = new ArrayList <BlocModel> ();
		piece.add(new Objet1(this.gameBoard.getWidth()));
		piece.add(new Objet2(this.gameBoard.getWidth()));
	//	piece.add(new Objet3(this.gameBoard.getWidth()));
		piece.add(new Objet4(this.gameBoard.getWidth()));
		piece.add(new Objet5(this.gameBoard.getWidth()));
		piece.add(new Objet6(this.gameBoard.getWidth()));
		piece.add(new Objet7());
		this.piece = list.get(0);
		list.set(0, list.get(1));
		list.set(1, list.get(2));
		list.set(2, list.get(3));
		list.set(3, list.get(4));
		list.set(4, piece.get ( (int) ( Math.random() * nbPiece )  ));
	}
	
	
	/**
	 *  Initialise les listes de Piece
	 */
	public void initialise () {
		InitialiseList();
		NextPiece();
	}
	
	public Matrice getBoard () {
		return gameBoard;
	}
	
	public ArrayList <BlocModel>getList() {
		return list;
	}
	
	public BlocModel getPiece() {
		return piece;
	}
	
	public void setBoard (Matrice matrice) {
		this.gameBoard = matrice;
	}

	public void setNewPlayer(Player p) {
		player = p;
	}
	
	
	
	public void setSpeed(int i) {
		speed = i;
	}
	
	public void setPiece(BlocModel p) {
		piece = p;
	}
	
	
	
	public Player getPlayer() {
		return player ;
	}


	public void refresh(int r[], int hauteur) {
		player.score(r[0]);
		nombreDeLigneDernierCoup = r[0];
		nombreDecelluleDernierPiece = r[1];
		hauteurDeDernierePiece = hauteur;
		line = r[2];
		if (r[2]>0) isLine = true;
		else isLine = false;
		
		
	}

	public int numberLine() {
		return nombreDeLigneDernierCoup;
	}
	
	public int numberCell() {
		return nombreDecelluleDernierPiece;
	}

	public double dernierCoupHauteur() {
		return hauteurDeDernierePiece;
	}
	
	public void line(int i) {
		double [] r = {i, 200, i};
		lignePiece.add(r);
	}
	
	public void computeF(int[] action) {
		
		if (action[2] > 0)
			step2 = 0;
		if (step2  >= 23 ) step2 = 0;
		
		// On definie la piece precedente
		lastPiece = getPiece().copy();
		int hauteur = 0;
		
		// rotation
		if (action[1]  > 0) { if ( getBoard().rotate(getPiece())) getPiece().rotate();}
		// Changement de piece
		if (action[1]< 0 && holdNb == 0) { if (hold !=null) { BlocModel h = hold; hold = getPiece(); piece = h; } else {   hold = getPiece(); NextPiece(); } hold.restart(); holdNb = 1; } 
		else {
		
		// Action a effectuer
		int actionFinal=action[0];
		int chut;
		if (step2==0)
		chut=1;
		else
		chut=0;
		
		// Si la piece bloque en base on stop les chut
		if (getBoard().bloque_bas(getPiece())) { chut = 0;}
		
		// Si la piece bloque a gauche on empeche qu'elle aille loin
		if (getBoard().bloque_left(getPiece(), chut,-1) && action[0] == -1) {
				actionFinal = 0; 
			}
		
		// Cf gauche
		if (getBoard().bloque_droite(getPiece(), chut,1) && action[0] == 1) {
				actionFinal = 0 ;
		}
		
		// On ajoute un vecteur a cette piece
		getPiece().addVectorPosition(actionFinal, chut);
		
		}
		
	}
	
	

	
	public void computeF2(int [] action) {
		if (!(action[1]< 0 && holdNb == 0)) {
		BlocModel f = getPiece().fictif(gameBoard);
		int hauteur = 0;
		int a = getBoard().place(f, 70);
		if(a>0){
			holdNb = 0;
			lastPiece = getPiece().copy();
			lastPieceX = lastPiece.getX();
			lastPieceY = lastPiece.getY();
			hauteur = a;
			NextPiece();
			step = 0;
		}
		
		 int rr[] = getBoard().checkLine(lastPiece);
		this.refresh(rr, hauteur);
		}
		else {
		int rr[] = {0,0,0};
		this.refresh(rr, this.hauteurDeDernierePiece);
		}
		
	}
	public void compute(int[] action) {
		
		// Permet de savoir combien d etat se sont passé entre les different appel de la fonction
		step2++;
		if (action[2] > 0)
			step2 = 0;
		if (step2  >= 23 ) step2 = 0;
		int hauteur = 0;
		// rotation
		if (action[1]  > 0) { if ( getBoard().rotate(getPiece())) getPiece().rotate();}
		
		// Changement de piece
		if (action[1]< 0 && holdNb == 0) { if (hold != null) { BlocModel h = hold; hold = getPiece(); piece = h; } else {   hold = getPiece(); NextPiece(); } hold.restart(); holdNb = 1;  } 
		else {
		
		// On place la piece dans le plateau
		int a = getBoard().place(getPiece(), step);
		
		// Placement réussi
		if(a>0){
			holdNb = 0;
			lastPiece = getPiece().copy();
			lastPieceX = lastPiece.getX();
			lastPieceY = lastPiece.getY();
			hauteur = a;
			NextPiece();
			step = 0;
		}
		
		// Placement raté a cause d'un nombre d'etat non atteint
		else if (a==0) step++; 
		
		// Placement raté
		if (a == -1) step = 0;
		if (a==-1 || a == 0) {
			int actionFinal=action[0];
			int chut;
			if (step2==0)
			chut=1;
			else
			chut=0;
			if (getBoard().bloque_bas(getPiece())) { chut = 0;}
			if (getBoard().bloque_left(getPiece(), chut,-1) && action[0] == -1) {
					actionFinal = 0; 
				}
			if (getBoard().bloque_droite(getPiece(), chut,1) && action[0] == 1) {
					actionFinal = 0 ;
			}
		
		getPiece().addVectorPosition(actionFinal, chut);
		
}

		// Check le nb de ligne faites
		int r[] = getBoard().checkLine(lastPiece, this);
		
		// On refresh les informations
		this.refresh(r, hauteur);
		
		// On test si on a perdu
		for (int i = 0; i < getBoard().getWidth(); i++) {
			if (getBoard().getMatrice()[i][0] > 0)
				end = true;
			if (getBoard().getMatrice()[i][1] > 0)
				end = true;
			if (getBoard().getMatrice()[i][2] > 0)
				end = true;
			if (getBoard().getMatrice()[i][3] > 0)
				end = true;
			
		}
		}
	}

	public GameState copy() {
		Matrice gameBoard1 = this.gameBoard.copy();
		ArrayList list1 = new ArrayList();
		for (int i = 0; i < this.list.size(); i++)
			list1.add((BlocModel) (  this.list.get(i)).copy());
		BlocModel piece1 = this.piece.copy();
		Player player1;
		
			player1 = player.copy() ;
	
		GameState s = new GameState(gameBoard1,list1, piece1, player1, this.nombreDecelluleDernierPiece,this.nombreDeLigneDernierCoup,this.hauteurDeDernierePiece);
	return s;
	}

	
	
	
	public BlocModel getHold() {
		return hold;
	}
	
	public int getHoldNb() {
		return holdNb;
	}
	
	public boolean isEnd() {
		return end;
	}
	
	public boolean start() {
		return start;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void levelUp() {
		level = level + 1;
		speed = speed - 10;
		if (speed < 1)
			speed = 1;
	}
	
	
}
