import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Sprite
{
    int gravity, speed;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        fall();
    }
    
    public void fall() {
        if (!isTouching(Ground.class)) {
            gravity++;
        }
        else {
            gravity = 0;
        }
        setLocation(getX(), getY() + gravity);
    }
}
