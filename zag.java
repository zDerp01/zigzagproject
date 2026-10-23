import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zag extends Player
{
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, jumpAnimLeft, throwAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, jumpAnimRight, throwAnimRight;
    
    public zag() {
        idleAnimLeft = loadAnimation("sprites/zag/idle/idle", 2, 3);
        idleAnimRight = flipAnimation(idleAnimLeft);
        
        walkAnimLeft = loadAnimation("sprites/zag/walk/walk", 4, 3);
        walkAnimRight = flipAnimation(walkAnimLeft);
        
        jumpAnimLeft = loadAnimation("sprites/zag/jump/jump", 4, 3);
        jumpAnimRight = flipAnimation(jumpAnimLeft);
        
        throwAnimLeft = loadAnimation("sprites/zag/throw/throw", 3, 3);
        throwAnimRight = flipAnimation(throwAnimLeft);
    }
    
    /**
     * Act - do whatever the zag wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act()
    {
        super.act();
        
        Level level = (Level) getWorld();
        
        if ("j".equals(level.getPressedKey())) {
            SetPressedSearchKey(true);
        }
        else {
            SetPressedSearchKey(false);
        }
    }
    
    @Override
    public void move() {
        Level level = (Level) getWorld();
        
        if (Greenfoot.isKeyDown("left")) {
            exactX -= 2 * GetSpeed(); // x2 para igualar ao speed do zig (já que tem resistência da câmera)
            
            SetMoving(true);
            SetFacingRight(false);
        }
        else if (Greenfoot.isKeyDown("right")) {
            exactX += 2 * GetSpeed();
            
            SetMoving(true);
            SetFacingRight(true);
        }
        else {
            SetMoving(false);
        }
        
        update(level.getCameraX());
    }
    
    @Override
    public void jump() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("up")) {
            SetGravity(GetJumpStrength());
            
            animSpeed = 13;
            animFrame = 0;
            animTimer = 0;
            SetIsJumping(true);
        }
    }
    
    @Override
    public void useHability() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("l") && !UsingHability()) {
            habilityTimer = habilityTotalTime;
            
            animFrame = 0;
            animTimer = 0;
            SetUsingHability(true);
        }
    }
    
    @Override
    public Spots hidesInSpot() {
        Spots spot = (Spots) getOneIntersectingObject(Spots.class);
        Level level = (Level) getWorld();
        
        if (spot != null && "k".equals(level.getPressedKey())) {
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
            GreenfootImage[] currentAnim = IsFacingRight() ? throwAnimRight : throwAnimLeft;
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
