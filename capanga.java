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
        AGGRESSIVE,
        CAUGHT
    }
    
    private int patrolCounter, patrolTime = 1400, directionFactor = 1, visionRange = 500;
    private float speed = 1.2f;
    private State estadoAtual = State.PATROL;
    private Player currentTarget;
    private boolean gameOverScreenActive = false;
    
    /**
     * Act - do whatever the capanga wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        Level level = (Level) getWorld();
        
        switch (estadoAtual) {
            case PATROL:
                checkState();
                movePatrol();
                break;
            case AGGRESSIVE:
                checkState();
                moveAggressive();
                break;
            case CAUGHT:
                if (!gameOverScreenActive) {
                    game_controller gameover = new game_controller();
                    level.addObject(gameover, getWorld().getWidth()/2, getWorld().getHeight()/2); 
                    gameOverScreenActive = true;
                }
                
                break;
        }
        
        update(level.getCameraX());
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
    
    private int getDistanceToCurrentTarget() {
        return Math.abs(currentTarget.getX() - getX());
    }
    
    public void checkState() {
        Player target = findClosestTarget(visionRange);
        
        
        if (estadoAtual != State.CAUGHT && currentTarget != null && getDistanceToCurrentTarget() < visionRange / 4) {
            estadoAtual = State.CAUGHT;
        }
        else if (estadoAtual == State.PATROL && target != null && canSeePlayer(target)) {
            currentTarget = target;
            estadoAtual = State.AGGRESSIVE;
        }
        else if (estadoAtual == State.AGGRESSIVE && currentTarget != null) {
            if (currentTarget.IsHidden()) {         
                if (getDistanceToCurrentTarget() > visionRange / 2) {
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
            exactX += (int) (directionFactor * speed);
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
        
        exactX += (int) (directionFactor * speed * 2);
    }
}
