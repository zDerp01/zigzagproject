import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class level2_wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class level2_wall extends Underlay
{
    /**
     * level2_wall - construtor que redimensiona o sprite para fins estéticos.
     */
    public level2_wall() { 
        GreenfootImage image = getImage();    
        image.scale((int) (image.getWidth() * 1.5f), (int) (image.getHeight() * 1.5f));
        setImage(image);
    }
}
