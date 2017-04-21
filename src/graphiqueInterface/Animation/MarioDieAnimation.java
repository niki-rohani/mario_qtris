package graphiqueInterface.Animation;

import graphiqueInterface.Tool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;

public class MarioDieAnimation extends Animation {

	
	
	
	public MarioDieAnimation (Rectangle bound, URL file) {
		super (bound);
		Ymultiple = -8;
		image = Tool.loadImage("ressource/Image/die.png", new Color (63, 72, 204) ) ;
	}
	
	public MarioDieAnimation (int x, int y, int a, int b, URL file) {
		super (x, y, a, b);
		Ymultiple = -8;
		image = Tool.loadImage("ressource/Image/die.png", new Color (63, 72, 204) ) ;
	}
	
	public void animation() {
		
	
			
				
				
			if (reperee > 30) 
				Ymultiple = 8;
			
			if (reperee > 200 ) {
						play = false;
						
			
			}
			
			
		
	}
	
	
	public void paintGraphics (Graphics g)  {
		System.out.println(getY());
		g.drawImage(image, (int) (getX()) , (int) getY(), null) ;
	}
	
}
