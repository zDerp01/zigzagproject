import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zag extends Player
{
    /**
     * Act - do whatever the zag wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    @Override
    public void move() {
        int moveFactor;
        
        if (Greenfoot.isKeyDown("left")) {
            moveFactor = -GetSpeed();
            setImage(GetImageLeft());
        }
        else if (Greenfoot.isKeyDown("right")) {
            moveFactor = GetSpeed();
            setImage(GetImageRight());
        }
        else {
            moveFactor = 0;
        }
        
        setLocation(getX() + moveFactor, getY());
    }
    
    @Override
    public void jump() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("up")) {
            SetGravity(GetJumpStrength());
        }
    }
}
