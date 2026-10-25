import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends ScrollableSprite
{
    /**
     * Ground - construtor que redimensiona o sprite para fins estéticos.
     */
    public Ground() { 
        GreenfootImage image = getImage();    
        image.scale(image.getWidth() * 2, image.getHeight() * 2);
        setImage(image);
    }
}
