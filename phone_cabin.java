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
        int width = (int) (image.getWidth() * 3f);
        int height = (int) (image.getHeight() * 3f);
        
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
                
                StartPoint sp = world.getObjects(StartPoint.class).get(0);
                int spX = sp.getX();
                int spY = sp.getY();
                
                world.removeObject(sp);   
                world.addObject(sp, spX, spY);
            }
        }
    }
}
