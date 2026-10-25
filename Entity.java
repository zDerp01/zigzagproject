import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends ScrollableSprite
{
    private boolean _isMoving, _isFacingRight;
    protected int animTimer, animFrame, animSpeed = 20;
    
    /**
     * Entity - construtor que redimensiona o sprite para fins estéticos.
     */
    public Entity() {
        GreenfootImage img = getImage();
        img.scale(img.getWidth() * 3, img.getHeight() * 3);
        setImage(img); // para ficar grande no editor
    }
    
    /**
     * IsMoving - retorna se a entidade se encontra em movimento.
     */
    public boolean IsMoving() {
        return _isMoving;
    }
    
    /**
     * IsFacingRight - retorna se a entidade está a olhar para a direita.
     */
    public boolean IsFacingRight() {
        return _isFacingRight;
    }
    
    /**
     * SetMoving - modifica se a entidade se encontra em movimento.
     */
    public void SetMoving(boolean state) {
        _isMoving = state;
    }
    
    /**
     * SetFacingRight - modifica se a entidade está a olhar para a direita.
     */
    public void SetFacingRight(boolean state) {
        _isFacingRight = state;
    }
    
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        animate();
    }
    
    /**
     * animate - método com toda a lógica das animações, condições das mesmas, velocidade de todos os frames,
     * e em alguns casos ANIMATION EVENTS.
     */
    protected abstract void animate();
}
