import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zig here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zig extends Player
{
    /**
     * Act - do whatever the zig wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        Level level = (Level) getWorld();
        
        if ("q".equals(level.getPressedKey())) {
            SetPressedSearchKey(true);
        }
        else {
            SetPressedSearchKey(false);
        }
    }
    
    @Override
    public void move() {
        float moveFactor;
        Level level = (Level) getWorld();
        
        if (Greenfoot.isKeyDown("a")) {
            moveFactor = -GetSpeed();
            setImage(GetImageLeft());
            
            if (level.getCameraX() > 0) {
                level.setCameraX(level.getCameraX() - 2);
                level.moveWorld(2);
            }
            else {
                moveFactor = moveFactor * 2; // x2 para manter o speed quando a câmera não tem movimento
            }
        }
        else if (Greenfoot.isKeyDown("d")) {
            moveFactor = GetSpeed();
            setImage(GetImageRight());
            
            if (level.getCameraX() < level.getCameraLimit()) {
                level.setCameraX(level.getCameraX() + 2);
                level.moveWorld(2);
            }
            else {
                moveFactor = moveFactor * 2;
            }
        }
        else {
            moveFactor = 0;
        }
        
        setLocation(getX() + (int) moveFactor, getY());
    }
    
    @Override
    public void jump() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("w")) {
            SetGravity(GetJumpStrength());
        }
    }
    
    @Override
    public Spots hidesInSpot() {
        Spots spot = (Spots) getOneIntersectingObject(Spots.class);
        Level level = (Level) getWorld();
        
        if (spot != null && "e".equals(level.getPressedKey())) {
            return spot;
        }
        
        return null;
    }
    
    @Override
    public Containers searchedContainer() {
        Containers container = (Containers) getOneIntersectingObject(Containers.class);
        Level level = (Level) getWorld();
        
        if (container != null && PressedSearchKey()) {
            return container;
        }
        
        return null;
    }
}
