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
    
    public boolean HasClue() {
        return _hasClue;
    }
    
    public void SetHasClue(boolean state) {
        _hasClue = state;
    }
    
    public phone_cabin() {
        GreenfootImage image = getImage();
        int width = (int) (image.getWidth() * 1.5f);
        int height = (int) (image.getHeight() * 1.5f);
        
        image.scale(width, height);
        setImage(image);
    }
    
    /**
     * Act - do whatever the phone_cabin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        OnSearch();
    }
    
    public void OnSearch() {
        List<Player> players = getWorld().getObjects(Player.class);
        
        for (Player player : players) { 
            if (player.GetHiddenSpot() == this && player.PressedSearchKey()) {     
                System.out.println("Cabine tinha clue: " + HasClue());
            }
        }
    }
    
    @Override
    public void OnInteract() {
        List<Player> players = getWorld().getObjects(Player.class);
        List<Entity> entities = getWorld().getObjects(Entity.class);

        for (Player player : players) { 
            if (player.hidesInSpot() == this) {
                World world = getWorld();
                
                if (this.GetHiddenPlayer() == player) {        
                    this.SetHiddenPlayer(null);
                    player.SetIsHidden(false);
                    player.SetHiddenSpot(null);
                }
                else if (this.GetHiddenPlayer() == null) {
                    int cX = this.getX();
                    int cY = this.getY();
                    
                    world.removeObject(this);
                    world.addObject(this, cX, cY);
                    
                    player.setLocation(cX, cY + 10);
                    player.SetHiddenSpot(this);
                    
                    this.SetHiddenPlayer(player);
                    player.SetIsHidden(true);
                }
                
                for (Entity entity : entities) {
                    if ((entity instanceof Player p && !p.IsHidden()) || entity instanceof Enemy) {
                        int eX = entity.getX();
                        int eY = entity.getY();
                    
                        world.removeObject(entity);   
                        world.addObject(entity, eX, eY);
                    }
                }
            }
        }
    }
}
