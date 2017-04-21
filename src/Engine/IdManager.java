package Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class IdManager implements KeyListener {

	// Les Interfaces de jeux
	private ArrayList<IdInterface> ids;
	
	// les panels pour graphiste
	private ArrayList<JPanel> pan;
	private JFrame f;
	
	// States des interfaces
	private int DEBUT = 0;
	private int MENU = 0;
	private int GAME = 1;
	private int MENU_S = 3;
	private int FIN = 2;
	/////////////
	
	// State de l'interface en cour
	private int current = 1 ;
	
	
	public boolean pass = false;
	
	// si on change de state
	public boolean change = true;
	
	public int currentChange = current;
	
	private FlowLayout panLayout;
	
	// Dimension de la fenetre
	private Dimension taille = new Dimension (600,600);
	
	public IdManager(JFrame file) {
		ids = new ArrayList<IdInterface> ();
		
		// Création de la liste des panels et interface
		pan = new ArrayList<JPanel> ();
		for(int i = 0; i < FIN; i++) {
			pan.add(null);
			ids.add(null);
		}
		for (int i = DEBUT ; i <  FIN ; i++) 
		pan.set(i, new JPanel());
		for(int i = DEBUT; i < FIN; i++) {
		//	pan.get(i).addKeyListener(this);
		}
		panLayout = new FlowLayout();
		for (int i = 0; i < pan.size(); i++) {
			pan.get(i).setPreferredSize(new Dimension(800,600));
			pan.get(i).setLayout(new BorderLayout());
		}
		ids.set(GAME, new GameManag(1, pan.get(GAME), this, file));
		ids.set(MENU, new Menu(MENU, pan.get(MENU), this, file));
		
		
	
		/////////////////////////////////
		
		
		f = file;
		
		f.addKeyListener(this);
		
		
	}
	
	// Démarage du thread
	public void run() throws InterruptedException {
			while (true) {
				f.getContentPane().repaint();
				// On check si rien ne doit etre changé
				checkID();
				Thread.sleep(7);
				f.repaint();
				
				
			}
			
			
		
	}
	
	
	
	public void startPan(int id) {
		if (current != id)
			stopPan(current);
		if ((ids.get(id).isPlay()))
			ids.get(id).start();
		else {
			ids.get(id).pause = false;
			ids.get(id).play();
		}
		
		current = id;
		System.out.println ("ID :" + id + " add Key Okay ");
		ids.get(id).addKey(f);
	}
	
	
	public void stopPan(int id) {
		//f.getContentPane().setVisible(false);
		//f.setContentPane(null);
		ids.get(id).pause();
		System.out.println ("STOP ID :" + id + " isAlive :" + ids.get(id).isAlive() + " isInterup " + ids.get(id).isInterrupted());
		ids.get(id).removeKey(f);
	}
	
	
	
	public void checkID() {
		if (change) {
			f.setContentPane(pan.get(currentChange));
			f.remove(pan.get(current));
			f.getContentPane().setVisible(true);
			startPan (currentChange);
			ids.get(currentChange).addKey(f);
			
			
			change = false;
			
			System.out.println("Changing Okay .... State " + current);
			
			f.setVisible(false);
			f.setVisible(true);
		}
		
	}

	public void changeToMenu() {
		change = true;
			currentChange = MENU;
		System.out.println("Changing .... Status Temporary " + currentChange);
	}
	
	public void goToMenu() {
		change = true;
		currentChange = MENU;
	}
	
	public void goToGame() {
		change = true;
		currentChange = GAME;
	}
	
	public void endGame() {
		change = true;
		currentChange = MENU_S;
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
		// TODO Auto-generated method stub
		
	}
	
	
}
