package AudioGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;


public class Music extends Thread {

	
	private boolean play = false ;
	

	
	private boolean boucle;
	
	


	    private String fFilename = null;
	    private boolean remote = false;
	    
	    URL file;
	    
	    
	 private Player player;

	    static public Music createInstance(String[] args)
	    {
	        Music player = new Music();
	        if (!player.parseArgs(args))
	                player = null;
	        return player;
	    }

	    Music() {}

	    public Music(String filename, boolean boucle, URL fileracine)
	    {
	        init(filename);
	        this.boucle = boucle;
	        try {
				file = new URL (new File (filename).getAbsolutePath());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        player = null;
	    }

	    protected void init(String filename)
	    {
	        fFilename = filename;
	    }

	    protected boolean parseArgs(String[] args)
	    {
	        boolean parsed = false;
	        if (args.length == 1)
	        {
	                init(args[0]);
	                parsed = true;
	                remote = false;
	        }
	        else if (args.length == 2)
	        {
	                if (!(args[0].equals("-url")))
	                {
	                        showUsage();
	                }
	                else
	                {
	                        init(args[1]);
	                        parsed = true;
	                        remote = true;
	                }
	        }
	        else
	        {
	                showUsage();
	        }
	        return parsed;
	    }

	    public void showUsage()
	    {
	        System.out.println("Usage: jlp [-url] <filename>");
	        System.out.println("");
	        System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");

	    }
	    public void stopM()
	    {
	    if (play) {
	    player.close();
	    System.out.println("Close the Sound");
	    play = false;
	    }
	    
	    }

	    public void play()
	            throws JavaLayerException
	    {
	    	if (!play) return;
	            try
	            {
	                System.out.println("playing "+fFilename+"...");
	                InputStream in = null;
	                if (remote == true) in = getURLInputStream();
	                else in = getInputStream();
	                AudioDevice dev = getAudioDevice();
	                player = new Player(in, dev);
	                player.play();
	                
	            }
	            catch (IOException ex)
	            {
	                    throw new JavaLayerException("Problem playing file "+fFilename, ex);
	            }
	            catch (Exception ex)
	            {
	                    throw new JavaLayerException("Problem playing file "+fFilename, ex);
	            }
	    }

	    /**
	     * Jouer fichier de l' URL (Streaming).
	     */
	    protected InputStream getURLInputStream() throws Exception
	    {

	            URL url = new URL(file, fFilename);
	            InputStream fin = url.openStream();
	            BufferedInputStream bin = new BufferedInputStream(fin);
	            return bin;
	    }

	    /**
	     * Jouer un fichier de FileInputStream.
	     */
	    protected InputStream getInputStream() throws IOException
	    {
	            FileInputStream fin = new FileInputStream(fFilename);
	            BufferedInputStream bin = new BufferedInputStream(fin);
	            return bin;
	    }

	    protected AudioDevice getAudioDevice() throws JavaLayerException
	    {
	            return FactoryRegistry.systemRegistry().createAudioDevice();
	    }

	    public void run() {
	    	play = true;
	    	
	    	if (boucle && play) {
	    		
	        while(play)
	        {
	        	if (!play)
	        		return;
	        	System.out.println(" ");
	        	
	            try {
	            	
	                this.play();
	            }catch (Exception e) {e.printStackTrace(); play = false; break;}
	        }
	    	}
	    	else {
	    		try {
	                this.play();
	            }catch (Exception e) {e.printStackTrace();}
	      
	    	}
	    	
	    	
	    }
	
	public boolean isPlay() {
		return play;
	}
	
	
	
	
	
}
