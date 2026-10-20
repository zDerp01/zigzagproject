import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Interactables here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Containers extends ScrollableSprite
{
    private boolean _hasClue;
    
    public boolean HasClue() {
        return _hasClue;
    }
    
    public void SetHasClue(boolean state) {
        _hasClue = state;
    }
    
    /**
     * Act - do whatever the Interactables wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        OnInteract();
    }
    
    public abstract void OnInteract();
}
