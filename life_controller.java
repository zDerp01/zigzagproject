import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ClueController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class life_controller extends Controllers
{
    private int _lives = 3;
    
    /**
     * GetLives - retorna as vidas restantes.
     */
    public int GetLives() {
        return _lives;
    }
    
    /**
     * SetLives - modifica as vidas restantes.
     */
    public void SetLives(int n) {
        _lives = n;
    }
    
    /**
     * DecreaseLives - reduz 1 vida e trata do respawn dos jogadores e do game over.
     */
    public void DecreaseLives() {
        _lives--;
        
        if (_lives > 0) {
            level1 reloadedLevel = new level1(((level1) getWorld()).GetLevelSeed());
            reloadedLevel.lifeController.SetLives(_lives);
            
            Greenfoot.setWorld(reloadedLevel);
        }
        else {
            Greenfoot.setWorld(new Menu());
        }
    }
    
    /**
     * Act - do whatever the life_controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(new GreenfootImage("Vidas: " + _lives, 48, Color.WHITE, null));
    }
    
    
}
