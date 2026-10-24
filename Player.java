import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Entity
{
    protected int habilityTimer, habilityTotalTime = 60;
    
    private int _gravity, _jumpStrength = -13;
    private float _speed = 1f;
    private boolean _isHidden = false, _searchKeyPress, _isJumping, _usingHability, _isRestricted;
    private Spots _hiddenSpot;
    
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
            
            if (!_isHidden) {
                if (!_usingHability) {
                    move();
                    jump();
                }
                
                fall();   
            }
        }
        
        if (habilityTimer > 0) habilityTimer--;
        else if (_usingHability && habilityTimer <= 0) {
            _usingHability = false;
            resetAnimParams();   
        }
        
        if (isTouching(Ground.class) && _gravity > 0) { // para detetar o fim do salto do player
            if (_isJumping) {
                _isJumping = false;
                resetAnimParams();   
            }
        }
    }
    
    /**
     * move - método que trata da lógica da movimentação horizontal.
     */
    public abstract void move();
    
    /**
     * jump - método que trata da lógica do salto.
     */
    public abstract void jump();
    
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
    public abstract Containers searchedContainer();
    
    /**
     * fall - puxa os jogadores para baixo se estes não estiverem no chão.
     */
    public void fall() {
        if (!isTouching(Ground.class)) {
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