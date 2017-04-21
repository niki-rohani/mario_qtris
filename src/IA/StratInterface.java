package IA;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ressource.GameState;

public abstract class StratInterface implements KeyListener {

	
	public abstract int[] getAction(GameState state);
	
	
}
