package IA;

import java.util.ArrayList;

import ressource.GameState;

public class Noeud {
	
	
	private ArrayList<Noeud> fils;
	private Noeud pere;
	private GameState valeur;
	
	private double score;
	
	
	
	public Noeud(GameState valeur){
		this.valeur = valeur;
		fils = new ArrayList <Noeud>()  ;
		pere = null;
		
	}
	
	public Noeud(Noeud parent, double sc) {
		fils = new ArrayList <Noeud>();
		pere = parent;
		score = sc;
	}

	
	public Noeud feuille (int i) {
		return fils.get(i);
	}
	
	public void addFils(Noeud fils) {
		this.fils.add(fils);
	}
	
	public void addNoeud(double sc) {
		this.fils.add(new Noeud(this, sc + this.score));
	}
	
	public void addPere(Noeud parent) {
		pere = parent;
	}
	

	
	public double score() {
		return score;
	}
	
	public boolean isFeuille() {
		if (fils.size()==0)
			return true;
		return false;
	}

	public Noeud getFils (int i) {
		return fils.get(i) ;
	}
	
	public int largeur() {
		return fils.size();
	}
	
	public int hauteur() {
		int h = 0;
		if (isFeuille())
			return 1;
		else {
			for (int i = 0; i < fils.size(); i++)
				h = Math.max(h, fils.get(i).hauteur() );
			return h + 1;}
	}
		
	
	
	public int nbVide() {
		int vide = 0;
		if (this.isFeuille())
			return 1;
		else 
			for (int i = 0; i < fils.size(); i++)
				vide = vide + fils.get(i).nbVide();
		return vide;
	}
	
	public int nbVide(ArrayList <Double> score) {
		int vide = 0;
		if (this.isFeuille()) {
			score.add(this.score );
			return 1;
		}
		else 
			for (int i = 0; i < fils.size(); i++)
				vide = vide + fils.get(i).nbVide(score);
		return vide;
	}
	
	public int nbVide(ArrayList <Double> score, ArrayList<Integer> chemin, ArrayList<ArrayList<Integer>> meilleur) {
		ArrayList <Integer> ch = new ArrayList <Integer> ();
		ch.addAll(chemin);
		int vide = 0;
		if (this.isFeuille()) {
			score.add(this.score );
			meilleur.add(ch);
			return 1;
		}
		else 
			for (int i = 0; i < fils.size(); i++) {
				ch.clear();
				ch.addAll(chemin);
				ch.add(i);
				fils.get(i) . nbVide(score, ch, meilleur);
			}
		return vide;
	}
	
	
	
}