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
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, jumpAnimLeft, stabAnimLeft, searchAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, jumpAnimRight, stabAnimRight, searchAnimRight;
    
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
        
        searchAnimLeft = loadAnimation("sprites/zig/search/search", 2, 3);
        searchAnimRight = flipAnimation(searchAnimLeft);
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
        Level level = (Level) getWorld();
        
        int screenCenterX = level.getCameraX() + (level.getWidth() / 2);
        
        if (Greenfoot.isKeyDown("a")) {
            if (getExactX() > 100) {
                exactX -= 3 * GetSpeed();
            
                if (getExactX() <= screenCenterX && level.getCameraX() > 0) {
                    level.setCameraX(level.getCameraX() - 3);
                    level.moveWorld(); // chama o update() de cada sprite para recalcular a posicao com a nova alteracao do cameraX
                }
                
                SetMoving(true);
                SetFacingRight(false);
            }
        }
        else if (Greenfoot.isKeyDown("d")) {
            if (getExactX() < 4260) {
                exactX += 3 * GetSpeed();
                
                if (getExactX() > screenCenterX && level.getCameraX() < level.getCameraLimit()) {
                    level.setCameraX(level.getCameraX() + 3);
                    level.moveWorld(); // chama o update() de cada sprite para recalcular a posicao com a nova alteracao do cameraX
                }
                
                SetMoving(true);
                SetFacingRight(true);
            }
        }
        else {
            SetMoving(false);
        }
        
        update(level.getCameraX());
    }
    
    /**
     * jump - método que trata da lógica do salto.
     */
    @Override
    public void jump(boolean forced) {
        if (((isTouching(Ground.class) || isOnPlatformTop()) && Greenfoot.isKeyDown("w")) || forced) {
            SetGravity(GetJumpStrength());
            
            ((Level) getWorld()).audioController.PlaySFX(((Level) getWorld()).audioController.GetJumpSfx());
            
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
        if ((isTouching(Ground.class) || isOnPlatformTop()) && Greenfoot.isKeyDown("f") && !UsingHability() && !IsHidden()) {
            habilityTimer = habilityTotalTime;
            
            World world = getWorld();
            
            List<Enemy> enemies = world.getObjects(Enemy.class);
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getX() - getX()) <= 100 && !enemy.IsDead()) {
                    enemy.SetDeadState(true);   
                    ((Level) world).scoreController.AddRandomScore();
                    break;
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
            resetAnimParams();
            SetMoving(false);
            SetIsJumping(false);
            return spot;
        }
        
        return null;
    }
    
    /**
     * searchContainer - método que trata da interação com um container.
     */
    @Override
    public void searchContainer() {
        Containers container = (Containers) getOneIntersectingObject(Containers.class);
        
        if (container != null && PressedSearchKey()) {
            searchTimer = searchTotalTime;
            SetSearch(container);
            SetIsSearching(true);
            SetIsRestricted(true);
        }
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
        
        if (IsSearching()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? searchAnimRight : searchAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = currentAnim.length - 1;
            setImage(currentAnim[animFrame]);
        }
        else if (IsJumping()) {
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
