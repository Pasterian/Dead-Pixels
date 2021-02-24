import greenfoot.*;  
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
/**
 * Write a description of class MenuGameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenuGameWorld extends World {
  
    /**
     * Constructor for objects of class MenuGameWorld.
     * 
     */
    public MainMenuGameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 580, 1);
        prepare();
        addMenu();
        Greenfoot.start();
    }
 
    
    public void prepare()
    {
        MenuGameText logo = new MenuGameText();
        addObject(logo, getWidth()/2, getHeight()/2);
       
    }
    
    public void addMenu()
    {
        int x = 0;
        int y = 0;
        String[] menuItems = {"Play", "Settings", "About", "Quit"};
        for(int i=0; i< menuItems.length; i++)
        {
            //rect = new Rect();
        }
    }
}
