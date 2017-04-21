package Engine;

import java.applet.Applet;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;


import AudioGame.Music;

public class Event {

	private GameManag gc;
	private ArrayList<Music> listOfMusic;
	private boolean gameMusic = false;
	private boolean endM = false;
	
	
	
	private static int GAME_MUSIC = 0;
	private static int END_MUSIC = 1;
	private static int LOOSE = 2;
	
	public Event (GameManag game, JFrame a) {
		gc = game;
		listOfMusic = new ArrayList<Music> ();
		listOfMusic.add  (new Music ("ressource/music/themeMario.mp3", true, a.getClass().getResource("")  ) );
	//	listOfMusic.add a.getAudioClip ( a.getCodeBase()  " ressource/music/end.mp3", true, file));
	//	listOfMusic.add (new Music (" ressource/music/Loose.mp3", false, file));
	}
	
	public void startGame() {
		if (!gameMusic) {
		listOfMusic.get(GAME_MUSIC).start() ;
		gameMusic = true;
		}
	}
	
	public void EndGame() {
		if (gameMusic && !endM) {
	//	listOfMusic.get (GAME_MUSIC).stopM();
		}
	}
	
	
	
	public void playEnd() {
		if (!endM) {
	//		listOfMusic.get (END_MUSIC).start();
			endM = true;
		}
	}
	public void playLoose() {
	//	listOfMusic.get (LOOSE).start();
		System.out.println(" Play Sound : loose.mp3 ");
	}
	
	
}
