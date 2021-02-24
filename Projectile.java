import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    private int speed = 10;
    private int damage = 2;
    
    public Projectile()
    {
       setImage(new GreenfootImage(10, 2));
       getImage().setColor(Color.RED);
       getImage().fillRect(0, 0, 10, 2);    
    }
    
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(this.speed);
        checkForImpact();
    }  
    
    public void checkForImpact()
    {
        if (isAtEdge()) 
        {
            getWorld().removeObject(this); 
            return;
        }
        
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if (enemy != null) 
        {
            enemy.reduceHealth(damage);
            getWorld().removeObject(this);
        }
    }
}
