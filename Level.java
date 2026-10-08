import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    private String _pressedKey;
    
    public String getPressedKey() {
        return _pressedKey;
    }

    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1800, 1000, 1, false);
        getBackground().setColor(new Color(40, 40, 50));
        getBackground().fill();
    }
    
    public void act() {
        _pressedKey = Greenfoot.getKey();
    }
}
