import greenfoot.*;
import javax.imageio.ImageIO;
import  java.io.*;
import java.awt.image.BufferedImage;

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tilemap 
{
    // instance variables - replace the example below with your own
    public String tilemapName;
    public BufferedImage tilemapImage;
    public BufferedImage[] tilemap;
    
    /**
     * Constructor for objects of class Level
     */
    public Tilemap(String tilemapName, int rows, int cols)
    {
        this.tilemapName = tilemapName;        
        try 
        {
            String path = System.getProperty("user.dir") + "//images/" + tilemapName.toLowerCase() + ".png";
            System.out.println(path);
            this.tilemapImage = ImageIO.read(new File(path));
            this.tilemap = splitImageIntoTiles(tilemapImage, rows, cols);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }

    public static BufferedImage[] splitImageIntoTiles(BufferedImage imageWithTiles,  int numberOfTilesAcross,  int numberOfTilesDown) {
         int tileWidth = imageWithTiles.getWidth() / numberOfTilesAcross;
         int tileHeight = imageWithTiles.getHeight() / numberOfTilesDown;
         int totalNumberOfTiles = numberOfTilesDown * numberOfTilesAcross;

         BufferedImage[] tiles = new BufferedImage[totalNumberOfTiles];
        for (int y = 0; y < numberOfTilesDown; y++) {
            for (int x = 0; x < numberOfTilesAcross; x++) {
                tiles[x + y * numberOfTilesAcross] = imageWithTiles.getSubimage(x * tileWidth, y * tileHeight,
                        tileWidth, tileHeight);
            }
        }

        return tiles;
    }
    
    public BufferedImage[] getTilemap()
    {
        return this.tilemap;
    }
    
    public void setTilemapName(String tilemapName)
    {
        this.tilemapName = tilemapName;
    }
   
    public String getTilemapName()
    {
        return this.tilemapName;
    }
}

