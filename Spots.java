import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spots here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Spots extends ScrollableSprite
{
    private Player _hiddenPlayer;
    
    /**
     * GetHiddenPlayer - retorna o jogador que se encontra escondido neste spot.
     */
    public Player GetHiddenPlayer() {
        return _hiddenPlayer;
    }
    
    /**
     * SetHiddenPlayer - modifica o jogador que se encontra escondido neste spot.
     */
    public void SetHiddenPlayer(Player player) {
        _hiddenPlayer = player;
    }

    /**
     * Act - do whatever the Spots wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        OnInteract();
    }
    
    /**
     * OnInteract - método que trata da lógica da interação do jogador com este spot.
     */
    public abstract void OnInteract();
}
