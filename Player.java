import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A player object is a movable character used by a player.
 *
 * @author (Dead Pixels team)
 * @version (0.1)
 */
public class Player extends Actor
{
    private Weapon weapon;
    private int meleeDamage = 1;
    private int speed = 2;
    private int[] scale = { 30, 30};
    private SpriteAnimator spriteAnimator;
    private int minHealth = 0;
    private int health = 1000;
    private int maxHealth = 1000;
    PercentageBar healthBar = new PercentageBar(32, 16);

    private int[] vector = { 0, 0};

    /**
     * Creates a player object  with its sprite info
     *
     * @param spritePath relative path from the current folder to the folder containing the sprite images e.g. './imagefolder/'
     * @param spriteName file name of the sprite eg. 'zombie'
     * @param spriteExt file extension of the image eg. '.png'
     * @param animateNumMin minimun number of the first image file. image files must have sprite numbers attached e.g. spriteImage1.png
     * @param animateNumMax maximun number of the last image file. image files must have sprite numbers attached e.g. spriteImage7.png
     * @param animateDelay delay for animating each frame/sprite image
     * @param scaleWidth number of pixels to scale the sprite to
     * @param scaleHiegh number of pixels to scale the sprite to
     * @param numPrefix a prefix put before a number eg. "--" in spriteimages--1.
     */
    public Player(String spritePath, String spriteName, String spriteExt, String numPrefix, int animateNumMin, int animateNumMax, int animateDelay, int scaleWidth, int scaleHeight)
    {
        super();
        this.spriteAnimator = new SpriteAnimator(this, spritePath, spriteName, spriteExt, numPrefix, animateNumMin, animateNumMax, animateDelay);
        setImage(this.spriteAnimator.getAnimationPathAsString());
        this.scale = new int[]{scaleWidth, scaleHeight};
        //scaleImage(this.scale[0], this.scale[1]);
    }

    /**
     * sets the unit vector for the player object
     *
     * @param  x   unit vector for the object on the x axis
     * @param  y   unit vector for the object on the y axis
     *
     * @return void
     */
    private void setVector(int x, int y)
    {
        this.vector[0] = x;
        this.vector[1] = y;
    }

    /**
     * gets the the objects vector (represented as an x,y value)
     *
     * @param  x   unit vector for the object on the x axis
     * @param  y   unit vector for the object on the y axis
     *
     * @return  an integer array
     */
    private int[] getVector()
    {
        return this.vector;
    }

    /**
     * gets the the objects speed
     *
     * @param
     *
     * @return  an integer value
     */
    public int getSpeed()
    {
        return this.speed;
    }

    /**
     * gets the the objects health
     *
     * @param
     *
     * @return  an integer value
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * gets the the players health as a percentage in decimal
     *
     * @param
     *
     * @return  double signifying the players health as a percentage
     */
    public double getHealthPercentage()
    {
        //System.out.printf("%d / %d\n",health, maxHealth);
        //System.out.println((double)health/maxHealth);
        return (double)health/(double)maxHealth;
    }

    /**
     * sets the the objects health
     *
     * @param health value of the integers health
     *
     * @return  void
     */
    public void setHealth(int health)
    {
        this.health = health;
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

        // add health bar on top of player
        healthBar.setWidth(getImage().getWidth() * 2);
        healthBar.setHeight(4);
        healthBar.refreshImage();
        healthBar.setPercentage(getHealthPercentage());
        getWorld().addObject(healthBar, getX(), getY() - getImage().getHeight());
    }

    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * [currently handles health status, mouse to weapon movement, movement, weapon pickup, and weapon firing]
     */
    public void act()
    {
        // adjust players health bar(percentage and position)
        healthBar.setPercentage(getHealthPercentage());
        healthBar.setLocation(getX(), getY() - getImage().getHeight());

        checkHealthStatus();

        handleArrowKeysListener();
        moveAround();

        //scaleImage(this.scale[0], this.scale[1]);

        Weapon weaponTemp = (Weapon)getOneIntersectingObject(Weapon.class);
        if (weaponTemp != null) this.weapon = weaponTemp;

        handleMouseToWeaponTurn();

        handleMouseRightCLicked();
    }


