import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends ScrollableSprite
{
    /**
     * Background - construtor que redimensiona todas as imagens das suas subclasses para fins estéticos.
     */
    public Background() {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 4, image.getHeight() * 4);
        setImage(image);
    }
}
