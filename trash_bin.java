import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class trash_bin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class trash_bin extends Containers
{
    public trash_bin() {
        GreenfootImage image = getImage();
        int width = (int) (image.getWidth() * 2.4f);
        int height = (int) (image.getHeight() * 2.4f);
        
        image.scale(width, height);
        setImage(image);
    }
    
    protected void addedToWorld(Level level) {
        if (isTouching(trash_bin.class) || isTouching(phone_cabin.class)) {
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            level.addScrollObject(new trash_bin(), randomX, 747);
            
            level.removeObject(this);
        }
    }
    
    /**
     * Act - do whatever the trash_bin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);

        for (Player player : players) { 
            if (player.searchedContainer() == this) {
                World world = getWorld();
                
                System.out.println("Lixo tinha clue: " + HasClue());
            }
        }
    }
    
    
}
