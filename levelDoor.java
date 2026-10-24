import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class levelDoor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class levelDoor extends Spots
{
    /**
     * levelDoor - construtor que redimensiona o sprite para fins estéticos.
     */
    public levelDoor() {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 4, image.getHeight() * 4);
        setImage(image);
    }
    
    /**
     * OnInteract - método que trata da lógica da interação do jogador com este spot, estratégicamente modificando
     * a posição do jogador que interagir e fazendo as devidas recolocações e remoções de objetos.
     */
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);

        for (Player player : players) { 
            if (player.hidesInSpot() == this) { // neste caso usamos o mesmo método mas como "entersSpot"
                Level level = (Level) getWorld();
                level2 loadedLevel = new level2(level.getLevelSeed());
                loadedLevel.getLifeController().SetLives(level.lifeController.GetLives());
                
                Greenfoot.setWorld(loadedLevel);
            }
        }
    }
}
