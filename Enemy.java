import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends Entity
{
    private int gravity;
    
    public int GetGravity() {
        return gravity;
    }
    
    public void SetGravity(int num) {
        gravity = num;
    }
    
    // --------------------------------------------
    
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
        else if (gravity > 0) {
            gravity = 0;
        }
        setLocation(getX(), getY() + gravity);
    }
}
