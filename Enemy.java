import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int speed = 1;
    private int health = 20;
    private int damage = 4;
    
    public Enemy()
    {
        
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {                
        seek();
        checkForImpact();
        checkHealthStatus();
    }  
    
    public int getHealth()
    {
        return this.health;
    }
    
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    public void seek()
    {
        move(speed);
        List<Player> players = getWorld().getObjects(Player.class);
        
        if (!players.isEmpty()) 
        {
            if (players.get(0) != null) turnTowards(players.get(0).getX(), players.get(0).getY());
        }
    }
    
    public void checkForImpact()
    {
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player != null) 
        {
            player.reduceHealth(damage);
        }
    }
    
    public void reduceHealth(int reduction)
    {
        setHealth(getHealth() - reduction);
    }
    
    private void checkHealthStatus()
    {
        if (health < 1) getWorld().removeObject(this);
    }
}
