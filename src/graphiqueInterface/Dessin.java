package graphiqueInterface;

import graphiqueInterface.Animation.Animation;
import graphiqueInterface.Animation.MarioAnimation;
import graphiqueInterface.Animation.MarioDieAnimation;
import graphiqueInterface.Animation.MarioTuyauAnimation;
import graphiqueInterface.Animation.NuageAnimation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ressource.GameState;
import ressource.mdls.BlocModel;
import ressource.mdls.Matrice;

public class Dessin extends JPanel {
	
	// Etat du jeu
	private GameState state;
	
	// ??
	private int li;

	
	// Images
	private BufferedImage back;
	private BufferedImage img;
	private BufferedImage tuyau;
	private BufferedImage tuyauUp;
	private BufferedImage nuageIm;
	private BufferedImage p;
	private BufferedImage terre;
	private BufferedImage marioHappy;
	private BufferedImage hold;
	private BufferedImage marioIm;
	private BufferedImage aff;
			BufferedImage wall;
	
	private Animation marioDie;
	private Animation nuage;
	private Animation marioTuyau;
	private Animation marioAnim;
	//
	// Nombre d'images
	double nbImage = 5;
	
	// Taille des cases
	private int CASEY;
	private int CASEX;
	
	private	int DEBUT_X  = 30;
	
	private int width;
	private int h;
	
	// ??
	int ana = 4;
	
	private int tailleFenetreX = 400;
	private int tailleFenetreY = 570;
	
	private int repere = 0;
	
	private URL file;
	
	
	
	public Dessin(GameState s, URL fileracine) { 
		
		this.state = s;
		file = fileracine;
		
		loadSprite();
	
		width = state.getBoard().getWidth();
		h = state.getBoard().getHeigth();
		CASEX =  ( 260 - 2*DEBUT_X )  / width;
		CASEY = ( tailleFenetreY) / h;
		
		
		
		
		
		loadAnim();
	}
	
	private void loadAnim() {
		marioDie = new MarioDieAnimation(200, 400, tailleFenetreX, tailleFenetreY, file);
		marioDie.start(); 
		
		nuage = new NuageAnimation (30, 40, 180, 40, nuageIm);
		nuage.start();
		
		marioTuyau = new MarioTuyauAnimation (10, 40, 230, 180, marioHappy);
		marioTuyau.start();
		
		marioAnim = new MarioAnimation (tailleFenetreX - 10, 498 - 1, 260, tailleFenetreY, marioIm);
		marioAnim.start();
		
	}
	
	private void loadSprite() {
		System.out.println("Charging ....");
		System.out.println(0 + "%");
		
		back = Tool.loadImage("ressource/Image/back.jpg");
		System.out.println((1/nbImage) * 100 + "%");
		
		img = Tool.loadImage("ressource/Image/tetrispieces.png", Color.black);
		System.out.println((2/nbImage) * 100 + "%");
		
		
		tuyau = Tool.loadImage ("ressource/Image/tuyau.png") ;
		System.out.println((3/nbImage) * 100 + "%");
		
		
		tuyauUp = Tool.loadImage(("ressource/Image/tuyauUp.png") );
		System.out.println((4/nbImage) * 100 + "%");
		
		
		p = Tool.loadImage("ressource/Image/piece.png", Color.white);
		
		
		terre = Tool.loadImage("ressource/Image/ground.png", Color.white);
		
		
		
		System.out.println((7/nbImage) * 100 + "%");
		
		
		
		
		
		marioHappy = Tool.loadImage("ressource/Image/mario.png", Color.white) ;
		
		System.out.println((8/nbImage) * 100 + "%");
		
	
		
		
		hold = Tool.loadImage("ressource/Image/hold1.png", Color.white);
		
		nuageIm = Tool.loadImage("ressource/Image/fly.png", new Color (76, 130, 162));
		
		marioIm = Tool.loadImage("ressource/Image/mario anim.png", new Color (255, 174, 201) );
		
		aff = Tool.loadImage("ressource/Image/wan.jpg", new Color (39,15,11));
		
		
		wall = Tool.loadImage ("ressource/Image/wall.png");
	}
	
	
	
	
	
