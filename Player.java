import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Sprite
{
    private int gravity, speed = 4, jumpStrength = -10;
    private GreenfootImage imageRight, imageLeft;
    
    public int GetGravity() {
        return gravity;
    }
    
    public int GetSpeed() {
        return speed;
    }
    
    public int GetJumpStrength() {
        return jumpStrength;
    }
    
    public GreenfootImage GetImageLeft() {
        return imageLeft;
    }
    
    public GreenfootImage GetImageRight() {
        return imageRight;
    }
    
    public void SetGravity(int num) {
        gravity = num;
    }
    
    public void SetSpeed(int num) {
        speed = num;
    }
    
    public void SetJumpStrength(int num) {
        jumpStrength = num;
    }
    
    public void SetImageLeft(GreenfootImage image) {
        imageLeft = image;
    }
    
    public void SetImageRight(GreenfootImage image) {
        imageRight = image;
    }
    
    // --------------------------------------------
    
    public Player () {
        imageLeft = getImage();
        imageRight = new GreenfootImage(imageLeft);
        imageRight.mirrorHorizontally();
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        fall();
        jump();
    }
    
    public abstract void move();

    public abstract void jump();
    
    public void fall() {
        if (!isTouching(Ground.class)) {
            gravity++;
        }
        else if (gravity > 0) {
            gravity = 0;
        }
        setLocation(getX(), getY() + gravity);
    }
}
