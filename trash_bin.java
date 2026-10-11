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
        int width = (int) (image.getWidth() * 1.2f);
        int height = (int) (image.getHeight() * 1.2f);
        
        image.scale(width, height);
        setImage(image);
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
