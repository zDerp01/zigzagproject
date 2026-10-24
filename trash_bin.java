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
    /**
     * trash_bin - construtor que redimensiona o sprite para fins estéticos.
     */
    public trash_bin() {
        GreenfootImage image = getImage();
        image.scale((int) (image.getWidth() * 2.4f), (int) (image.getHeight() * 2.4f));
        setImage(image);
    }
    
    /**
     * Act - do whatever the trash_bin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * garante que os lixos não aparecem uns em cima dos outros na geração aleatória.
     */
    public void act()
    {
        super.act();
        
        if (isTouching(trash_bin.class)) {
            Level level = (Level) getWorld();
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            level.addScrollObject(new trash_bin(), randomX, 747);
            replaceEntitiesAndPointsOnTop();
            
            level.removeObject(this);
            return;
        }

    }
    
    /**
     * OnInteract - método que adiciona uma clue aos jogadores se este objeto a tiver.
     */
    @Override
    public void OnInteract() {
        World world = getWorld();
        List<Player> players = world.getObjects(Player.class);

        for (Player player : players) { 
            if (player.searchedContainer() == this) {
                if (HasClue()) {
                    SetHasClue(false);
                    ((level1) world).clueController.AddCollectedClue();
                }
            }
        }
    }
    
    
}
