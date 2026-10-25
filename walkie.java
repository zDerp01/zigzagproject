import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class walkie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class walkie extends Spots
{
    /**
     * walkie - construtor que redimensiona o sprite para fins estéticos.
     */
    public walkie() { 
        GreenfootImage image = getImage();    
        image.scale(image.getWidth() * 3, image.getHeight() * 3);
        setImage(image);
    }
    
    public void act() {
        super.act();
        
        //clue_controller clueController = ((Level) getWorld()).getClueController();
        //System.out.println("Clues: " + clueController.GetClues());
        //System.out.println("Collected Clues: " + clueController.GetCollectedClues());
        //System.out.println("Total Collected Clues: " + clueController.GetTotalCollectedClues());
        //System.out.println("Total Clues: " + clueController.GetTotalClues());
    }
    
    /**
     * OnInteract - método que trata da lógica da interação do jogador com este spot, estratégicamente modificando
     * a posição do jogador que interagir e fazendo as devidas recolocações e remoções de objetos.
     */
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);

        for (Player player : players) { 
            if (player.hidesInSpot() == this) { // neste caso usamos o mesmo método mas como "interactWithSpot"
                System.out.println("interacted");
                World world = getWorld();
                
                ((Level) world).audioController.PlaySFX(((Level) world).audioController.GetWalkieSfx());
                clue_controller clueController = ((Level) world).getClueController();
                
                clueController.SetTotalCollectedClues(clueController.GetCollectedClues() + clueController.GetTotalCollectedClues());
                clueController.SetTotalClues(clueController.GetClues() + clueController.GetTotalClues());
                
                if (clueController.GetTotalCollectedClues() >= clueController.GetTotalClues()) {
                    game_controller success = new game_controller(1);
                    world.addObject(success, world.getWidth()/2, world.getHeight()/2); 
                }
                else {
                    game_controller gameover = new game_controller(2);
                    world.addObject(gameover, world.getWidth()/2, world.getHeight()/2); 
                }
                world.removeObject(this);
                return;
            }
        }
    }
}
