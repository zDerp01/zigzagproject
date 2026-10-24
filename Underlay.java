import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Underlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Underlay extends ScrollableSprite
{
    /**
     * Underlay - construtor que redimensiona o sprite para fins estéticos.
     */
    public Underlay() { 
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 2, image.getHeight() * 2);
        setImage(image);
    }
}
