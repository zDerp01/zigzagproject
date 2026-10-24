import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartPoint extends ScrollableSprite
{
    /**
     * StartPoint - construtor que redimensiona o sprite para fins estéticos.
     */
    public StartPoint() {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 4, image.getHeight() * 4);
        setImage(image);
    }
}
