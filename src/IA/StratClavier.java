package IA;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ressource.GameState;

public class StratClavier extends StratInterface implements KeyListener {

	private int[] r = new int[3];
	public int[] getAction(GameState state) {
		int[] rco = r.clone();
		r[0] = 0;
		r[1] = 0;
		return rco ;
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==38)
			r[1] = 1;
		if (arg0.getKeyCode()==37)
			r[0] = -1;
		if (arg0.getKeyCode()==39)
			r[0] = 1;	
		if (arg0.getKeyCode()==40)
			r[2] = 1;
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if (arg0.getKeyCode()==37)
			r[0] = 0;
		if (arg0.getKeyCode()==38)
			r[1] = 0;
		if (arg0.getKeyCode()==39)
			r[0] = 0;
		if (arg0.getKeyCode()==40)
			r[2] = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
