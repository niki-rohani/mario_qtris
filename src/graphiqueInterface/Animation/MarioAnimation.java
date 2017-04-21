package graphiqueInterface.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MarioAnimation extends Animation {
	
	int x, y, xx, yy;
	double anim;
	double animM;
	int state;
	int yyy;
	int yTemp;
	
	int animstate;
	
	boolean pass;

	public MarioAnimation (int x, int y, int w, int h, BufferedImage im) {
		super (x, y, w, h);
		image = im;
		Xmultiple = -1;
		refresh = 39;
		anim = 0;
		animM = 1;
		state = 1;
		yyy = y;
		yTemp = 0;
		animstate = 0;
		
		pass = false;
		
	}
	
	public void animation () {
		
		if (animstate == 0) {
			animstate = 2 ;
		}
		
		if (animstate == 1) {
		if (state == 1)
		marioLeft();
		if (state == 2)
		marioClean();
		if (state == 3)
		marioRight();
		}
		
		if (animstate == 2) {
			if (state == 1)
				marioLeftR();
			if (state == 2)
				marioFingerR();
			if (state == 3)
				marioRegardR();
			if (state == 4)
				marioRR();
		}
	}
	
	public void paintGraphics (Graphics g) {
		g.drawImage(image, getX(), getY() + yTemp, getX() + xx - x , getY() + yTemp + yy - y, x, y, xx, yy, null );
		
	}
	
	
	private void marioLeft() {
		

		refresh = 39;

		Xmultiple = -1;
		
		y = 312;
		yy = 368;
		
		yTemp = 0;
		
		if (anim < 1) animM = 1;
		if (anim > 3) animM = -1;
		
		
		
		if (anim == 1) {
			x = 143;
			xx = 185;
		}
		if (anim == 3) {
			x = 39;
			xx = 94;
		}
		else if (anim == 2) {
			x = 94;
			xx = 140;
		}
		
		anim = anim + animM ;
		
		if (getX() < bounds.width + 28)
			changeState (2);
	}
	
	private void marioLeftR() {
		
		
		
		refresh = 39;
		
		Xmultiple = -1;

		
		y = 433;
		yy = 469;
		
		yTemp = 17 + 2;
		
		if (anim < 1) animM = 1;
		if (anim > 3) animM = -1;
		
		
		
		if (anim == 1) {
			x = 166;
			xx = 188;
		}
		if (anim == 3) {
			x = 46;
			xx = 65;
		}
		else if (anim == 2) {
			x = 6;
			xx = 23;
		}
		
		anim = anim + animM ;
		
		if (getX() < bounds.width + 68 && getX() > bounds.width && !pass ) {
			changeState (2);
			pass = true;
		}
		
		if (getX() < bounds.width )
			changeState(3);
		
	}
	
	


private void marioRR() {
		
		
		
		refresh = 39;
		
		Xmultiple = 4;

		
		y = 433;
		yy = 469;
		
		yTemp = 17 + 2;
		
		if (anim < 1) animM = 1;
		if (anim > 3) animM = -1;
		
		
		
		if (anim == 1) {
			x = 166;
			xx = 188;
		}
		if (anim == 3) {
			x = 46;
			xx = 65;
		}
		else if (anim == 2) {
			x = 6;
			xx = 23;
		}
		
		anim = anim + animM ;
		
		
		
		if (getX() > bounds.width + 400 ) {
			changeState(1);
			animstate = 1;
			
		}
		
	}
	
	
	
private void marioRight () {
		
		refresh = 39;
		
		Xmultiple = 1;
		
		 
		
		
		if (anim < 1) animM = 1;
		if (anim > 3) animM = -1;
		
		
		y = 312;
		yy = 368;
		yTemp = 0;
		
		if (anim == 1) {
			x = 202;
			xx = 251;
		}
		
		if (anim == 3) {
			x = 306;
			xx = 349;
		}
		
		else if (anim == 2) {
			x = 254;
			xx = 302;
		
		}
		
		
		
		anim = anim + animM ;
		
		
	}
	private void marioClean () {
		
		refresh = 80;
		
		Xmultiple = 0.5;
		
		 
		
		
		if (anim < 1) animM = 1;
		if (anim > 2) animM = -1;
		
		
		
		if (anim == 1) {
			y = 375;
			x = 126;
			xx = 150;
			yy = 423;
			yTemp = 3;
		}
		
		else if (anim == 2) {
			setY  (yyy );
			x = 165;
			xx = 188;
			y = 373;
			yy = 430;
			yTemp = -17;
			
			
		}
		
		anim = anim + animM ;
		
		if (getX() > bounds.width + 92)
			changeState (3);
	}
	
	
	public void marioFingerR() {
		refresh = 800;
		
		Xmultiple = 0;
		
		yTemp = 17 + 2;
		
		if (anim == 1) {
			y = 435;
			yy = 465;
			x = 124;
			xx = 145;
		}
		
		else {
			y = 512;
			
			yy = 545;
			
			x = 4;
			
			xx = 25;
			
			yTemp = 17;
		}
		
		anim = anim + animM;
		
		if (anim == 3)
			changeState(1);
	}
	
	

	public void marioRegardR() {
		refresh = 800;
		
		Xmultiple = 0;
		
		yTemp = 17 + 2;
		
		if (anim == 1) {
			y = 434;
			yy = 466;
			x = 204;
			xx = 224;
		}
		
		else if (anim == 2) {
			y = 474;
			
			yy = 505;
			
			x = 322;
			
			xx = 342;
			
			
			
			refresh = 1200;
		}
		
		if (anim == 3) {
			y = 474;
			yy = 505;
			
			x = 369;
			xx = 389;
			
			refresh = 300;
		}
		
		anim = anim + 1;
		
		if (anim == 6)
			changeState(4);
	}
	
	
	
	
	
	protected void changeState (int v) {
		Xmultiple = 0;
		anim = 1;
		animM = 1;
		state = v;
	}
	
}
