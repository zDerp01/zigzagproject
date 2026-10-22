import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends ScrollableSprite
{
    private boolean _isMoving, _isFacingRight;
    protected int animTimer, animFrame, animSpeed = 20;
    
    public Entity() {
        GreenfootImage img = getImage();
        img.scale(img.getWidth() * 3, img.getHeight() * 3);
        setImage(img); // para ficar grande no editor
    }
    
    public boolean IsMoving() {
        return _isMoving;
    }
    
    public boolean IsFacingRight() {
        return _isFacingRight;
    }
    
    public void SetMoving(boolean state) {
        _isMoving = state;
    }
    
    public void SetFacingRight(boolean state) {
        _isFacingRight = state;
    }
    
    
    public void act() {
        animate();
    }
    
    protected abstract void animate();
}
