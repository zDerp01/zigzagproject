import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class capanga here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class capanga extends Enemy
{
    
    private enum State {
        PATROL,
        AGGRESSIVE
    }
    
    private int patrolCounter, patrolTime = 1000, directionFactor = 1, visionRange = 500;
    private float speed = 1.2f;
    private State estadoAtual = State.PATROL;
    
    /**
     * Act - do whatever the capanga wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        switch (estadoAtual) {
            case PATROL:
                checkState();
                movePatrol();
                break;
            case AGGRESSIVE:
                checkState();
                moveAggressive();
                break;
        }
    }
    
    private Player findTarget(boolean hasRange, int range) {
        List<Player> players;
        
        if (hasRange) players = getObjectsInRange(range, Player.class);
        else players = getWorld().getObjects(Player.class);
  
        if (!players.isEmpty()) {
            Player closestPlayer = null;
            double nearestDistance = Double.MAX_VALUE;
            
            for (Player p : players) {
                int distance = p.getX() - getX();
                
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    closestPlayer = p;
                }
            }
            
            return closestPlayer;
        }
        
        return null;
    }
    
    private boolean canSeePlayer(Player player) {
        boolean playerOnLeft = false;
        if (player != null) playerOnLeft = player.getX() < getX() ? true : false;
        else return false;
        
        if (playerOnLeft && directionFactor < 0) {
            return true;
        }
        else if (!playerOnLeft && directionFactor > 0) {
            return true;
        }
        else if (Math.abs(player.getX() - getX()) < visionRange/3){
            return true;
        }
        
        return false;
    }
    
    public void checkState() {
        if (canSeePlayer(findTarget(true, visionRange))) {
            estadoAtual = State.AGGRESSIVE;
        }
    }
    
    public void movePatrol() {
        patrolCounter--;
        if (patrolCounter <= 0) {
            directionFactor = -directionFactor;
            patrolCounter = patrolTime;
        }
        else if (patrolCounter > 500){
            if (directionFactor < 0) setImage(GetImageLeft());
            else setImage(GetImageRight());
            setLocation(getX() + (int) (directionFactor * speed), getY());
        }
    }
    
    public void moveAggressive() {
        int direction = 0;
        Player target = findTarget(false, 0);
        if (target != null) direction = target.getX() < getX() ? -1 : 1;
        
        if (direction < 0) setImage(GetImageLeft());
        else setImage(GetImageRight());
        setLocation(getX() + (int) (direction * speed * 2), getY());
    }
}
