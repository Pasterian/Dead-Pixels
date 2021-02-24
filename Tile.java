import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class del here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    /**
     * Act - do whatever the del wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    public Boolean doesCollision(Player player)
    {
        player.getWorld().getObjectsAt(player.getX(), player.getY(), Rock.class);
        return true;
    }
}
