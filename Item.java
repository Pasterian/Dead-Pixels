import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    
    public String itemName;
    public ArrayList<String> frames;
    public String currentFrame;
    public Boolean isAnimated;
    
    public Item(String itemName)
    {
        this.itemName = itemName;
        this.frames = new ArrayList<String>();
        this.isAnimated = true;
        register();
    }
    
    /**
     * Act - do whatever the Item wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        while(isAnimated){
             for(String frame : frames){
                 setImage(frame);
             }
        }    
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
    
    
    public void register()
    {
            Path path = Paths.get(System.getProperty("user.dir") + "\\items\\" + itemName + "\\");
            if (Files.exists(path)) {
                File directory= new File(path.toString());
                int fileCount=directory.list().length;
                
                for(int i=0; i < fileCount ;i++)
                {
                    String currentFrame = path + getItemName() + "_" + i + ".png";
                    System.out.printf("\n[%s] Register Frame: %s", getItemName(), currentFrame);
                    this.frames.add(currentFrame);
                }
        }
    }
    
}
