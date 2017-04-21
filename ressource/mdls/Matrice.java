package ressource.mdls;
import graphiqueInterface.Animation.Animation;

import java.awt.List;
import java.util.ArrayList;

import ressource.GameState;



import AudioGame.Music;
import Tool.GameTool ;

public class Matrice {
	
	// attribut de hauteur largeur et position (0,0) pour la matrice de jeu
	protected int heigth, width, x, y;
	
	// Matrice de point de la matrice
	protected int [] [] matrice;
	
	
	// Constructeur par defaut
	public Matrice() {
		heigth = 10;
		width = 20;
		x = 0;
		y = 0;
		matrice = new int [width][heigth];
	}
	
	
	public Matrice(int heigth,int width, int x, int y) {
		matrice = new int[width][heigth];
		this.heigth = heigth;
		this.width = width;
		this.x = x;
		this.y = y;
		initMatrice(false);
	}
	
	public Matrice(int heigth,int width, int x, int y, boolean b) {
		matrice = new int[width][heigth];
		this.heigth = heigth;
		this.width = width;
		this.x = x;
		this.y = y;
		initMatrice(b);
	}
	
	public Matrice(int heigth,int width, int x, int y, int [][] m) {
		matrice = new int [width][heigth];
		this.heigth = heigth;
		this.width = width;
		this.x = x;
		this.y = y;
		for (int i = 0 ; i < width; i ++)
			for (int j = 0 ; j < heigth ; j++)
				matrice[i][j] = m[i][j];
	}
	
	public Matrice(int[][] m) {
		matrice = m.clone();
		heigth = m[0].length;
		width = m.length;
		x = 0;
		y = 0;
	}


	public void initMatrice(boolean b) {
		for (int i=0; i<width;i++)
			for (int j=0; j<heigth; j++) {
				if (j==heigth-1 && b)
				matrice[i][j]=23;
				else
				matrice[i][j]=0;
			}
	}
	
	/**
	 * GetX() : Renvoi la valeur de position X
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * GetY() : Renvoi la valeur de position Y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * GetPosition() : Renvoi la valeur de position sous forme de tableau de int
	 */
	public int[] getPosition() {
		int[] r = {x,y};
		return r;
	}
	
	/**
	 * GetMatrice() : Renvoi la matrice sous forme de tableau 2d de int
	 */
	public int[][] getMatrice() {
		return matrice;
	}
	
	/**
	 * GetPoint() : Renvoi la valeur de la matrice aux position x y
	 */
	public int getPoint (int x, int y) {
		return matrice[x][y];
	}
	
	/**
	 * GetLigne() : Renvoi la matrice de la ligne y sous forme d'un tableau
	 */
	public int[] getLigne (int y) {
		int [] r = new int[width];
		for (int i=0; i<width; i++ ) {
		r[i] = matrice [i][y];	
		}
		return r;
		}
	
	/**
	 * GetColumn() : Renvoi la matri de la colonne y sous forme d'un tableau
	 */
	public int[] getColumn (int x) {
		int [] r = new int[heigth];
		for (int i=0; i<heigth; i++ ) {
		r[i] = matrice [x][i];	
		}
		return r;
	}
	
	/**
	 * GetHeigth() : Renvoi la valeur de la hauteur
	 */
	public int getHeigth () {
		return heigth;
	}
	public int getWidth () {
		return width;
	}
	
