import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Weapon object is an item wielded by a Player(Actor) and is used as a survival tool.
 *
 * @author (Dead Pixels team)
 * @version (0.1)
 */
public class Weapon extends Actor
{
    private int[] scale = {1, 1};
    private int height = 16/2;
    private int width = 16;

    /**
     * Creates a Weapon object which can be picked up and used by a Player(Actor)
     */
    public Weapon()
    {
        setImage(new GreenfootImage(this.height, this.width));
        getImage().setColor(Color.BLACK);
        getImage().fillRect(0, 0, this.width, this.height/4);
        getImage().fillRect(0, this.height/4, this.width/6, this.height/4);
        //getImage().fillRect(getImage().getWidth()/2, getImage().getHeight()/2, this.width/2, this.height/2);
        //getImage().fillRect(getImage().getWidth()/2, getImage().getHeight()/2, this.width*2, this.height/4);
    }

    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * [Does nothing for now]
     */
    public void act()
    {

    }

    /**
     * sets up the objects
     * [currently sets the scale of the objects sprite]
     *
     * @param
     *
     * @return  void
     */
    public void prepare()
    {
        //scaleImage(this.scale[0], this.scale[1]);
    }

    /**
     * Fires a projectile object in the current rotational direction of the weapon and moves at a set speed.
     *
     * @param
     *
     * @return     void
     */
    public void fire()
    {
        Projectile projectile = new Projectile();
        getWorld().addObject(projectile, getX(), getY());
        projectile.setRotation(getRotation());
    }
}
