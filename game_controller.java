import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class game_controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class game_controller extends Controllers
{
    private boolean pressedEnter;
    
    public game_controller() {
        GreenfootImage image = new GreenfootImage(500, 120);
        image.setColor(new Color(0, 0, 0, 180));
        image.fill();

        GreenfootImage title = new GreenfootImage("Foste Visto", 48, Color.WHITE, Color.BLACK);
        image.drawImage(title, (500 - title.getWidth()) / 2, 15);

        GreenfootImage subtitle = new GreenfootImage("Pressiona ENTER para tentar de novo", 24, Color.GRAY, Color.BLACK);
        image.drawImage(subtitle, (500 - subtitle.getWidth()) / 2, 70);

        setImage(image);
    }
    
    @Override
    protected void addedToWorld(World world) {    
        List<Player> players = world.getObjects(Player.class);
        for (Player player : players) {
            player.SetIsRestricted(true);
        }
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter") && !pressedEnter) {
            pressedEnter = true;
            level1 currentLevel = (level1) getWorld();
            currentLevel.lifeController.DecreaseLives();
        }
    }
}
