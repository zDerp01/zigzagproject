import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class phone_cabin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class phone_cabin extends Spots
{
    private boolean _hasClue;
    private Random rng;
    private int _attempts;
    
    /**
     * HasClue - retorna se o container tem ou não uma clue.
     */
    public boolean HasClue() {
        return _hasClue;
    }
    
    /**
     * SetHasClue - modifica se o container tem ou não uma clue.
     */
    public void SetHasClue(boolean state) {
        _hasClue = state;
    }
    
    /**
     * phone_cabin - construtor que redimensiona o sprite para fins estéticos.
     */
    public phone_cabin() {
        GreenfootImage image = getImage();
        image.scale((int) (image.getWidth() * 3f), (int) (image.getHeight() * 3f));
        setImage(image);
    }
    
    /**
     * phone_cabin - OVERLOAD: construtor que permite definir se o objeto aparece com clue.
     */
    public phone_cabin(boolean hasClue) {
        this();
        SetHasClue(hasClue);
    }
    
    /**
     * addedToWorld - trata de atribuir rng à variável rng, com base na seed do nível.
     */
    @Override
    protected void addedToWorld(World world) {  
        rng = new Random(((Level) getWorld()).getLevelSeed() + _attempts);
    }
    
    /**
     * Act - do whatever the phone_cabin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * garante que as cabines não aparecem umas em cima das outras ou tapem outros objetos mais importantes na geração aleatória.
     */
    public void act()
    {
        super.act();
        
        if (isTouching(trash_bin.class) || isTouching(phone_cabin.class) || isTouching(street_lamp.class)) {
            Level level = (Level) getWorld();
            
            int randomX = rng.nextInt(3400) + 300;
            
            phone_cabin newCabin = new phone_cabin(this.HasClue());
            newCabin._attempts = this._attempts + 1;
            
            level.addScrollObject(newCabin, randomX, 720);
            replaceEntitiesAndPointsOnTop();
            
            level.removeObject(this);
            return;
        }
        
        OnSearch();
    }
    
    /**
     * OnSearch - método que trata da interação de procurar por clues.
     */
    public void OnSearch() {
        World world = getWorld();
        List<Player> players = world.getObjects(Player.class);
        
        for (Player player : players) { 
            if (player.GetHiddenSpot() == this && player.PressedSearchKey()) {     
                if (HasClue()) {
                    SetHasClue(false);
                    ((level1) world).clueController.AddCollectedClue();
                    ((Level) world).audioController.PlaySFX(((Level) world).audioController.GetFoundClueSfx());
                }
            }
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
                    
                    player.setLocation(cX, cY + 10);
                    player.setLocationScroll(getExactX());
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
