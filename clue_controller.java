import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Collections;

/**
 * Write a description of class ClueController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class clue_controller extends Controllers
{
    private int _clues, _totalClues, _collectedClues, _totalCollectedClues, _assignedClues;
    
    /**
     * clue_controller - construtor que randomiza o número total de clues do nível.
     */
    public clue_controller() {
        _clues = Greenfoot.getRandomNumber(3) + 2;
    }
    
    /**
     * GetTotalClues - retorna as clues totais.
     */
    public int GetTotalClues() {
        return _totalClues;
    }
    
    /**
     * GetCollectedClues - retorna as clues coletadas pelos jogadores.
     */
    public int GetCollectedClues() {
        return _collectedClues;
    }
    
    /**
     * GetTotalCollectedClues - retorna todas as clues coletadas pelos jogadores.
     */
    public int GetTotalCollectedClues() {
        return _totalCollectedClues;
    }
    
    /**
     * GetClues - retorna as clues locais.
     */
    public int GetClues() {
        return _clues;
    }
    
    /**
     * SetTotalClues - modifica as clues totais.
     */
    public void SetTotalClues(int n) {
        _totalClues = n;
    }
    
    /**
     * SetCollectedClues - modifica as clues coletadas pelos jogadores.
     */
    public void SetCollectedClues(int n) {
        _collectedClues = n;
    }
    
    /**
     * SetTotalCollectedClues - modifica todas as clues coletadas pelos jogadores.
     */
    public void SetTotalCollectedClues(int n) {
        _totalCollectedClues = n;
    }
    
    /**
     * AddCollectedClue - adiciona uma clue às clues coletadas pelos jogadores.
     */
    public void AddCollectedClue() {
        _collectedClues++;
    }
    
    /**
     * RemoveMissingClue - remove uma clue das assignedClues, para ter mais uma para distribuir (exemplo uso: contentores não conseguiram
     * spawnar, a clue não consegue ser usada.
     */
    public void RemoveMissingClue() {
        _assignedClues--;
    }
    
    /**
     * addedToWorld - método que usa o método placeClues() quando é adicionado no mundo.
     */
    @Override
    protected void addedToWorld(World world) {
        placeClues();
    }
    
    /**
     * placeClues - trata de distribuir clues por todos os containers, cabines telefónicas e contentores.
     */
    public void placeClues() {
        World world = getWorld();
        
        List<Actor> searchableObjects = new java.util.ArrayList<>();
        searchableObjects.addAll(world.getObjects(Containers.class));
        searchableObjects.addAll(world.getObjects(phone_cabin.class));    
        searchableObjects.addAll(world.getObjects(contentor.class)); 
        
        Collections.shuffle(searchableObjects);
        
        for (Actor obj : searchableObjects) {
            if (_assignedClues >= _clues) break;
            
            if (obj instanceof Containers ct) {
                if (!ct.HasClue()) {
                    ct.SetHasClue(true);
                    _assignedClues++;
                    System.out.println("clue place on container");
                }
            } 
            else if (obj instanceof phone_cabin pc) {
                if (!pc.HasClue()) {
                    pc.SetHasClue(true);
                    _assignedClues++;
                    System.out.println("clue place on phonecabin");
                }
            }
            else if (obj instanceof contentor c) {
                if (!c.HasClue()) {
                    c.SetHasClue(true);
                    _assignedClues++;
                    System.out.println("clue place on contentor");
                }
            }
        }
    }
    
    /**
     * Act - do whatever the clue_controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(new GreenfootImage("Clues: " + (_collectedClues+_totalCollectedClues) + "/" + (_clues +_totalClues), 28, Color.GRAY, null));
    }
    
    
}