	/* Dessin de la grille */
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		super.paintComponents(g);
		
		
		 
		// Init de l'espace de dessin
		this.setSize(tailleFenetreX, tailleFenetreY);
		g.setColor(Color.black);
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height)  ;
		g.setColor(Color.white);
		width = state.getBoard().getWidth();
		h = state.getBoard().getHeigth();
		CASEX =  ( 260 - 2*DEBUT_X )  / width;
		CASEY = ( tailleFenetreY) / h;
	///////////////////
		for (double i = 258; i < this.getWidth(); i = i + wall.getWidth()*0.5) {
			for (double j = 0; j < this.state.getBoard().getHeigth() * CASEY - 8; j = j + wall.getHeight()*0.5)
			g.drawImage(wall,  (int) i, (int)j, (int) ( wall.getWidth() * 0.5 ) , (int) ( wall.getHeight() *  0.5 ), null);
		}
		
		// Dessin du decor arriere
		drawDecorBehind(g);
		
		// Dessin du tableau
		drawTab(g);
		drawPiece(g);
		
		
		// Dessin animations secondaires
		marioTuyau.paint(g);
		
		drawDecorFront(g);
		
		
		// dessin anim
		nuage.paint(g);
		Animation.piece(g, p, CASEY);
		  	
		
		// dessin des animation de debut et de fin
		if (state.isEnd()) {
			
			drawEnd(g);
		}	
		
		if (state.start()) {
			drawStart(g);
		}  	
		
		
		
		 g.drawImage(hold, state.getBoard().getWidth() * CASEX + 2 * DEBUT_X, 80, null)  ;
		 
		 g.setColor(Color.white);
		 g.drawString("HOLD", state.getBoard().getWidth() * CASEX + 2 * DEBUT_X + 4, 79);
		
		 g.setColor(Color.white);
	
		 drawS(g);
		 marioAnim.paint(g);
		
		
	}
	
	private void drawStart (Graphics g) {

		
		if (ana == 4)
		g.drawImage(img, 60, 200, 160, 399, 230, 830, 330, 1029, this);
		else if (ana == 3)
		g.drawImage(img, 60, 200, 160, 399, 340, 830, 440, 1029, this);
		else if (ana == 2)
			g.drawImage(img, 60, 200, 160, 399, 450, 830, 550, 1029, this);
		else if (ana == 1)
			g.drawImage(img, 60, 200, 160, 399, 560, 830, 660, 1029, this);
		
		
			g.setColor(Color.red);
			g.setFont(new Font("AR DESTINE", 1, 20));
			g.drawString(ana - 1 + " " , this.getWidth() / 2 , 300 );
		
	
	ana--;
	if (ana < 0) {
		ana = 4;
		state.start = false;
	}
	
	try {
		Thread.sleep(270);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	
	public void drawGround (Graphics g) {
		for (int i = 0; i < this.getWidth(); i = i + terre.getWidth()) {
			g.drawImage(terre, i, state.getBoard().getHeigth() * CASEY - 3, null);
		}
	}
	
	private void drawEnd(Graphics g) {
		marioDie.paint(g);
	}
	
	
	public void drawDecorFront(Graphics g) {
		drawPipe(g);
		drawGround (g);
	
	}
	
	private void drawPipe (Graphics g) {
		for (int i = 0; i < this.getHeight() - 5 * CASEY; i++) {
			g.drawImage(tuyau, 0, 4*CASEY + i*tuyau.getHeight(this), this);
			g.drawImage(tuyau, 230, 4*CASEY + i*tuyau.getHeight(this), this);
		}
		
		
		
		g.drawImage(tuyauUp, 0, 4*CASEY - 15 , this);
		g.drawImage(tuyauUp, 230, 4*CASEY - 15 , this);
		
		
	}
	
	private void drawDecorBehind(Graphics g) {
		g.drawImage(back, 0, 0, width * CASEX + 2*DEBUT_X, ( h - 1 ) * CASEY  , this);
		for(int i=4;i<h;i++)
			for(int j=0;j<width;j++) {
				
				g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.4f));
				g.fillRoundRect(DEBUT_X + j*CASEX, i*CASEY, CASEX,  CASEY, 4, 4 )  ;
				
				g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
				
				g.drawRoundRect(DEBUT_X + j*CASEX, i*CASEY, CASEX,  CASEY, 4, 4 )  ;
				}
	}
	
	private void drawTab(Graphics g) {
		
			int x1;
			int y1;
			int x2;
			int y2;
		
		
		// Dessin du tableau de jeu
		for(int i=4;i<h;i++)
			for(int j=0;j<width;j++) {
				int m = state.getBoard().getPoint(j, i);
				
				if (m > 0) {
				
					
					if (m == 1) { x1 = 246; x2 = 1065; y1 = 266; y2 = 1085;
					}
					else if (m == 2) { x1 = 278; x2 = 1065; y1 = 298; y2 = 1085;
					}
					else if (m == 3) { x1 = 317; x2 = 1065; y1 = 337; y2 = 1085;
					}
					else if (m == 4) { x1 = 349; x2 = 1065; y1 = 369; y2 = 1085;
					}
					else if (m == 5) { x1 = 386; x2 = 1065; y1 = 406; y2 = 1085;
					}
					else if (m == 6) { x1 = 420; x2 = 1065; y1 = 440; y2 = 1085;
					}
					else if (m == 7) { x1 = 455; x2 = 1065; y1 = 475; y2 = 1085;
					}
					else { x1 = 581; x2 = 1062; y1 = 597; y2 = 1085; }
					
					
					
				
				if (state.getPiece().getY()+i > 3)
				g.drawImage(img, DEBUT_X + CASEX*(j),   CASEY*(i), DEBUT_X + CASEX*(j) + CASEX,    CASEY*(i) + CASEY, x1, x2, y1, y2, this);
				}
			}
	}
	
	public void drawPiece(Graphics g) {
		

		
		
		
		int x1;
		int y1;
		int x2;
		int y2;
		
		h = state.getPiece().getHeigth();
		width = state.getPiece().getWidth();
		// Dessin de la piece
		for(int i=0;i<h;i++)
			for(int j=0;j<width;j++) {
				int m = state.getPiece().getMatrice()[j][i];
				if (m > 0) {
				
					
					if (m == 1) { x1 = 246; x2 = 1065; y1 = 266; y2 = 1085;
					}
					else if (m == 2) { x1 = 278; x2 = 1065; y1 = 298; y2 = 1085;
					}
					else if (m == 3) { x1 = 317; x2 = 1065; y1 = 337; y2 = 1085;
					}
					else if (m == 4) { x1 = 349; x2 = 1065; y1 = 369; y2 = 1085;
					}
					else if (m == 5) { x1 = 386; x2 = 1065; y1 = 406; y2 = 1085;
					}
					else if (m == 6) { x1 = 420; x2 = 1065; y1 = 440; y2 = 1085;
					}
					else if (m == 7) { x1 = 455; x2 = 1065; y1 = 475; y2 = 1085;
					}
					else { x1 = 246; x2 = 1065; y1 = 266; y2 = 1085; }
					
					
					
				
				if (state.getPiece().getY()+i > 3)
				g.drawImage(img,  DEBUT_X + CASEX*(state.getPiece().getX()+j),   CASEY*(state.getPiece().getY()+i), DEBUT_X + CASEX*(state.getPiece().getX()+j) + CASEX,    CASEY*(state.getPiece().getY()+i) + CASEY, x1, x2, y1, y2, this);
				}
				}
	}
	

	public void drawS (Graphics g) {
		 g.drawImage(aff, 258 + 23 - 5, 369 + 6, 140, 190 - 14, this);
		 g.setFont(new Font ("Courier new", 1, 11));
		 g.setColor(Color.black);
		 g.drawString(state.getPlayer().getScore() + " " , state.getBoard().getWidth() * CASEX + 2 * DEBUT_X + 62, 471);
	}
	 
	
}



