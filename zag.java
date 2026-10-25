import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zag extends Player
{
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, jumpAnimLeft, throwAnimLeft, searchAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, jumpAnimRight, throwAnimRight, searchAnimRight;
    private boolean _spawnedStone;
    
    /**
     * zag - construtor que atribui animações.
     */
    public zag() {
        idleAnimLeft = loadAnimation("sprites/zag/idle/idle", 2, 3);
        idleAnimRight = flipAnimation(idleAnimLeft);
        
        walkAnimLeft = loadAnimation("sprites/zag/walk/walk", 4, 3);
        walkAnimRight = flipAnimation(walkAnimLeft);
        
        jumpAnimLeft = loadAnimation("sprites/zag/jump/jump", 4, 3);
        jumpAnimRight = flipAnimation(jumpAnimLeft);
        
        throwAnimLeft = loadAnimation("sprites/zag/throw/throw", 3, 3);
        throwAnimRight = flipAnimation(throwAnimLeft);
        
        searchAnimLeft = loadAnimation("sprites/zag/search/search", 2, 3);
        searchAnimRight = flipAnimation(searchAnimLeft);
    }
    
    /**
     * Act - do whatever the zag wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * verifica se a tecla de procurar foi pressionada.
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
    
    /**
     * move - método que trata da lógica da movimentação horizontal, com ajustes no speed por causa da lógica
     * da câmera.
     */
    @Override
    public void move() {
        Level level = (Level) getWorld();
        
        if (Greenfoot.isKeyDown("left")) {
            if (getExactX() > 100) {
                exactX -= 3 * GetSpeed();
                
                SetMoving(true);
                SetFacingRight(false);
            }
        }
        else if (Greenfoot.isKeyDown("right")) {
            if (getExactX() < 4260) {
                exactX += 3 * GetSpeed();
            
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
        if (((isTouching(Ground.class) || isOnPlatformTop()) && Greenfoot.isKeyDown("up")) || forced) {
            SetGravity(GetJumpStrength());
            
            ((Level) getWorld()).audioController.PlaySFX(((Level) getWorld()).audioController.GetJumpSfx());
            
            animSpeed = 13;
            animFrame = 0;
            animTimer = 0;
            SetIsJumping(true);
        }
    }
    
    /**
     * forceJump - método que força um salto.
     */
    public void forceJump() {
        SetGravity(GetJumpStrength());
            
        ((Level) getWorld()).audioController.PlaySFX(((Level) getWorld()).audioController.GetJumpSfx());
            
        animSpeed = 11;
        animFrame = 0;
        animTimer = 0;
        SetIsJumping(true);
    }
    
    /**
     * useHability - método que trata da lógica da habilidade.
     */
    @Override
    public void useHability() {
        if (((isTouching(Ground.class) || isOnPlatformTop()) || IsHidden()) && Greenfoot.isKeyDown("l") && !UsingHability()) { // permite usar a habilidade mesmo estando escondido
            habilityTimer = habilityTotalTime;
            
            // a pedra é instanciada num animation event, no animate()
            _spawnedStone = false;
            
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
        
        if (spot != null && "k".equals(level.getPressedKey())) {
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
     * e neste caso ANIMATION EVENTS.
     */
    @Override
    protected void animate() {
        Level level = (Level) getWorld(); 
        
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
            GreenfootImage[] currentAnim = IsFacingRight() ? throwAnimRight : throwAnimLeft;
            if (animFrame == 1 && !_spawnedStone) {
                // ANIMATION EVENT -------------------------
                _spawnedStone = true;
                level.addScrollObject(new stone(IsFacingRight(), this), getExactX()+10, getY()-5);
            }
            
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
