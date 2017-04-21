package IA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ressource.GameState;

public class Arbre {

	private Noeud tete;
	
	public Arbre(Noeud racine) {
		tete = racine;
	}

	
	public int hauteur() {
		return tete.hauteur();
	}
	
	public int largeur() {
		return tete.nbVide();
	}
	
	
	public ArrayList <Double> parcoursProfondeur() {
		Noeud s = tete;
		ArrayList<Double> r = new ArrayList<Double> ();
		if (s.largeur() == 0) {
			r.add(s.score());
			return r;
		}
	    Set sommetsVisit�s = new HashSet();
	    for (int i = 0; i < s.largeur(); i++) {
	    	Noeud next = s.feuille(i);
	      if (!sommetsVisit�s.contains(s)) {
	    	  r.add(s.score());
	        parcoursProfondeur(next, sommetsVisit�s, s.score(), r);
	      }
	    }
	    return r;
	  }
	  static void parcoursProfondeur(Noeud s, 
	                                 Set sommetsVisit�s, double score, ArrayList r) {
		  
		  if (s.largeur() == 0) {
			  	r.add(score+s.score());
				return;
			}
		  sommetsVisit�s.add(s);
		  for (int i = 0; i < s.largeur(); i++) {
		    	Noeud sprime = s.feuille(i);
		      if (!sommetsVisit�s.contains(sprime)) {
		        parcoursProfondeur(sprime, sommetsVisit�s, score + s.score(), r);
		      }
		    }
		  
	  }
}

