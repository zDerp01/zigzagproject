import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndPoint extends ScrollableSprite
{
    /**
     * EndPoint - construtor que redimensiona o sprite para fins estéticos.
     */
    public EndPoint() {
        GreenfootImage image = getImage();
        int width = image.getWidth() * 4;
        int height = image.getHeight() * 4;
        
        image.scale(width, height);
        setImage(image);
    }
}
