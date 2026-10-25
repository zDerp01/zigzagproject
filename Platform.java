import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends ScrollableSprite
{
    /**
     * Platform - construtor que redimensiona o sprite para fins estéticos.
     */
    public Platform() { 
        GreenfootImage image = getImage();    
        image.scale(image.getWidth() * 4, image.getHeight() * 4);
        setImage(image);
    }
}
