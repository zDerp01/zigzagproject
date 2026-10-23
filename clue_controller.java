import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ClueController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class clue_controller extends Controllers
{
    private int _clues, _totalClues, _collectedClues;
    
    public clue_controller() {
        _clues = Greenfoot.getRandomNumber(3) + 1;
        _totalClues = _clues;
    }
    
    public int GetCollectedClues() {
        return _collectedClues;
    }
    
    public void AddCollectedClue() {
        _collectedClues++;
    }
    
    protected void addedToWorld(World world) {
        List<Actor> searchableObjects = new java.util.ArrayList<>();
        searchableObjects.addAll(world.getObjects(Containers.class));
        searchableObjects.addAll(world.getObjects(phone_cabin.class));    
        
        while (_clues > 0) {
            for (Actor obj : searchableObjects) { 
                if (obj instanceof Containers) {
                    Containers ct = (Containers) obj;
                    if (!ct.HasClue() && Greenfoot.getRandomNumber(10) == 0) {
                        ct.SetHasClue(true);
                        _clues--;
                    }
                } 
                else if (obj instanceof phone_cabin) {
                    phone_cabin pc = (phone_cabin) obj;
                    if (!pc.HasClue() && Greenfoot.getRandomNumber(20) == 0) {
                        pc.SetHasClue(true);
                        _clues--;
                    }
                }
            }   
        }
    }
    
    /**
     * Act - do whatever the ClueController wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(new GreenfootImage("Clues: " + _collectedClues + "/" + _totalClues, 28, Color.GRAY, null));
    }
    
    
}
