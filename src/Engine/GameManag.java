package Engine;
import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;




import ressource.GameState;
import ressource.mdls.BlocModel;
import ressource.mdls.Player;
import graphiqueInterface.GameInterface;
import graphiqueInterface.VueTetris;
import AudioGame.Music;
import IA.StratClavier;
import IA.StratIA;
import IA.StratInterface;
import Tool.GameTool;


public class GameManag extends IdInterface {


	// Etat de jeu
	private GameState state;

	// Interface graphiaque
	private GameInterface gameGraficInterface;
	boolean isConsole;

	// Stratégie
	private StratInterface strat;

	// Vitesse de Jeu
	private int speed;

	// Action envoyé par strat
	private int[] action = null ;
	
	private Music mPlay;
	
	private Event event;
	
	private int justEnd = 1 ;
	
	JApplet file;
	
	int wait;

	public GameManag(int id, JPanel j, IdManager m, JFrame f) {
		super(id, j, m);
		GameTool.INITIALISING_GAME(isConsole);
		state = new GameState();
	
		gameGraficInterface = new VueTetris(f.getClass().getResource(""));
		strat = new StratIA();
	//	strat = new StratClavier();
		initialize();
		speed = 40;
		pause = true;
		Player p = new Player();
		loadPlayer(p);
		j.addKeyListener(this);
		
		event = new Event(this, f);
		
		wait = 10;

	}
	
	
	
	public GameManag(int id, JPanel j, JFrame a) {
		super(id, j);
		GameTool.INITIALISING_GAME(isConsole);
		state = new GameState();
		gameGraficInterface = new VueTetris(a.getClass().getResource(""));
		 strat = new StratClavier();
		// strat = new StratClavier();
		initialize();
		speed = 40;
		pause = true;
		Player p = new Player();
		loadPlayer(p);
		j.addKeyListener(this);
		
		event = new Event(this, a);
	}


	public void run() {
		isCreate = false;
		play();
	}


	public void pause() {
		pause = true;
	}


	private void initialize () {
		
		state.initialise();
		gameGraficInterface.initialised(state,strat, 1, pan);

	}

	public void loadPlayer(Player p){
		state.setNewPlayer(p);
	}

	public void play() {
		pause = false;
		try {
			Begin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void Begin () throws InterruptedException {
		System.out.println("Starting");
		state.start = true;
		// Boucle de jeu
		Thread t = new Thread (new Runnable() { public void run () { while (true) {  while (!pause) draw();  } } } );
		t.start();
		while (true) {
			
			// Si on a pas pause
			while (!pause) {
				
				
				
					 
					
				// Si le jeu n'est pas loose
				
				
				if (!state.isEnd()) {
					if (!state.start()) {
						event.startGame();
					//recupere la strat
					Strategie strategie = new Strategie();
					strategie.start();

					// On attends 4 ms pour recup la strat
					
					Thread.sleep(speed);

					
					if (action == null) Thread.sleep(100);
					
					// Si l'action est recup on test si on veux aller plus vite
					if (action != null  && action[2] > 0) { state.setSpeed(1); speed = 8;  }
					else { state.setSpeed(wait); }


					// On definie la vitesse de jeu
					Thread.sleep(state.getSpeed());


					if (action == null) { action = new int[3]; action[0] = 0; action[1] = 0; action[2] = 0; System.out.println("TROP TAR"); }


					// On donne les actions a effectuer		
					state.compute(action);

					
					action = null;
					
					
					
				}
				}
				else {
					if (justEnd==1000) {
						event.playEnd();
						Thread.sleep(8);
					}
					if (justEnd==1) {
					event.EndGame();
					Thread.sleep(8);
					event.playLoose();
					}
					Thread.sleep(1);
					justEnd = justEnd + 1;
					
				
					
				}
					



			}
			
			
			
			
		}
		}
	

	public void draw () {
		try {
			Thread.sleep(8);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		gameGraficInterface.refresh(1);
	}

	public void addKey (JFrame f) {
		f.addKeyListener(this);
		f.addKeyListener(strat);
	}
	
	public void addKey (JApplet f) {
		f.addKeyListener(this);
		f.addKeyListener(strat);
		file = f;
	}

	public void restart() {

		state.initialise();
		gameGraficInterface.initialised(state,strat, 1, pan);
		state.restart();
	

	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			pause = !pause; 
		
		if (arg0.getKeyCode() == KeyEvent.VK_1) {
			strat = new StratIA();
			wait = 180;
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_2) {
			strat = new StratClavier();
			file.addKeyListener(strat);
			wait = 8;
		}
		
	}
	
	public boolean isPlay() {
		return isCreate;
	}



	public class Strategie extends Thread {
		public void run() {
			action = strat.getAction(state);
		}
	}
	
	public Event event() {
		return event;
	}

}
