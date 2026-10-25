import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Overlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Overlay extends ScrollableSprite
{
    /**
     * Overlay - construtor que redimensiona o sprite para fins estéticos.
     */
    public Overlay() { 
        GreenfootImage image = getImage();    
        image.scale(image.getWidth() * 2, image.getHeight() * 2);
        setImage(image);
    }
}