	/**
	 * setPosition() : Mettre la position en x,y
	 */
	public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addVectorPosition (int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	/**
	 * isEmpty() : check si la case x y est vide
	 */
	public boolean isEmpty (int x, int y) {
		if (getPoint(x,y)>0)
			return false;
		return true;
	}
	
	/**
	 * isLigneEmpty() : check si la ligne y est vide
	 */
	public boolean isLigneEmpty (int y) {
		for (int i=0; i<width; i++ ) {
			if (!isEmpty(i,y)) return false;
		}
		return true;
	}
	
	/**
	 * isIntersect() : check la matrice m est en intersection avec la matrice
	 */
	public int isIntersect (BlocModel m, int ax, int ay) {
		int[][] piecem = m.getMatrice().clone();
		int x = m.getX();
		int y = m.getY();
		int h = m.getHeigth();
		int w = m.getWidth();
		
		
		for (int i = 0; i < h; i++ )
			for (int j = 0 ; j < w; j++ )
				if (this.matrice[j+x+ax][i+y+ay]>=1&&piecem[j][i]>=1) {
					if (i+y+ay > i+y)
					return 1;
					else if (j+x+ax<j+x)
					return 2;
					else
					return 3;
				}
		 return 0;
	}
	
	

	
	/**
	 * addMatrice() : ajoute la matrice m a la matrice au position x y
	 */
	public int addMatrice(BlocModel m) {
		    
			int[][] piecem = m.getMatrice().clone();
			int hauteur = 0;
			for (int i = 0; i < m.getHeigth(); i++ )
				for (int j = 0 ; j < m.getWidth(); j++ ) {
					if (piecem[j][i]>=1){
					if (i + m.getY() >= heigth) {
						matrice[j + m.getX()] [heigth - i - 2]=piecem[j][i];
						hauteur = m.H();
					}
					else {
						matrice[j + m.getX()] [i + m.getY()]=piecem[j][i];
						hauteur = m.H();
				//		System.out.println(m.fictif(this).getX());
					}
					}
					
				}
			
			return heigth - hauteur;
	}
	
	

	public String toString() {
		String toString = "MATRICE \n";
		for (int i=0; i<matrice.length; i++ ){
			for (int j=0 ; j<matrice[i].length; j++ ){
				if (matrice [i][j] >= 1)
				toString += matrice [i][j];
				else
				toString += ".";
				
			}
		toString +="\n";
		}
		return toString;
	}
	
	/*
	public boolean isPlacable(BlocModel m) {
		return (!isOutofBound(m) && !isIntersect(m));
	}*/
	
	
	
	/*public boolean checkBlock(BlocModel m, int nextActionX, int nextActionY) {
		BlocModel t = m.copy();
		t.addVectorPosition(nextActionX, nextActionY);
		if(isPlacable(t))
			return false;
		else if (isIntersect(t))
			return true;
		else
			return false;
		
	}*/
	
	
	public int place(BlocModel m, int step) {
		if (bloque_bas(m)) {
			if (step>23) {
				int h = addMatrice(m);
				step = 0;
				return h;
			}
			else {
				step++;
				return 0;
			}
		}
		return -1;
	}
	
	public boolean bloque_droite(BlocModel m, int ay, int droite)
	{
		{
		for (int i=0;i<m.getMatrice().length;i++) {
			for (int j = 0; j<m.getWidth(); j++) {
			// La case de la pièce est-elle remplie ?
			if (m.getMatrice()[j][i] !=0 && (m.getX()+j+droite > width - 1)) 
					return true;
			if (m.getMatrice()[j][i] !=0 && !(m.getX()+j+droite > width - 1) &&  matrice[m.getX()+j+droite][m.getY()+i+ay] !=0 )
				return true;
			
			}
			}
		 
		}
		return false;
		
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
	
	public boolean bloque_left(BlocModel m, int ay, int left)
	{
		{
		for (int i=0;i<m.getMatrice().length;i++) {
			for (int j = 0; j<m.getWidth(); j++) {
			// La case de la pièce est-elle remplie ?
			  if (m.getMatrice()[j][i] !=0 && m.getX() + j + left < 0)
					return true;
			if (m.getMatrice()[j][i] !=0 &&
					(m.getX()+j+left) < (width - 1))
					if ( matrice[m.getX()+j+left][m.getY()+i+ay] !=0 )
				return true;
			}

		 }
		}
		return false;
		
	}
	
	public boolean bloque_bas(BlocModel m)
	{
		{
			
		for (int i=0;i<m.getMatrice().length;i++) {
			for (int j = 0; j<m.getWidth(); j++) {
			// La case de la pièce est-elle remplie ?
			if (m.getMatrice()[j][i] !=0 && m.getY() + i + 1 > this.heigth-1)
					return true;
			if (m.getMatrice()[j][i] !=0 && matrice[m.getX()+j][m.getY()+i+1] !=0 )
				return true;
			}

		 }
		}
		return false;
		
	}
	
	public int[] checkLinef(BlocModel l) {
		int[] r = {0,0,0};
		for(int i=0; i <heigth-1;i++) 
			if(this.isLigneFull(i)) {
				for (int y = 0; y < width; y ++ ) {
					if (i > l.getY() && y > l.getX() && y < l.getWidth() && i < l.getHeigth()) {
						if (l.getMatrice()[y][i]==1)
							r[1] += 1;
					}
				}
				
					
					eraseLine(i) ;
				
				r[0] += 1;
				r[2] = i;
				
			}
		return r;
	}
	
	public int[] checkLine(BlocModel l) {
		int[] r = {0,0,0};
		for(int i=0; i <heigth-1;i++) 
			if(this.isLigneFull(i)) {
				for (int y = 0; y < width; y ++ ) {
					if (i > l.getY() && y > l.getX() && y < l.getWidth() && i < l.getHeigth()) {
						if (l.getMatrice()[y][i]==1)
							r[1] += 1;
					}
				}
				
					
					
					eraseLine(i) ;
					
				
				r[0] += 1;
				r[2] = i;
				
			}
		return r;
	}
	
	public int[] checkLine(BlocModel l, GameState state) {
		int[] r = {0,0,0};
		for(int i=0; i <heigth-1;i++) 
			if(this.isLigneFull(i)) {
				for (int y = 0; y < width; y ++ ) {
					if (i > l.getY() && y > l.getX() && y < l.getWidth() && i < l.getHeigth()) {
						if (l.getMatrice()[y][i]==1)
							r[1] += 1;
					}
				}
				
					
					state.line(i);
					eraseLine(i) ;
					
				
				r[0] += 1;
				r[2] = i;
				
			}
		return r;
	}
	
	
	
	private int eraseLine(int i) {
		int mb[] = new int[width];
		int ma[] = new int[width];
		mb = this.getLigne(0);
		int nb = 0;
		if (i==0)
		for (int k = 0; k < width; k++)
			matrice[k][i] = 0 ;
		for (int k = 0; k < i; k++){
		mb = ma.clone();
		ma = this.getLigne(k+1);
		nb = nb + width;
		for (int j = 0; j < width; j++) {
			matrice[j][k+1]=mb[j];
			
			
		}
		}
		return nb;
	}
	
	public int addLine(int i) {
		int mb[] = new int[width];
		int ma[] = new int[width];
		ma = this.getLigne(1);
		int nb = 0;
		
		for (int l = 0; l < i; l++) {

		for (int k = 1; k < heigth; k++){
		mb = ma.clone();
		ma = this.getLigne(k+1);
		nb = nb + width;
		for (int j = 0; j < width; j++) {
			matrice[j][k-1]=mb[j];
		}
		}
		for (int j = 0; j < width; j++) {
			matrice[j][heigth-1] = 10;
		}
			
			
		
		}
		
		return nb;
	}


	private boolean isLigneFull(int i) {
		int full = 0;
		for (int j = 0; j < width; j++)
			if (matrice[j][i]!=0)
				full +=1;
		if (full==width)
		return true;
		return false;
	}


	public Matrice copy() {
		Matrice copy = new Matrice (heigth, width, x, y, matrice);
		
		return copy;
	}


	public boolean checkOutOfBoundBottom(BlocModel m, int x, int y) {
		BlocModel t = m.copy();
		t.addVectorPosition(x, y);
		if (m.getY() + m.getHeigth() >= heigth-1) 
			return true;
		return false;
	}
	
	
	
	public int checkOutOfBoundArround(BlocModel m, int x, int y) {
		BlocModel t = m.copy();
		t.addVectorPosition(x, y);
		if (m.getX() + m.getWidth() >= width-1)
			return 1;
		else if (m.getX() <= 1)
			return 2;
		else
			return 0;
	}
	
	public boolean rotate(BlocModel m) {
		BlocModel mt = m.copy();
		mt.rotate();
		if (bloque_left(mt,0,0) || bloque_droite(mt,0,0) || bloque_bas(mt))
		return false;
		else
		return true;
	}
	
	public int transitionHorizontale() {
		int r = 0;
		for (int i = 0; i < heigth - 1; i ++) {
			for (int j = 1; j < width; j++) {
				if (matrice[j-1][i] > 0 && matrice [j][i] < 1 || matrice[j-1][i] < 1 && matrice [j][i]     > 0 )
					r = r + 1;
			}
		}
			return r;
	}
	
	public int transitionVerticale() {
		int r = 0;
		for (int i = 0; i < width; i++)
			for (int j=1; j < heigth - 1; j++)
				if (matrice[i][j-1] > 0 && matrice [i][j] < 1 || matrice[i][j-1] < 1 && matrice [i][j] > 0)
					r = r + 1;
		return   r ;
	}
	
	public int trou() {
		int r = 0;
		for (int i = 0; i < width; i++)
			for (int j=1; j < heigth - 1; j++)
				if (matrice[i][j-1] > 0 && matrice [i][j] < 1)
					r = r + 1;
		return   r ;
	}
	
	public ArrayList <Integer> puit(int j) {
		ArrayList r = new ArrayList();
		int p = 0;
		int left = -1;
		int droit = 1;
		for (int i = 0; i < heigth - 1 ; i++) {
			if (j==0) {
				left = 1;
			}
			else if (j==width-1)
				droit = -1;
			
			
			if (matrice[j][i] == 0 && matrice[j+left][i]>0 && matrice[j+droit][i]>0) {
				p++;
			}
			else {
				if (p>1)
					r.add(p);
				p = 0;
			}
			if (i==heigth - 1) {
				if (p>1)
					r.add(p);
				p = 0;
			}
				
		}
		
		return r;
	}
	
	public ArrayList puits () {
		ArrayList r = new ArrayList();
		for (int i = 0; i < width; i++) {
			r.addAll(puit(i));
		}
	//	System.out.println(r);
		return r;
	}


	public void set(Matrice m) {
		// TODO Auto-generated method stub
 		matrice = new int[m.matrice.length][m.matrice[0].length];
 		heigth = m.matrice[0].length;
 		width = m.matrice.length;
		matrice = m.matrice.clone();
		
		x = m.getX();
		y = m.getY();

	}
	
	
	public int H() {
		for (int i = 0; i < heigth; i++)
			for (int j = 0; j < width; j++)
				if (matrice[j][i] > 0)
					return getY()+i;
		return 0;
	}

}
	
	

