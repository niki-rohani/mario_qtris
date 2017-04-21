package graphiqueInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JFrame;

import ressource.GameState;



import Engine.IdManager;

public class Menu extends JPanel implements KeyListener {
	
	private int i = 0;
	
	public int y = 300;
	
	 BufferedImage img;
	
	IdManager ma;
	
	JFrame f;
	

	
	public Menu(IdManager m, JFrame j) {
		ma = m;
		f = j;
		
	
			
			img = Tool.loadImage( "ressource/Image/tetrispieces.png" )  ;
		
	}
	
	public void refresh (int i) {
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponents(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
        g.drawImage(img, 10, 10, 270, 200, 0, 840, 272, 1080, this);
        
        if (i % 2 == 0)
        g.drawImage(img, 10, y, 60, y + 50, 160, 650, 310, 810, this);
        else
        g.drawImage(img, 10, y, 60,  y + 50 , 0 , 650, 170, 810, this);
        
        
        i = i + 1;
        
        if (y == 400)
        g.setColor(Color.white);
        else
        g.setColor(Color.red);
      //  g.setFont(new Font ("Ms sans comics", 10, 2));
        g.drawString("Nouveau Jeu", 80, 350);
        
        
        g.setColor(Color.white);
        
        if (y == 300)
            g.setColor(Color.white);
            else
            g.setColor(Color.red);
        
        g.drawString("Quitter", 80, 450);
        
        
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			 if (y == 400)
				 System.exit(0);
			 else
				 ma.goToGame();
				 
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			 y = 400;
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			 y = 300;
		}
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
