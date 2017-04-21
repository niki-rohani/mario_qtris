package graphiqueInterface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Tool {

	
	
	 private Image TransformGrayToTransparency(Image image)
	  {
	    ImageFilter filter = new RGBImageFilter()
	    {
	      public final int filterRGB(int x, int y, int rgb)
	      {
	        return (rgb << 8) & 0x00000000;
	      }
	    };

	    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	  }
	 
	 

	  private static BufferedImage ImageToBufferedImage(Image image, int width, int height)
	  {
	    BufferedImage dest = new BufferedImage(
	        width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = dest.createGraphics();
	    g2.drawImage(image, 0, 0, null);
	    g2.dispose();
	    return dest;
	  }
	  
	  public static BufferedImage  makeColorTransparent(BufferedImage im, final Color color) {
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
		    return ImageToBufferedImage(Toolkit.getDefaultToolkit().createImage(ip), im.getWidth(), im.getHeight()) ;
		    
	  }
	  
	  public static BufferedImage loadImage(String path) {
		  
		  System.out.println(new File (path).getAbsolutePath() );
		  
		  
		  
		  BufferedImage back = null;
		  try {
			   
				back = ImageIO.read(new File (path));
				System.out.println( path + " charged");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  return back;
			
			
	  }
	  
 public static BufferedImage loadImage(String path, Color color) {
		  
		  
		  
		  BufferedImage back = null;
		  try {
			  
			  back = ImageIO.read(new File (path));
				System.out.println( path + " charged");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		  
		  back = Tool.makeColorTransparent(back, color) ;
		  
		  
		  return back;
			
			
	  }
}
