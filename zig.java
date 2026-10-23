import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class zig here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zig extends Player
{
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, jumpAnimLeft, stabAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, jumpAnimRight, stabAnimRight;
    
    public zig() {     
        idleAnimLeft = loadAnimation("sprites/zig/idle/idle", 2, 3);
        idleAnimRight = flipAnimation(idleAnimLeft);
        
        walkAnimLeft = loadAnimation("sprites/zig/walk/walk", 4, 3);
        walkAnimRight = flipAnimation(walkAnimLeft);
        
        jumpAnimLeft = loadAnimation("sprites/zig/jump/jump", 4, 3);
        jumpAnimRight = flipAnimation(jumpAnimLeft);
        
        stabAnimLeft = loadAnimation("sprites/zig/stab/stab", 5, 3);
        stabAnimRight = flipAnimation(stabAnimLeft);
    }
    
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
            
            if (level.getCameraX() > 0) {
                level.setCameraX(level.getCameraX() - 2);
                level.moveWorld(2);
            }
            else {
                moveFactor = moveFactor * 2; // x2 para manter o speed quando a câmera não tem movimento
            }
            
            SetMoving(true);
            SetFacingRight(false);
        }
        else if (Greenfoot.isKeyDown("d")) {
            moveFactor = GetSpeed();
            
            if (level.getCameraX() < level.getCameraLimit()) {
                level.setCameraX(level.getCameraX() + 2);
                level.moveWorld(2);
            }
            else {
                moveFactor = moveFactor * 2;
            }
            
            SetMoving(true);
            SetFacingRight(true);
        }
        else {
            moveFactor = 0;
            SetMoving(false);
        }
        
        setLocation(getX() + (int) moveFactor, getY());
    }
    
    @Override
    public void jump() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("w")) {
            SetGravity(GetJumpStrength());
            
            animSpeed = 11;
            animFrame = 0;
            animTimer = 0;
            SetIsJumping(true);
        }
    }
    
    @Override
    public void useHability() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("f") && !UsingHability()) {
            habilityTimer = habilityTotalTime;
            
            World world = getWorld();
            
            List<Enemy> enemies = world.getObjects(Enemy.class);
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getX() - getX()) <= 150) {
                    enemy.SetDeadState(true);   
                }
            }
            
            animSpeed = 11;
            animFrame = 0;
            animTimer = 0;
            SetUsingHability(true);
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
    
    @Override
    protected void animate() {
        animTimer++;
        if (animTimer >= animSpeed) {
            animTimer = 0;
            animFrame++;
        }
        
        if (IsJumping()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? jumpAnimRight : jumpAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = currentAnim.length - 1;
            setImage(currentAnim[animFrame]);
        }
        else if (UsingHability()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? stabAnimRight : stabAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = currentAnim.length - 1;
            setImage(currentAnim[animFrame]);
        }
        else if (IsMoving()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? walkAnimRight : walkAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = 0; // looping
            setImage(currentAnim[animFrame]);
        } 
        else {
            GreenfootImage[] currentAnim = IsFacingRight() ? idleAnimRight : idleAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = 0; // looping
            setImage(currentAnim[animFrame]);
        }
    }
}
