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
    
    /**
     * zig - construtor que atribui animações.
     */
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
     * * 
     * verifica se a tecla de procurar foi pressionada.
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
    
    /**
     * move - método que trata da lógica da movimentação horizontal, com ajustes no speed por causa da lógica da câmera.
     * trata também da movimentação da própria câmera, movendo o X da mesma e movendo o mundo também.
     */
    @Override
    public void move() {
        float moveFactor;
        Level level = (Level) getWorld();
        
        if (Greenfoot.isKeyDown("a")) {
            moveFactor = -1;
            
            if (level.getCameraX() > 0) {
                level.setCameraX(level.getCameraX() - 2);
                level.moveWorld();
            }
            else {
                moveFactor = moveFactor * 2; // x2 para manter o speed quando a câmera não tem movimento
            }
            
            SetMoving(true);
            SetFacingRight(false);
        }
        else if (Greenfoot.isKeyDown("d")) {
            moveFactor = 1;
            
            if (level.getCameraX() < level.getCameraLimit()) {
                level.setCameraX(level.getCameraX() + 2);
                level.moveWorld();
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
        
        setLocation(getX() + (int) (moveFactor * GetSpeed()), getY());
    }
    
    /**
     * jump - método que trata da lógica do salto.
     */
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
    
    /**
     * useHability - método que trata da lógica da habilidade.
     */
    @Override
    public void useHability() {
        if (isTouching(Ground.class) && Greenfoot.isKeyDown("f") && !UsingHability() && !IsHidden()) {
            habilityTimer = habilityTotalTime;
            
            World world = getWorld();
            
            List<Enemy> enemies = world.getObjects(Enemy.class);
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getX() - getX()) <= 130) {
                    enemy.SetDeadState(true);   
                }
            }
            
            animSpeed = 11;
            animFrame = 0;
            animTimer = 0;
            SetUsingHability(true);
        }
    }
    
    /**
     * hidesInSpot - método que trata da interação com um spot para se esconder, e retorna-o.
     */
    @Override
    public Spots hidesInSpot() {
        Spots spot = (Spots) getOneIntersectingObject(Spots.class);
        Level level = (Level) getWorld();
        
        if (spot != null && "e".equals(level.getPressedKey())) {
            return spot;
        }
        
        return null;
    }
    
    /**
     * searchedContainer - método que trata da interação com um container.
     */
    @Override
    public Containers searchedContainer() {
        Containers container = (Containers) getOneIntersectingObject(Containers.class);
        Level level = (Level) getWorld();
        
        if (container != null && PressedSearchKey()) {
            return container;
        }
        
        return null;
    }
    
    /**
     * animate - método com toda a lógica das animações, condições das mesmas, velocidade de todos os frames,
     * e em alguns casos ANIMATION EVENTS.
     */
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
