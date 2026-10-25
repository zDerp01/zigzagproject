import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class instructions_controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instructions_controller extends Controllers
{
    private boolean pressedEnter;
    
    /**
     * Act - do whatever the instructions_controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.isKeyDown("enter") && !pressedEnter) {
            pressedEnter = true;
            Greenfoot.setWorld(new level1(Greenfoot.getRandomNumber(5000)));
            getWorld().removeObject(this);
        }
    }
}
