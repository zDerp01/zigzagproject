import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ClueController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class score_controller extends Controllers
{
    private int _score = 0;
    
    /**
     * GetScore - retorna o score.
     */
    public int GetScore() {
        return _score;
    }
    
    /**
     * SetScore - modifica o score.
     */
    public void SetScore(int n) {
        _score = n;
    }
    
    /**
     * AddRandomScore - adiciona uma pontuação entre 400 e 600.
     */
    public void AddRandomScore() {
        _score += Greenfoot.getRandomNumber(201) + 400;
    }
    
    /**
     * RemoveRandomScore - remove uma pontuação entre 200 e 400.
     */
    public void RemoveRandomScore() {
        if (_score >= 200) _score -= Greenfoot.getRandomNumber(201) + 200;
    }
    
    /**
     * Act - do whatever the points_controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_score != 0) setImage(new GreenfootImage("Score: " + _score, 48, Color.WHITE, null));
        else setImage(new GreenfootImage("Score: 0000", 48, Color.WHITE, null));
    }
    
    
}
