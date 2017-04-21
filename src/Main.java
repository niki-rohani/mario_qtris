import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import graphiqueInterface.GameInterface;
import graphiqueInterface.VueTetris;

import javax.rmi.CORBA.Util;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Engine.GameManag;
import Engine.IdManager;
import IA.Arbre;
import IA.Noeud;
import Tool.GameTool;

import ressource.Matrice.GameBoard;
import ressource.mdls.*;
import ressource.pieces.Objet1;
import ressource.pieces.Objet3;


public class Main {

	
	public static void main (String[] args) {
		JFrame f = new JFrame();
		JPanel pp = new JPanel();
		f.add(pp);
		pp.setLayout(new BorderLayout());
		pp.setSize(600,400);
		f.setSize(500,600);
	
	  try {
			new IdManager(f).run() ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
}

	