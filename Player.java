import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Entity
{
    protected int habilityTimer, habilityTotalTime = 60, searchTimer, searchTotalTime = 40;
    
    private int _gravity, _jumpStrength = -13;
    private float _speed = 1f;
    private boolean _isHidden = false, _searchKeyPress, _isJumping, _usingHability, _isRestricted, _isSearching;
    private Spots _hiddenSpot;
    private Containers _search;
    
    /**
     * GetGravity - retorna o valor da gravidade.
     */
    public int GetGravity() {
        return _gravity;
    }
    
    /**
     * GetSpeed - retorna o valor da velocidade.
     */
    public float GetSpeed() {
        return _speed;
    }
    
    /**
     * GetJumpStrength - retorna o valor da força do salto.
     */
    public int GetJumpStrength() {
        return _jumpStrength;
    }
    
    /**
     * IsHidden - retorna se está escondido.
     */
    public boolean IsHidden() {
        return _isHidden;
    }
    
    /**
     * IsJumping - retorna se está a saltar.
     */
    public boolean IsJumping() {
        return _isJumping;
    }
    
    /**
     * IsSearching - retorna se está a procurar.
     */
    public boolean IsSearching() {
        return _isSearching;
    }
    
    /**
     * UsingHability - retorna se está a usar a sua habilidade.
     */
    public boolean UsingHability() {
        return _usingHability;
    }
    
    /**
     * GetHiddenSpot - retorna o spot em que se esconde atualmente.
     */
    public Spots GetHiddenSpot() {
        return _hiddenSpot;
    }
    
    /**
     * GetSearch - retorna o container procurado.
     */
    public Containers GetSearch() {
        return _search;
    }
    
    /**
     * PressedSearchKey - retorna se a tecla de procurar foi pressionada.
     */
    public boolean PressedSearchKey() {
        return _searchKeyPress;
    }
    
    /**
     * SetGravity - modifica o valor da gravidade.
     */
    public void SetGravity(int num) {
        _gravity = num;
    }
    
    /**
     * SetSpeed - modifica o valor da velocidade.
     */
    public void SetSpeed(float num) {
        _speed = num;
    }
    
    /**
     * SetJumpStrength - modifica o valor da força do salto.
     */
    public void SetJumpStrength(int num) {
        _jumpStrength = num;
    }
    
    /**
     * SetIsHidden - modifica se está escondido.
     */
    public void SetIsHidden(boolean state) {
        _isHidden = state;
    }
    
    /**
     * SetIsJumping - modifica se está a saltar.
     */
    public void SetIsJumping(boolean state) {
        _isJumping = state;
    }
    
    /**
     * SetUsingHability - modifica se está a usar a sua habilidade.
     */
    public void SetUsingHability(boolean state) {
        _usingHability = state;
    }
    
    /**
     * SetIsSearching - modifica se está a usar a procurar.
     */
    public void SetIsSearching(boolean state) {
        _isSearching = state;
    }
    
    /**
     * SetHiddenSpot - modifica o spot em que se esconde atualmente.
     */
    public void SetHiddenSpot(Spots spot) {
        _hiddenSpot = spot;
    }
    
    /**
     * SetPressedSearchKey - modifica se a tecla de procurar foi pressionada.
     */
    public void SetPressedSearchKey(boolean state) {
        _searchKeyPress = state;
    }
    
    /**
     * SetIsRestricted - modifica se está restrigido.
     */
    public void SetIsRestricted(boolean state) {
        _isRestricted = state;
    }
    
    /**
     * SetSearch - modifica container procurado.
     */
    public void SetSearch(Containers container) {
        _search = container;
    }
    
    // --------------------------------------------
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * chama todos os métodos do jogador, trata de algumas flags e do cooldown da sua habilidade.
     */
    public void act()
    {
        super.act();
        if (!_isRestricted) { // restrigido = congelamento total
            useHability();
            searchContainer();
            
            if (!_isHidden) {
                if (!_usingHability) {
                    move();
                    jump(false);
                }
                
                fall();   
            }
        }
        
        if (habilityTimer > 0) habilityTimer--;
        else if (_usingHability && habilityTimer <= 0) {
            _usingHability = false;
            resetAnimParams();   
        }
        
        if (searchTimer > 0) searchTimer--;
        else if (_isSearching && searchTimer <= 0) {
            _isSearching = false;
            _isRestricted = false;
            _search = null;
            resetAnimParams();   
        }
        
        if ((isTouching(Ground.class) || !isTouching(platform_ground.class)) && _gravity > 0) { // para detetar o fim do salto do player
            if (_isJumping) {
                _isJumping = false;
                resetAnimParams();   
            }
        }
        
        if (IsMoving() && !_isJumping) {
            ((Level) getWorld()).audioController.PlaySFX(((Level) getWorld()).audioController.GetStepSfx());
        }
    }
    
    /**
     * hasFinishedSearch - retorna true exatamente no frame em que o procurar termina.
     */
    public boolean hasFinishedSearch() {
        return searchTimer == 1;
    }
    
    /**
     * move - método que trata da lógica da movimentação horizontal.
     */
    public abstract void move();
    
    /**
     * jump - método que trata da lógica do salto.
     */
    public abstract void jump(boolean forced);
    
    /**
     * useHability - método que trata da lógica da habilidade.
     */
    public abstract void useHability();
    
    /**
     * hidesInSpot - método que trata da interação com um spot para se esconder, e retorna-o.
     */
    public abstract Spots hidesInSpot();
    
    /**
     * searchedContainer - método que trata da interação com um container.
     */
    public abstract void searchContainer();
    
    /**
     * isOnPlatformTop - verifica se o jogador está em cima da plataforma.
     */
    protected boolean isOnPlatformTop() {
        platform_ground platform = (platform_ground) getOneIntersectingObject(platform_ground.class);
        if (platform != null) {
            return getY() <= platform.getY() - (platform.getImage().getHeight() / 2);
        }
        return false;
    }
    
    /**
     * fall - puxa os jogadores para baixo se estes não estiverem no chão.
     */
    public void fall() {
        
        if (_gravity >= 30) ((Level) getWorld()).lifeController.DecreaseLives(2);
        
        if (!isTouching(Ground.class) && !isOnPlatformTop()) {
            _gravity++;
        }
        else if (_gravity > 0) {
            _gravity = 0;
        }

        setLocation(getX(), getY() + _gravity);
    }
    
    /**
     * resetAnimParams - reseta os parâmetros das animações (frame atual, timer e velocidade).
     */
    protected void resetAnimParams() {
        this.animFrame = 0;
        this.animTimer = 0;
        this.animSpeed = 20;
    }
}