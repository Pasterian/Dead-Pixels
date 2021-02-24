import greenfoot.*;

public class MenuGameText extends Menu
{
    private int time = 0;
    private int transitiontime = 3;
    private int speed;
    private int canpress = 0;
    public static final GreenfootSound menuMusic = new GreenfootSound(System.getProperty("user.dir") + "/sounds/MenuMusic.mp3");
    public static final GifImage menuImage = new GifImage("menuImage.gif");
    public boolean menumusic = true;
    
    public MenuGameText()
    {
    }
    
    public void act()
    {
        //getImage().setTransparency(time);
        keyHandler();
        setImage(menuImage.getCurrentImage());
        menuMusic();
        checkStart();
        
    }
    
    public void keyHandler()
    {
         String key = Greenfoot.getKey();

        if (key != null){
            if (key.equals("b")){
                //
            }
            if (key.equals("n")){
                //
            }
        }
        
        /*
        time = time + speed;
        if(time == 252)
        {
            speed = -3;
        }
        if(time == 0)
        {
            speed = 3;
        }
        */
    }
    
    public void menuMusic()
    {
        if(menumusic)
        {
            if (menuMusic.isPlaying() == false)
            {
                //menuMusic.playLoop();
            }
        }
        else
        {
            menuMusic.stop();
        }
    }
    public void checkStart()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            if(canpress == 0)
            {
                setTransitionTime();
                canpress = 1;
            }    
        }
    }
    public void setTransitionTime()
    {
        transitiontime = 2;
    }
    public void setTransition()
    {
        transitiontime = transitiontime - 1;
        if(transitiontime == 0)
        {
            //getWorld().addObject(new TransitionFade(), 377, 280);
        }
    }
}
