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
     * OnInteract - método que trata da lógica da interação do jogador com este spot, transportando os jogadores assim para o próximo nível.
     */
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);

        for (Player player : players) { 
            if (player.hidesInSpot() == this) { // neste caso usamos o mesmo método mas como "entersSpot"
                Level level = (Level) getWorld();
                level2 loadedLevel = new level2(level.getLevelSeed());
                loadedLevel.getLifeController().SetLives(level.getLifeController().GetLives());
                loadedLevel.getScoreController().SetScore(level.getScoreController().GetScore());
                
                loadedLevel.getClueController().SetTotalCollectedClues(level.getClueController().GetTotalCollectedClues() + level.getClueController().GetCollectedClues());
                //loadedLevel.getClueController().SetCollectedClues(level.getClueController().GetCollectedClues());
                loadedLevel.getClueController().SetTotalClues(level.getClueController().GetTotalClues() + level.getClueController().GetClues());
                
                Greenfoot.setWorld(loadedLevel);
            }
        }
    }
}
