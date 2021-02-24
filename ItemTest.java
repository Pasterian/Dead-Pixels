import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

/**
 * Write a description of class ItemTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ItemTest extends Actor
{
    private String itemName;
    private ArrayList<String> frames;
    private String currentFrame;
    private Boolean isAnimated; 
    private int frame;

    private double xx, yy;

    private long lastFrame;
    private double framesPerSecond; // animation speed
    private int moveSpeed; // per second, not act
    private double secondsPerFrame;

    private int walkSpeed;
    private int walkAnimSpeed;

    private int runSpeed;
    private int runAnimSpeed;
    private GreenfootImage[][] item;
    private GreenfootImage[][] itemImage;
    private boolean idle;
    private int totalFrames;
    private int imageWidth; 
    private int imageHeight;
    
    public ItemTest(String itemName, int imageWidth,int imageHeight)
    {
        this.itemName = itemName;
        this.frames = new ArrayList<String>();
        this.isAnimated = true;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        
        walkSpeed = 20;
        walkAnimSpeed = 15;

        runSpeed = 40;
        runAnimSpeed = 30;

        framesPerSecond = walkAnimSpeed;
        moveSpeed = walkSpeed;

        secondsPerFrame = 1.0 / framesPerSecond;       
        lastFrame = System.nanoTime();

        frame = 0;
        idle = false;

        item = importSprites(itemName, 1);
        itemImage = item;
        
        setImage(itemImage[0][0]);
        getImage().scale(imageWidth,imageHeight);
        //setImage(getImage());
    }

    /**
     * Act - do whatever the ItemTest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // determine how much time has passed since the last act
        long current = System.nanoTime();
        // Find elapsed time - in milliseconds (ms)
        long elapsed = (current - lastFrame) / 1000000;
        lastFrame = current;
        frame++;
        
        if(frame == totalFrames){
            frame = 0;
        } else {
            setImage(itemImage[0][frame]);
            getImage().scale(imageWidth,imageHeight);
            
        }
        /*if (elapsed > secondsPerFrame * 1000){
                // note - the use of the idle variable here is to avoid restarting the animation
                // timer after idle. This way, the first frame after idle starts instantly
                lastFrame = current;
                frame++;
            }
           */
        
          checkPickup();
          
    }   

    private GreenfootImage[][] importSprites (String itemName, int variations){
        Path baseString = Paths.get(System.getProperty("user.dir") + "\\images\\" + "\\items\\" + itemName + "\\");

        if (Files.exists(baseString)) {
            File directory = new File(baseString.toString());
            int fileCount = directory.list().length;
            totalFrames = fileCount;
            GreenfootImage[][] temp = new GreenfootImage[variations][fileCount];
            for (int dir = 0; dir < variations; dir++){
                for (int frm = 0; frm < fileCount; frm++){
               
                    String tempFileName = baseString + "\\" + itemName + "_" + frm +".png";
                    temp[dir][frm] = new GreenfootImage (tempFileName);
                }
            }
            return temp;
        }

        return null;
    }
    
    public void checkPickup()
    {
        //if(isTouching(PlayerTest.class)) removeObject((ItemTest)this.class);
        removeTouching(PlayerTest.class);
    }

    public String getItemName()
    {
        return this.itemName;
    }

    public void setName(String itemName)
    {
        this.itemName = itemName;
    }

    public Boolean isAnimated()
    {
        return this.isAnimated;
    }

    public void setAnimated(Boolean value)
    {
        this.isAnimated = value;
    }
    
}
