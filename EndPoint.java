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
        image.scale(image.getWidth() * 4, image.getHeight() * 4);
        setImage(image);
    }
}
