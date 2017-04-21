package graphiqueInterface.Animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import AudioGame.Music;


public abstract class Animation extends Thread {
	
	protected Rectangle bounds;
	protected int reperee;
	protected BufferedImage image;
	
	protected double Xmultiple;
	protected double Ymultiple;
	
	protected boolean play;
	
	protected double repere2;
	
	protected long refresh = 8 ;
	
	private double x;
	private double y;
	
	public Animation (java.awt.Rectangle bound) {
		bounds = bound;
		reperee = 0;
		Xmultiple = 0;
		Ymultiple = 0;
		play = true;
		repere2 = 0;
		
	}
	
	public Animation (java.awt.Rectangle bound, BufferedImage im) {
		bounds = bound;
		reperee = 0;
		Xmultiple = 0;
		Ymultiple = 0;
		play = true;
		repere2 = 0;
		image = im;
		
	}
	
	public Animation (int x, int y, int a, int b) {
		bounds = new Rectangle (x,y,a, b);
		reperee = 0;
		Xmultiple = 0;
		Ymultiple = 0;
		play = true;
		repere2 = 0;
		
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		return (int) bounds.getX() ;
	}
	
	public int getY() {
		return (int) bounds.getY();
	}
	
	public void setY(double v) {
		y = v;
	}
	
	public boolean step() {
		return (reperee != repere2);
	}
	
	public void multipleX() {
		x = x + Xmultiple;
		bounds.x = (int) x;
	}
	
	public void multipleY() {
		y = y  + Ymultiple;
		bounds.y = (int) y  ;
		
	}
	
	
	public void newStep() {
		try {
			Thread.sleep(refresh);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (step()) {
			repere2 = reperee;
		multipleX();
		multipleY();
		}
		
	}
	
	public void paint(Graphics g) {
		reperee++;
		paintGraphics (g);
	}
	
	public abstract void paintGraphics(Graphics g);
	
	
	public void run () {
		while (play) {
			animation();
			newStep();
		}
	}
	
	public abstract void animation();
	

	
	
	public static void line (int i) {
		double [] line = {i,200, i};
		
		Music m = new Music("ressource/music/coin.mp3", false, null);
		m.start();
	}
	
	
	
	public static void piece (Graphics g, BufferedImage p, int CASEY) {
		
//		for (int i = 0; i < piece.size(); i++) {
//		
//		g.drawImage(p,  (int) piece.get(i)[1],(int) ( piece.get(i)[0] * CASEY),(int) (piece.get(i)[1]  + 20),(int) ( piece.get(i)[0]  * CASEY  + 20 ) , 0, 0, 20, 20, null ) ;
//		if (repere%1 == 0 ) {
//		if (piece.get(i) [0] - piece.get(i) [2] > -4 && piece.get(i)[1] < 210)
//		piece.get(i)[0] = piece.get(i)[0] - 0.2;
//		else
//		piece.get(i)[0] = piece.get(i)[0] + 0.2;	
//		
//		piece.get(i)[1] = piece.get(i)[1] + 1;
//		
//		}
//		if (piece.get(i)[0] > 900) 
//			piece.remove(i);
//			
//		
//		
		
		
		
		
	}
	


	
	
	private BufferedImage ImageToBufferedImage(Image image, int width, int height)
	  {
	    BufferedImage dest = new BufferedImage(
	        width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = dest.createGraphics();
	    g2.drawImage(image, 0, 0, null);
	    g2.dispose();
	    return dest;
	  }
	  
	  public static Image makeColorTransparent(Image im, final Color color) {
	        ImageFilter filter = new RGBImageFilter() {

	                // the color we are looking for... Alpha bits are set to opaque
	                public int markerRGB = color.getRGB() | 0xFF000000;

	                public final int filterRGB(int x, int y, int rgb) {
	                        if ((rgb | 0xFF000000) == markerRGB) {
	                                // Mark the alpha bits as zero - transparent
	                                return 0x00FFFFFF & rgb;
	                        } else {
	                                // nothing to do
	                                return rgb;
	                        }
	                }
	        };
	        

		    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		    return Toolkit.getDefaultToolkit().createImage(ip);
		    
	  }
}
