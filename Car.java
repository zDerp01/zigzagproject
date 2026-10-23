import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class car extends Spots
{
    private int _carType = -1;
    
    @Override
    protected void addedToWorld(World world) {    
        if (_carType == -1) {
            Random rng = new Random(((level1) world).GetLevelSeed() + getX());
            _carType = rng.nextInt(4);
        }
        
        Random rng = new Random(((level1) getWorld()).GetLevelSeed() + getExactX()); // seed baseada na seed do level + posicao
        
        GreenfootImage image = new GreenfootImage("sprites/cars/car_" + _carType + ".png");
        int width = (int) (image.getWidth() * 3.5f);
        int height = (int) (image.getHeight() * 3.5f);
        
        image.scale(width, height);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        if (isTouching(car.class) || isTouching(trash_bin.class) || isTouching(phone_cabin.class)) {
            getWorld().removeObject(this);
            return;
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
                    
                    player.setLocation(cX + 70, cY - 30);
                    player.SetHiddenSpot(this);
                    
                    this.SetHiddenPlayer(player);
                    player.SetIsHidden(true);
                }
                
                // recolocar entidades e points para aparecerem por cima
                replaceEntitiesAndPointsOnTop();
            }
        }
    }
}
