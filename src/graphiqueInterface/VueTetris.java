package graphiqueInterface;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ressource.GameState;
import ressource.mdls.Matrice;





/* Cette classe permet d'afficher une grille Tetris (question 4 de la
 * 2e partie du projet). Elle est fournie aux étudiants. Elle s'appuie
 * sur les classes SWING. L'utilisation est très simple : il suffit de
 * créer une instance de VueTetris et d'appeler la méthode
 * update(Grille) sur cette instance pour mettre à jour
 * l'affichage. */

public class VueTetris extends GameInterface implements KeyListener {

	
	

	private GameState state;


	JPanel f;
	
	
	
    /* La constante CASE définit la taille (en pixels) d'une case */
	public static final int CASE = 10;

    /* Les attributs (privés) de la classe : la grille à afficher et
     * la zone de dessin */
	private JPanel dessin;
	private JPanel score;
	
    /* La méthode update pour afficher une nouvelle grille sur demande */
	public void refresh(int sleep) {
		
		
		dessin.repaint();
	//	score.repaint();

	}
	
	
	
	public VueTetris(URL file) {
		this.file = file;
	}
	
	public void initialised(GameState state, KeyListener k, int i, JPanel j) {f = j;
	
	
	dessin = new Dessin(state, file);
	score = new Tableau(file);
	
	
	JPanel p = new JPanel();
	p.setLayout(new GridLayout());
	 p.add(dessin);
	 p.add(score);
	
	
	f.add(p);
	
	
}
	
	public class Score extends JPanel {
		
		public void paint(Graphics g) {
			this.setBackground(Color.black);
			super.paint(g);
			super.paintComponents(g);
			g.setColor(Color.white);
			g.setFont(new Font("AR DESTINE", 1, 20));
			g.drawString("Score : " + state.getPlayer().getScore(), 2, 40);
		}
	}
	
	
	

	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
			
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
}
