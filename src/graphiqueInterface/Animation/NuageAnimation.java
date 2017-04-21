package graphiqueInterface.Animation;

import graphiqueInterface.Tool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NuageAnimation extends Animation {

	int b1;
	int x0;
	int y0;
	int yPers;
	int x;
	int y;
	int bm = 1;
	
	public NuageAnimation (int x, int y, int a, int b, BufferedImage im) {
		super (x,y, a, b);
		image = im;
		Xmultiple = 1;
		b1 = 2;
		refresh = 100;
		
		}
	
	
	
	@Override
	public void animation() {
		
		
		if (getX() > bounds.getWidth() - 30)
			Xmultiple = -1;
			if (getX() < 30)
				Xmultiple = 1;
			
		
			
			
				
					
				
				if (b1 == 1) { x = 27; y=85; x0 = x + 55; y0 = y + 23; yPers = getY() - 20; }
				if (b1 == 2) { x = 90; y=86; x0 = x + 60; y0 = y + 23; yPers =	getY() - 20; }
				if (b1 == 3) { x = 90; y=115; x0 = x + 59; y0 = y + 23; yPers = getY() - 14; }
				if (b1 == 4) { x = 27; y=107; x0 = x + 58; y0 = y + 37; yPers = getY() - 2 ;  }
				
					
			
				
			
					if (b1 >3 || b1 < 2) 
						bm = -bm;
				b1 = b1 + bm;
				
			
				
			
		 
				
				
		
			
		
	}
	
	public void paintGraphics (Graphics g) {
		
		g.drawImage(image, (int) (getX()) , (int) getY(), getX() + x0 - x, getY() + y0 - y, x, y, x0, y0, null) ;
		g.drawImage(image, getX() + 20, yPers, getX() + 40, yPers + 22, 12, 32, 40, 62, null );

	}

}
