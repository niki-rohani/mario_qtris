package Tool;

/*
 *  By Danti(dot)
 *  Classe D'outil pour le jeu meme
 */

public class GameTool {

 public static void tabString (int[][] t, int x, int y) {
	 String string = "";
	 for (int i=0; i<x; i++) {
		 string += "[";
		 for (int j=0; j<y; j++) {
			 string = string + t[i][j];
		 } 
		 string += "]";
	 }
	 System.out.println ("TABLEAU de Hauteur :"+ x + " de Largeur : " + y + " \n " + string);
 }

 
 
 ////////// Console Debug /////////////////////////////
public static void INITIALISING_GAME(boolean isConsole) {
	if (isConsole)
		System.out.println("Launching game ....");
}
	
	
}
