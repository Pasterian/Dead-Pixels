import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends Actor
{
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public static Boolean isMenuActive = false;
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("escape")){ 
            System.out.println("escape");

            this.isMenuActive = (isMenuActive) ? false: true;
        }
        
        if(isMenuActive){
            System.out.println("Menu Opened");
        } else {
            System.out.println("Menu Closed");
        }
    }
    
    
}
