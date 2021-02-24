import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

/**
 * A SpriteAnimator is an object that is passed the path of sprite images, along with the number of images and an animation speed and finally an 'Actor' object to animate.
 * Everytime its animate function is called, it animates one frame for the actor object.
 *
 * @author (Dead Pixels team)
 * @version (0.1)
 */
public class  SpriteAnimator
{
    private Player objToAnimate;
    private String spritePath; // path to sprite (should be relative) eg. ./home/sprites/animals/
    private String spriteName; // name of file eg. human
    private String spriteExt; // file extension eg. png
    private String numPrefix;
    private int animateNum; // current sprite image
    private int animateNumMin; // number of first sprite eg. 1 in sprite1
    private int animateNumMax; // number of last sprite eg. 6 in sprite6
    private int animateDelay;

    private String[] objDir = {"right", "down"}; // string represention of the player objects, unit vector

    private int counter = 0;

    /**
     * Creates a SpriteAnimator object which can be used to animate sprites
     *
     * @param  objToAnimate  a object that is a subclass of the Actor class
     * @param spritePath relative path from the current folder to the folder containing the sprite images e.g. './imagefolder/'
     * @param spriteName file name of the sprite eg. 'zombie'
     * @param spriteExt file extension of the image eg. '.png'
     * @param animateNumMin minimun number of the first image file. image files must have sprite numbers attached e.g. spriteImage1.png
     * @param animateNumMax maximun number of the last image file. image files must have sprite numbers attached e.g. spriteImage7.png
     * @param animateDelay delay for animating each frame/sprite image
     * @param numPrefix a prefix put before a number eg. "--" in spriteimages--1.
     */
    public SpriteAnimator(Player objToAnimate, String spritePath, String spriteName, String spriteExt, String numPrefix, int animateNumMin, int animateNumMax, int animateDelay)
    {
        this.objToAnimate = objToAnimate;
        this.spritePath = spritePath;
        this.spriteName = spriteName;
        this.spriteExt = spriteExt;
        this.animateNum = animateNumMin;
        this.animateNumMin = animateNumMin;
        this.animateNumMax = animateNumMax;
        this.animateDelay = animateDelay;
        this.numPrefix = numPrefix;
    }

    /**
     * Animates a sprite based on the details in the sprite object
     *
     * @param  vector   an integer array which holds a unit vector of a sprite.
     *
     * @return     void
     */
    public void animate(int[] vector)
    {
        if (vector.length < 0 || vector.length > 2 || vector.length == 1) return; // needs exactly 2 numbers in array

        int x = vector[0], y = vector[1];

        counter++;

        // player standing still
        if (x == 0 && y == 0)
        {
            if (animateNum != animateNumMin)
            {
                // try catch for when player field is null
                try {
                    if (counter % (animateDelay + (animateDelay - objToAnimate.getSpeed() < 0 ? 0 : animateDelay - objToAnimate.getSpeed())) == 0)  animateNum++;
                    if (animateNum > animateNumMax) animateNum = animateNumMin;

                    objToAnimate.setImage(getAnimationPathAsString());
                    if (objDir[0].equals("left")) objToAnimate.getImage().mirrorHorizontally();
                }
                catch (Exception exception)
                {
                    //TODO:
                }
            }
            return;
        }

        objDir[0] = (x != 0 && x == -1) ? "left": (x != 0 && x == 1) ?  "right" : objDir[0];
        objDir[1] = (y != 0 && y == -1) ? "up": (x != 0 && x == 1) ? "down" : objDir[1];

        // try catch for when player field is null
        try {
            objToAnimate.setImage(getAnimationPathAsString());
            if ( objDir[0].equals("left")) objToAnimate.getImage().mirrorHorizontally();

            if (counter % (animateDelay + (animateDelay - objToAnimate.getSpeed() < 0 ? 0 : animateDelay - objToAnimate.getSpeed())) == 0) animateNum++;
        }
        catch (Exception exception)
        {
            //TODO:
        }
        if (animateNum > animateNumMax) animateNum = animateNumMin;
    }

    /**
     * Provides the dynamic path of an image
     *
     * @param
     *
     * @return     String which should be the path of an image file
     */
    public String getAnimationPathAsString()
    {
        return spritePath + spriteName + numPrefix + animateNum + spriteExt;
    }
}
