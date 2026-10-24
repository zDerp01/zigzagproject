import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class contentor extends Spots
{
    private int _contentorType = -1, _attempts;
    private Random rng;
    
    /**
     * addedToWorld - trata de escolher uma variação de cor aleatória para o contentor através da seed do próprio nível e da posição X para não estar
     * sempre a mudar de cor na mesma seed e para terem cores diferentes uns dos outros.
     * também redimensiona a imagem para fins estéticos.
     * 
     * trata também de atribuir rng à variável rng, com base na seed do nível.
     */
    @Override
    protected void addedToWorld(World world) {    
        int levelSeed = ((Level) getWorld()).getLevelSeed();
        
        if (_contentorType == -1) {
            Random typeRng = new Random(levelSeed + getX());
            _contentorType = typeRng.nextInt(3);
        }
        
        Random typeRng = new Random(levelSeed + getExactX()); // seed baseada na seed do level + posicao
        
        GreenfootImage image = new GreenfootImage("sprites/contentor/contentor" + _contentorType + ".png");
        image.scale((int) (image.getWidth() * 3.5f), (int) (image.getHeight() * 3.5f));
        setImage(image);
        
        rng = new Random(levelSeed + _attempts);
    }
    
    /**
     * Act - do whatever the car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * garante que os contentores não aparecem uns em cima dos outros ou tapam outros objetos mais importantes na geração aleatória.
     */
    public void act()
    {
        super.act();
        
        if (isTouching(contentor.class) || isTouching(trash_bin.class)) {
            Level level = (Level) getWorld();
            
            int randomX = rng.nextInt(3400) + 300;
            
            contentor newContentor = new contentor();
            newContentor._attempts = this._attempts + 1;
            
            level.addScrollObject(newContentor, randomX, 710);
            replaceEntitiesAndPointsOnTop();
            
            level.removeObject(this);
            return;
        }
    }
    
    /**
     * OnInteract - método que trata da lógica da interação do jogador com este spot, estratégicamente modificando
     * a posição do jogador que interagir e fazendo as devidas recolocações e remoções de objetos.
     */
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);
        List<Entity> entities = getWorld().getObjects(Entity.class);

        for (Player player : players) { 
            if (player.hidesInSpot() == this) {
                World world = getWorld();
                
                if (GetHiddenPlayer() == player) {        
                    SetHiddenPlayer(null);
                    player.SetIsHidden(false);
                    player.SetHiddenSpot(null);
                }
                else if (GetHiddenPlayer() == null) {
                    int cX = getX();
                    int cY = getY();
                    
                    world.removeObject(this);
                    world.addObject(this, cX, cY);
                    
                    player.setLocation(cX + 70, cY - 15);
                    player.setLocationScroll(getExactX() + 70);
                    player.SetHiddenSpot(this);
                    
                    SetHiddenPlayer(player);
                    player.SetIsHidden(true);
                }
                
                // recolocar entidades e startpoints para aparecerem por cima
                replaceEntitiesAndPointsOnTop();
            }
        }
    }
}
