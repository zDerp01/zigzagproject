import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Sprite
{
    private GreenfootImage imageRight, imageLeft;
    
    public Entity() { 
        GreenfootImage image = getImage();
        int width = (int) (image.getWidth() * 1.5f);
        int height = (int) (image.getHeight() * 1.5f);
        
        image.scale(width, height);
        setImage(image);
        
        imageLeft = getImage();
        imageRight = new GreenfootImage(imageLeft);
        imageRight.mirrorHorizontally();
    }
    
    public GreenfootImage GetImageLeft() {
        return imageLeft;
    }
    
    public GreenfootImage GetImageRight() {
        return imageRight;
    }
    
    public void SetImageLeft(GreenfootImage image) {
        imageLeft = image;
    }
    
    public void SetImageRight(GreenfootImage image) {
        imageRight = image;
    }
    
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