    /**
     * Moves the player around based on keyboard input
     *
     * @param
     *
     * @return  void
     */
    public void handleMouseRightCLicked()
    {
        if(Greenfoot.mousePressed(null))
        {
            if (this.weapon != null)
            {
                this.weapon.fire();
            }
            else
            {
                Zombie enemy = (Zombie)getOneIntersectingObject(Zombie.class);
                if (enemy != null)
                {
                    enemy.reduceHealth(meleeDamage);
                }
            }
        }
    }

    /**
     * Moves the player around based on keyboard input
     *
     * @param
     *
     * @return  void
     */
    public void moveAround()
    {
        setLocation(getX() + this.vector[0] * speed, getY() + this.vector[1] * speed);

        spriteAnimator.animate(this.vector);

        if (this.weapon != null) this.weapon.setLocation(getX(), getY());

        setVector( 0, 0);
    }

    /**
     * listens to keyboard input(arrow keys to be specifc) to move player
     *
     * @param
     *
     * @return  void
     */
    public void handleArrowKeysListener()
    {
        //if (getOneIntersectingObject(Zombie.class) != null) return;

        if (Greenfoot.isKeyDown("w"))
        {
            setVector( 0, -1);
            if (Greenfoot.isKeyDown("a"))
            {
                setVector( -1, -1);
            }
            if (Greenfoot.isKeyDown("d"))
            {
                setVector( 1, -1);
            }
        }
        else if (Greenfoot.isKeyDown("s"))
        {
            setVector( 0, 1);
            if (Greenfoot.isKeyDown("a"))
            {
                setVector( -1, 1);
            }
            if (Greenfoot.isKeyDown("d"))
            {
                setVector( 1, 1);
            }
        }
        else if (Greenfoot.isKeyDown("a"))
        {
            setVector( -1, 0);
        }
        else if (Greenfoot.isKeyDown("d"))
        {
            setVector( 1, 0);
        }
    }

    /**
     * adds a weapon to the users inventory
     *
     * @param
     *
     * @return  void
     */
    public void pickUpWeapon()
    {
        this.weapon = new Weapon();
    }

    /**
     * lisntens to mouse input and rotates the players weapon
     *
     * @param
     *
     * @return  void
     */
    public void handleMouseToWeaponTurn()
    {
        if (this.weapon != null)
        {
            if(Greenfoot.getMouseInfo() != null) this.weapon.turnTowards(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
    }

    /**
     * scales the players sprite image
     *
     * @param width number of pixels for width
     * @param height number of pixels for height
     *
     * @return  void
     */
    public void scaleImage(int width, int height)
    {
        getImage().scale(width, height);
    }

    /**
     * creates and sets a new animator object for this player
     *
     * @param spritePath relative path from the current folder to the folder containing the sprite images e.g. './imagefolder/'
     * @param spriteName file name of the sprite eg. 'zombie'
     * @param spriteExt file extension of the image eg. '.png'
     * @param animateNumMin minimun number of the first image file. image files must have sprite numbers attached e.g. spriteImage1.png
     * @param animateNumMax maximun number of the last image file. image files must have sprite numbers attached e.g. spriteImage7.png
     * @param animateDelay delay for animating each frame/sprite image
     * @param numbPrefix a prefix put before a number eg. "--" in spriteimages--1.
     *
     * @return  void
     */
    public void setSpriteAnimator(String spritePath, String spriteName, String spriteExt, String numPrefix, int animateNumMin, int animateNumMax, int animateDelay)
    {
        this.spriteAnimator = new SpriteAnimator(this, spritePath, spriteName, spriteExt, numPrefix, animateNumMin, animateNumMax, animateDelay);
    }

    /**
     * reduces the players health
     *
     * @param reduction number of health to reduce by
     *
     * @return  void
     */
    public void reduceHealth(int reduction)
    {
        if (getHealth() <= minHealth) return;
        setHealth(getHealth() - reduction);
    }

    /**
     * checks the players health
     *
     * @param
     *
     * @return  void
     */
    private void checkHealthStatus()
    {
        /*if (health <= minHealth)
        {
        System.out.println("dead");
        getWorld().removeObject(healthBar);
        getWorld().removeObject(this);
        }*/
    }
}
