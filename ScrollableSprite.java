import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Sprite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollableSprite extends Actor
{
    protected int exactX;
    
    public int getExactX() {
        return exactX;
    }
    
    public void setLocationScroll(int x) {
        exactX = x;
    }
    
    public void update(int cameraX) {
        int screenX = exactX - cameraX;
        super.setLocation(screenX, getY());
    }
    
    protected void replaceEntitiesAndPointsOnTop() {
        World world = getWorld();
        
        List<Entity> entities = world.getObjects(Entity.class);
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
    
    protected GreenfootImage[] loadAnimation(String prefix, int framesCount, int scale) {
        GreenfootImage[] images = new GreenfootImage[framesCount];
        for (int i = 0; i < framesCount; i++) {
            images[i] = new GreenfootImage(prefix + i + ".png");
            images[i].scale(images[i].getWidth() * scale, images[i].getHeight() * scale);
        }
        return images;
    }
    
    protected GreenfootImage[] flipAnimation(GreenfootImage[] original) {
        GreenfootImage[] flipped = new GreenfootImage[original.length];
        for (int i = 0; i < original.length; i++) {
            flipped[i] = new GreenfootImage(original[i]);
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
}
