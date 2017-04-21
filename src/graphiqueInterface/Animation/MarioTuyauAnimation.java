package graphiqueInterface.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MarioTuyauAnimation extends Animation {
	
	
	private boolean marioMonte;
	private int marioD;
	
	 public MarioTuyauAnimation(int x, int y, int a, int b, BufferedImage im) {
		super (x, y, a, b);
		image = im;
		marioMonte = false;
		refresh = 70 ; 
	}
	 
	 public void animation() {
		 
		 
		 
		 if (marioMonte) {	Ymultiple = -1; bounds.height = bounds.height + 1; }
			else       {  Ymultiple = 1; bounds.height = bounds.height - 1 ; }
			
			
			if (getY() < 40) marioMonte = false;
			else if (getY() > 180) marioMonte = true;
			
			
			
			
			
		 
			
	 }
	 
	 public void paintGraphics(Graphics g) {
		 	
			g.drawImage(image, getX(),  getY(), null);
			g.drawImage(image, (int) bounds.getWidth(), (int) bounds.getHeight() , null);
	 }

}
