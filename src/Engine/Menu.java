package Engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Menu extends IdInterface {

	
	
	private graphiqueInterface.Menu p;
	
	
	
	
	public Menu (int i, JPanel j, IdManager m, JFrame f) {
		super (i, j, m);
		p = new graphiqueInterface.Menu(m, f);
		pan.add(p);
		
		
		
	}
	
	public void removeKey (JFrame f) {
		f.removeKeyListener(this);
		f.removeKeyListener(p);
	}
	
	public void run() {
		isCreate=false;
		while (!pause) {
			
			render();
		}
	}
	
	public void pause() {
		pause = true;
		
	}
	
	/**
	 * Initialise resources
	 */
	public void init() {
		
		
	}

	/**
	 * draw a quad with the image on it
	 */
	public void render() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}
	
	public void addKey (JApplet j) {
		j.addKeyListener(this);
		j.addKeyListener(p);
		
		System.out.println ("Menu Keylistener Added ");
	}
	
}
