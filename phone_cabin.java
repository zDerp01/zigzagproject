import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class phone_cabin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class phone_cabin extends Spots
{
    private boolean _hasClue;
    
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
            
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            level.addScrollObject(new phone_cabin(), randomX, 720);
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
