import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class game_controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class game_controller extends Controllers
{
    public game_controller() {
        setImage(new GreenfootImage("Foste Visto", 48, Color.WHITE, Color.BLACK));
    }
    
    @Override
    protected void addedToWorld(World world) {
        level1 currentLevel = (level1) world;
        currentLevel.lifeController.DecreaseLives();
    }
}
