import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Island here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Island extends Actor
{
    public Island() {
        GreenfootImage image = getImage();
        int width = image.getWidth() * 2;
        int height = image.getHeight() * 2;
        
        image.scale(width, height);
        setImage(image);
    }
    /**
     * Act - do whatever the Island wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
