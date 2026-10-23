import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    Button playBtn = new Button(), exitBtn = new Button();

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        super(1550, 900, 1, false);
        
        addObject(playBtn, getWidth() / 2 + 480, getHeight() / 2 - 60);
        addObject(exitBtn, getWidth() / 2 + 480, getHeight() / 2 + 120);
    }
    
    public void act() {
        OnClick();
    }
    
    public void OnClick() {
        if (playBtn.Clicked()) {
            Greenfoot.setWorld(new level1(Greenfoot.getRandomNumber(5000)));
        }
        else if (exitBtn.Clicked()) {
            System.exit(0);
        }
    }
}
