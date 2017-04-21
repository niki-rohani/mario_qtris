package Engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public abstract class IdInterface extends Thread implements KeyListener {

	
	protected boolean pause;
	protected int id;
	protected JPanel pan;
	protected boolean isCreate;
	public IdManager m;
	
	public IdInterface (int i, JPanel p, IdManager mm) {
		id = i;
		pan = p;
		isCreate = true;
		
		m = mm;
	}
	
	public IdInterface (int i, JPanel p) {
		id = i;
		pan = p;
		isCreate = true;
		
		m = null ;
	}
	
	
	
	public void removeKey (JFrame f) {
		f.removeKeyListener(this);
	}
	
	public int getClassId() {
		return id;
	}
	
	public boolean isPlay() {
		return isCreate;
	}
	
	
	
	
	public void run() {
		isCreate = false;
		play();
	}
	
	
	public void restart() {
		
	}
	
	public void pause() {
		pause = true;
	}
	
	
	
	public void play() {
		pan.add(new JPanel() { public void paint(Graphics g) { g.drawLine(0, 0, 200, 50); } } );
		pan.setVisible(true);
	}
	
	public void draw() {
		
	}

	@Override
	public abstract void keyPressed(KeyEvent e);

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addKey(JFrame f) {
		f.addKeyListener(this);
	}
	
}
