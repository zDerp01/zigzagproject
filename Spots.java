import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spots here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Spots extends Sprite
{
    private Player _hiddenPlayer;
    
    public Player GetHiddenPlayer() {
        return _hiddenPlayer;
    }
    
    public void SetHiddenPlayer(Player player) {
        _hiddenPlayer = player;
    }

    /**
     * Act - do whatever the Spots wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        OnInteract();
    }
    
    public abstract void OnInteract();
}
