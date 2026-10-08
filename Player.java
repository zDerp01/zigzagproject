import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Entity
{
    private int _gravity, _speed = 4, _jumpStrength = -10;
    private boolean _isHidden = false;
    
    public int GetGravity() {
        return _gravity;
    }
    
    public int GetSpeed() {
        return _speed;
    }
    
    public int GetJumpStrength() {
        return _jumpStrength;
    }
    
    public boolean IsHidden() {
        return _isHidden;
    }
    
    public void SetGravity(int num) {
        _gravity = num;
    }
    
    public void SetSpeed(int num) {
        _speed = num;
    }
    
    public void SetJumpStrength(int num) {
        _jumpStrength = num;
    }
    
    public void SetIsHidden(boolean state) {
        _isHidden = state;
    }
    
    // --------------------------------------------
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (!_isHidden) {
            move();
            fall();
            jump();
        }
    }
    
    public abstract void move();

    public abstract void jump();
    
    public abstract Spots interactedWithSpot();
    
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