import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends ScrollableSprite
{
    public Background() {
        GreenfootImage image = getImage();
        int width = image.getWidth() * 4;
        int height = image.getHeight() * 4;
        
        image.scale(width, height);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
