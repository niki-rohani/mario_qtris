package graphiqueInterface;

import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JPanel;

import ressource.GameState;

public abstract class GameInterface {
	
	URL file;

	GameState state;
	
	public abstract void initialised(GameState state, KeyListener k, int i, JPanel j);
	
	public void stop() {}
	
	public void refresh (int sleep) {}
	
	public void end() { }
	
}
