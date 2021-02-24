import greenfoot.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.util.Map;
 
/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends Actor
{
    // instance variables - replace the example below with your own
    public int level;
    public String levelNickname;
    public GreenfootImage gImage;
    public GreenfootImage levelImage;

    public Level(int level, String levelNickname)
    {
        this.level = level;
        this.levelNickname = levelNickname;
    }
    
    
    public int getLevel()
    {
        return this.level;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public String getLevelName()
    {
        return this.levelNickname;
    }
    
    public void setLevelNickname(String levelNickname)
    {
        this.levelNickname = levelNickname;
    }
   
    public GreenfootImage generateLevel(Map<Class, Integer> tiles)
    {
        BufferedImage bufImage;
        BufferedImage gBufImg;
        Graphics2D graphics;
        int imageX = 0;
        int imageY = 0;

    try {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "//maps/" + levelNickname + ".csv"));  
        sc.useDelimiter(",");   //sets the delimiter pattern 
    while (sc.hasNextInt())  //returns a boolean value  
    {
        int tileID = sc.nextInt();
        System.out.println("Reading Tile: " + tileID);
        
        
        
    }

    } catch (IOException e) {
        e.printStackTrace();
    }
    
    try {
            // retrieve image
        //File outputfile = new File("level.png");
        //ImageIO.write(levelImage.getAwtImage(), "png", outputfile);
    
    } catch (Exception e) {
        e.printStackTrace();
    }
    
        return levelImage;

    }
}

