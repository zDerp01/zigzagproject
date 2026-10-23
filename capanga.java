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
        DEAD,
        CAUGHT
    }
    
    private int patrolCounter, patrolTime = 1000, directionFactor = 1, visionRange = 500;
    private float speed = 1.2f;
    private State estadoAtual = State.PATROL;
    private Player currentTarget;
    private boolean gameOverScreenActive = false, dead = false;
    
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, deadAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, deadAnimRight;
    
    public capanga() {
        idleAnimLeft = loadAnimation("sprites/capanga/idle/idle", 2, 3);
        idleAnimRight = flipAnimation(idleAnimLeft);
        
        walkAnimLeft = loadAnimation("sprites/capanga/walk/walk", 4, 3);
        walkAnimRight = flipAnimation(walkAnimLeft);
        
        deadAnimLeft = loadAnimation("sprites/capanga/dead/dead", 3, 3);
        deadAnimRight = flipAnimation(deadAnimLeft);
    }
    
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
            case DEAD:   
                break;
            case CAUGHT:
                if (!gameOverScreenActive) {
                    game_controller gameover = new game_controller();
                    level.addObject(gameover, getWorld().getWidth()/2, getWorld().getHeight()/2); 
                    SetMoving(false);
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
        
        return false;
    }
    
    private int getDistanceToCurrentTarget() {
        return Math.abs(currentTarget.getX() - getX());
    }
    
    public void checkState() {
        Player target = findClosestTarget(visionRange);
        
        if (IsDead()) {
            animFrame = 0;
            animTimer = 0;
            estadoAtual = State.DEAD;
        }
        else if (estadoAtual != State.CAUGHT && currentTarget != null && getDistanceToCurrentTarget() < visionRange / 4) {
            animSpeed *= 2;
            estadoAtual = State.CAUGHT;
        }
        else if (estadoAtual == State.PATROL && target != null && canSeePlayer(target)) {
            currentTarget = target;
            estadoAtual = State.AGGRESSIVE;
            animSpeed /= 2;
        }
        else if (estadoAtual == State.AGGRESSIVE && currentTarget != null) {
            if (currentTarget.IsHidden()) {         
                if (getDistanceToCurrentTarget() > visionRange / 2) {
                    currentTarget = null;
                    estadoAtual = State.PATROL;
                    animSpeed *= 2;
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
        else if (patrolCounter > 300 && !isAtEdge()){
            exactX += (int) (directionFactor * speed);
            
            if (directionFactor < 0) SetFacingRight(false);
            else SetFacingRight(true);
            SetMoving(true);
        }
        else {
            SetMoving(false);
        }
    }
    
    public void moveAggressive() {
        if (currentTarget != null) {
            directionFactor = currentTarget.getX() < getX() ? -1 : 1;
        }
        
        exactX += (int) (directionFactor * speed * 2);
    
        if (directionFactor < 0) SetFacingRight(false);
        else SetFacingRight(true);
        SetMoving(true);
        
    }
    
    @Override
    protected void animate() {
        animTimer++;
        if (animTimer >= animSpeed) {
            animTimer = 0;
            animFrame++;
        }
        
        if (IsDead()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? deadAnimRight : deadAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = currentAnim.length - 1;
            setImage(currentAnim[animFrame]);
        }
        else if (IsMoving()) {
            GreenfootImage[] currentAnim = IsFacingRight() ? walkAnimRight : walkAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = 0; // looping
            setImage(currentAnim[animFrame]);
        } 
        else {
            GreenfootImage[] currentAnim = IsFacingRight() ? idleAnimRight : idleAnimLeft;
            if (animFrame >= currentAnim.length) animFrame = 0; // looping
            setImage(currentAnim[animFrame]);
        }
    }
}
