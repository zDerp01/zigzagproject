import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class car extends Spots
{
    
    public car() {
        GreenfootImage image = new GreenfootImage("sprites/cars/car_" + Greenfoot.getRandomNumber(4) + ".png");
        int width = (int) (image.getWidth() * 3.5f);
        int height = (int) (image.getHeight() * 3.5f);
        
        image.scale(width, height);
        setImage(image);
    }
    
    protected void addedToWorld(World world) {
        if (isTouching(car.class) || isTouching(trash_bin.class) || isTouching(phone_cabin.class)) {
            world.removeObject(this);
        }
    }
    
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
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
                    
                    player.setLocation(cX + 70, cY - 30);
                    player.SetHiddenSpot(this);
                    
                    this.SetHiddenPlayer(player);
                    player.SetIsHidden(true);
                }
                
                // recolocar entidades para aparecerem por cima
                for (Entity entity : entities) {
                    if ((entity instanceof Player p && !p.IsHidden()) || entity instanceof Enemy) {
                        int eX = entity.getX();
                        int eY = entity.getY();
                    
                        world.removeObject(entity);   
                        world.addObject(entity, eX, eY);
                    }
                }
                
                // recolocar start point para aparecer por cima
                StartPoint sp = world.getObjects(StartPoint.class).get(0);
                int spX = sp.getX();
                int spY = sp.getY();
                
                world.removeObject(sp);   
                world.addObject(sp, spX, spY);
            }
        }
    }
}
