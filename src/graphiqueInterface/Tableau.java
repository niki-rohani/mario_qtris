package graphiqueInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JPanel;

public class Tableau extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage hold;
	
	public Tableau(URL file) {
		load(file);
	}
	
	public void load(URL file) {
		 hold = Tool.loadImage("ressource/Image/hold1.png",  Color.white );
	}
	
	
	
	public void paint (Graphics g) {
		 g.drawImage(hold, 0, 0, null);
		
	}
	
}
