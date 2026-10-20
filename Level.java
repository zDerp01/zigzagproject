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
    private int _cameraX = 0;
    private final int CAMERA_LIMIT = 2000;
    
    public String getPressedKey() {
        return _pressedKey;
    }
    
    public int getCameraX() {
        return _cameraX;
    }
    
    public void setCameraX(int value) {
        _cameraX = value;
    }
    
    public int getCameraLimit() {
        return CAMERA_LIMIT;
    }

    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        super(1600, 900, 1, false);
        getBackground().setColor(new Color(40, 40, 50));
        getBackground().fill();
    }
    
    public void act() {
        _pressedKey = Greenfoot.getKey();
    }
    
    public void addScrollObject(ScrollableSprite obj, int x, int y) {
        addObject(obj, x, y);
        obj.setLocationScroll(x);
    }
    
    public void moveWorld(int playerSpeed) {
        for (ScrollableSprite obj : getObjects(ScrollableSprite.class)) {
            if (obj instanceof zig) continue;
            
            obj.update(_cameraX);
        }
    }
}
