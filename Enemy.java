import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends Entity
{
    private int _gravity;
    private boolean _isDead;
    
    /**
     * GetGravity - retorna o valor da gravidade.
     */
    public int GetGravity() {
        return _gravity;
    }
    
    /**
     * SetGravity - modifica o valor da gravidade.
     */
    public void SetGravity(int num) {
        _gravity = num;
    }
    
    /**
     * IsDead - retorna se está morto.
     */
    public boolean IsDead() {
        return _isDead;
    }
    
    /**
     * IsDead - modifica se está morto.
     */
    public void SetDeadState(boolean state) {
        _isDead = state;
    }
    
    // --------------------------------------------
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        fall();
    }
    
    /**
     * fall - puxa os inimigos para baixo se estes não estiverem no chão.
     */
    public void fall() {
        if (!isTouching(Ground.class) && !isTouching(platform_ground.class)) {
            _gravity++;
        }
        else if (_gravity > 0) {
            _gravity = 0;
        }
        setLocation(getX(), getY() + _gravity);
    }
}
