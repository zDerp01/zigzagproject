import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class trash_bin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class trash_bin extends Containers
{
    private Random rng;
    private int _attempts;
    
    /**
     * trash_bin - construtor que redimensiona o sprite para fins estéticos.
     */
    public trash_bin() {
        GreenfootImage image = getImage();
        image.scale((int) (image.getWidth() * 2.4f), (int) (image.getHeight() * 2.4f));
        setImage(image);
    }
    
    /**
     * addedToWorld - trata de atribuir rng à variável rng, com base na seed do nível.
     */
    @Override
    protected void addedToWorld(World world) {    
        rng = new Random(((Level) getWorld()).getLevelSeed() + _attempts);
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
        
        if (isTouching(trash_bin.class) || !getObjectsInRange(200, Platform.class).isEmpty()) {
            Level level = (Level) getWorld();
            
            int randomX = rng.nextInt(3300) + 300;
            
            trash_bin newTrash = new trash_bin();
            newTrash._attempts = this._attempts + 1;
            newTrash.SetHasClue(this.HasClue());
            
            level.addScrollObject(newTrash, randomX, 747);
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
            if (player.GetSearch() == this) {
                if (HasClue() && player.hasFinishedSearch()) {
                    SetHasClue(false);
                    player.jump(true);
                    ((Level) world).clueController.AddCollectedClue();
                    ((Level) world).audioController.PlaySFX(((Level) world).audioController.GetFoundClueSfx());
                }
            }
        }
    }
}
