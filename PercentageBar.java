import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * A percentage bar that draws a rectagle with the specified percentage area 
 * filled with green and the remaining area filled with red.
 * 
 * @author (Dead Pixels team) 
 * @version (0.1)
 */
public class PercentageBar extends Actor
{
    private double percentage = 1.0;
    private int width;
    private int height;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PercentageBar(int width, int height)
    {
       this.width = width;
       this.height = height;
       setImage(new GreenfootImage(this.width, this.height));
       getImage().setColor(Color.GREEN);
       getImage().fillRect(0, 0, this.width, this.height);
    }
    
    /**
     * Act - do whatever the UIElements wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * [currently repaints the percentage bar]
     */
    public void act() 
    {
        //System.out.printf("%f --  %f\n",percentage, player.getHealthPercentage());
        repaint();
    }
    
    /**
     * sets the width of the percentage bar
     * 
     * @param width new width of the percentage bar
     * 
     * @return     void 
     */
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    /**
     * sets the height of the percentage bar
     * 
     * @param height new height
     * 
     * @return     void 
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    /**
     * Resets the greenfoot image to an appropriate size
     * (Creates a new green foot image. Should only be called at the start)
     * 
     * @param 
     * 
     * @return     void 
     */
    public void refreshImage()
    {
       setImage(new GreenfootImage(this.width, this.height));
       getImage().setColor(Color.GREEN);
       getImage().fillRect(0, 0, this.width, this.height);
    }
    
    /**
     * sets the current percentage of as a decimal between 0 and 1.
     * 
     * @param percentage the current percentage of the bar in decimal form(between 0 and 1).
     * 
     * @return     void 
     */
    public void setPercentage(double percentage)
    {
        this.percentage = percentage;
    }
    
    /**
     * Repaints the percentage bar to the appropriate percentage
     * 
     * @param 
     * 
     *  
     */
    private void repaint()
    {
       //System.out.printf("%d\n",(int)(percentage * (double)100));
       int healthAsPixels = (int)(percentage * (double)this.width);
       
       getImage().setColor(Color.RED);
       getImage().fillRect(healthAsPixels, 0, this.width - healthAsPixels, this.height);
       
       getImage().setColor(Color.GREEN);
       getImage().fillRect(0, 0, healthAsPixels, this.height);
    }
}
