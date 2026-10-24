import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Interactables here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Containers extends ScrollableSprite
{
    private boolean _hasClue;
    
    /**
     * HasClue - retorna se o container tem ou não uma clue.
     */
    public boolean HasClue() {
        return _hasClue;
    }
    
    /**
     * SetHasClue - modifica se o container tem ou não uma clue.
     */
    public void SetHasClue(boolean state) {
        _hasClue = state;
    }
    
    /**
     * Act - do whatever the Containers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        OnInteract();
    }
    
    /**
     * OnInteract - Método que trata do resultado de uma interação com este objeto.
     */
    public abstract void OnInteract();
}
