import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class streetlamp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class street_lamp extends Background
{
    private GreenfootImage[] blinkAnim;
    private int animTimer, animFrame, animSpeed = 10;
    private boolean _blinking;
    
    public street_lamp() {     
        blinkAnim = loadAnimation("sprites/streetlamp/blink/blink", 4, 4);
    }
    
    /**
     * Act - do whatever the streetlamp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animate();
    }
    
    protected void animate() {
        if (!_blinking) {
            setImage(blinkAnim[0]);
    
            if (Greenfoot.getRandomNumber(500) == 1) {
                _blinking = true;
                animFrame = 0;
                animTimer = 0;
            }
        }
        else {
            animTimer++;
            if (animTimer >= animSpeed) {
                animTimer = 0;
                animFrame++;
                
                if (animFrame >= blinkAnim.length) {
                    animFrame = 0;
                    _blinking = false;
                    setImage(blinkAnim[0]);
                }
                else {
                    setImage(blinkAnim[animFrame]);
                }
            }
        }
    }
}
