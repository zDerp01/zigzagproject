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
    
    private int patrolCounter, patrolTime = 1400, directionFactor = 1, visionRange = 500;
    private float speed = 1.2f;
    private State estadoAtual = State.PATROL;
    private Player currentTarget;
    
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
    
    private Player findClosestTarget(int range) {
        List<Player> players = getObjectsInRange(range, Player.class);
  
        if (!players.isEmpty()) {
            Player closestPlayer = null;
            double nearestDistance = Double.MAX_VALUE;
            
            for (Player p : players) {
                int distance = Math.abs(p.getX() - getX());
                
                if (distance < nearestDistance && !p.IsHidden()) {
                    nearestDistance = distance;
                    closestPlayer = p;
                }
            }
            
            return closestPlayer;
        }
        
        return null;
    }
    
    private boolean canSeePlayer(Player player) {
        if (player.IsHidden()) return false;
        
        boolean playerOnLeft = false;
        playerOnLeft = player.getX() < getX() ? true : false;
        
        if (playerOnLeft && directionFactor < 0) {
            return true;
        }
        else if (!playerOnLeft && directionFactor > 0) {
            return true;
        }
        else if (Math.abs(player.getX() - getX()) < 150){
            return true;
        }
        
        return false;
    }
    
    public void checkState() {
        Player target = findClosestTarget(visionRange);
        
        if (estadoAtual == State.PATROL && target != null && canSeePlayer(target)) {
            currentTarget = target;
            estadoAtual = State.AGGRESSIVE;
        }
        else if (estadoAtual == State.AGGRESSIVE && currentTarget != null) {
            if (currentTarget.IsHidden()) {
                int distanceToTarget = Math.abs(currentTarget.getX() - getX());
                
                if (distanceToTarget > visionRange / 2) {
                    currentTarget = null;
                    estadoAtual = State.PATROL;
                }
            }
        }
    }
    
    public void movePatrol() {
        patrolCounter--;
        if (patrolCounter <= 0) {
            directionFactor = -directionFactor;
            patrolCounter = patrolTime;
        }
        else if (patrolCounter > 300 || !isAtEdge()){
            if (directionFactor < 0) setImage(GetImageLeft());
            else setImage(GetImageRight());
            setLocation(getX() + (int) (directionFactor * speed), getY());
        }
    }
    
    public void moveAggressive() {
        if (currentTarget != null) {
            if (currentTarget.getX() != getX()) {
                directionFactor = currentTarget.getX() < getX() ? -1 : 1;
            }
            else {
                directionFactor = 0;
            }
        }
    
        if (directionFactor < 0) setImage(GetImageLeft());
        else setImage(GetImageRight());
        
        setLocation(getX() + (int) (directionFactor * speed * 2), getY());
    }
}
