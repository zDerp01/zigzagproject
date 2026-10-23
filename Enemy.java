import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends Entity
{
    private int _gravity;
    private boolean _isDead;
    
    public int GetGravity() {
        return _gravity;
    }
    
    public void SetGravity(int num) {
        _gravity = num;
    }
    
    public boolean IsDead() {
        return _isDead;
    }
    
    public void SetDeadState(boolean state) {
        _isDead = state;
    }
    
    // --------------------------------------------
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        fall();
    }
    
    public void fall() {
        if (!isTouching(Ground.class)) {
            _gravity++;
        }
        else if (_gravity > 0) {
            _gravity = 0;
        }
        setLocation(getX(), getY() + _gravity);
    }
}
