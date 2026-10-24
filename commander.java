import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class commander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class commander extends Enemy
{
    
    private enum State {
        PATROL,
        AGGRESSIVE,
        DEAD,
        CAUGHT
    }
    
    private int patrolCounter, patrolTime = 1000, _directionFactor, visionRange = 400;
    private float speed = 1.2f;
    private State estadoAtual = State.PATROL;
    private Player currentTarget;
    private boolean gameOverScreenActive = false, dead = false;
    
    private GreenfootImage[] idleAnimLeft, walkAnimLeft, deadAnimLeft;
    private GreenfootImage[] idleAnimRight, walkAnimRight, deadAnimRight;
    
    /**
     * commander - construtor que atribui animações e gera uma direção aleatória.
     */
    public commander() {
        idleAnimLeft = loadAnimation("sprites/commander/idle/idle", 2, 3);
        idleAnimRight = flipAnimation(idleAnimLeft);
        
        walkAnimLeft = loadAnimation("sprites/commander/walk/walk", 4, 3);
        walkAnimRight = flipAnimation(walkAnimLeft);
        
        deadAnimLeft = loadAnimation("sprites/commander/dead/dead", 3, 3);
        deadAnimRight = flipAnimation(deadAnimLeft);
        
        _directionFactor = Greenfoot.getRandomNumber(2) == 0 ? 1 : -1;
    }
    
    /**
     * SetPatrolCounter - modifica o timer de movimento de patrulha.
     */
    public void SetPatrolCounter(int n) {
        patrolCounter = n;
    }
    
    /**
     * Act - do whatever the commander wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * controla o que cada estado deste inimigo fará, e atualiza a posição dele no ecrã.
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
    
    /**
     * findClosestTarget - encontra o jogador mais próximo que não esteja escondido, 
     * e que esteja dentro do range definido.
     */
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
    
    /**
     * canSeePlayer - retorna true se um jogador estiver à frente do inimigo (e não estiver escondido),
     * caso contrário retorna false.
     */
    private boolean canSeePlayer(Player player) {
        if (player.IsHidden()) return false;
        
        boolean playerOnLeft = false;
        playerOnLeft = player.getX() < getX() ? true : false;
        
        if (playerOnLeft && _directionFactor < 0) {
            return true;
        }
        else if (!playerOnLeft && _directionFactor > 0) {
            return true;
        }
        
        return false;
    }
    
    /**
     * getDistanceToCurrentTarget - retorna a distância entre o inimigo e seu alvo atual.
     */
    private int getDistanceToCurrentTarget() {
        return Math.abs(currentTarget.getX() - getX());
    }
    
    /**
     * checkState - método com a lógica de alterar o estado atual do inimigo, e que também procura um possível target, 
     * que se for avistado passa a ser o currentTarget.
     */
    public void checkState() {
        Player target = findClosestTarget(visionRange);
        
        if (IsDead()) {
            animFrame = 0;
            animTimer = 0;
            estadoAtual = State.DEAD;
        }
        else if (estadoAtual != State.CAUGHT && currentTarget != null && getDistanceToCurrentTarget() < visionRange / 4) { // se estiver perto o suficiente
            animSpeed *= 3;
            estadoAtual = State.CAUGHT;
        }
        else if (estadoAtual == State.PATROL && target != null && canSeePlayer(target)) { // se consegue ver o jogador
            currentTarget = target;
            estadoAtual = State.AGGRESSIVE;
            animSpeed /= 3;
        }
        else if (estadoAtual == State.AGGRESSIVE && currentTarget != null) { // enquanto estiver no estado agressivo, se o jogador estiver a uma certa distância e se esconder
            if (currentTarget.IsHidden()) {         
                if (getDistanceToCurrentTarget() > visionRange / 2) {
                    currentTarget = null;
                    estadoAtual = State.PATROL;
                    animSpeed *= 3;
                }
            }
        }
    }
    
    /**
     * movePatrol - movimentação simples do modo patrulha que através de um timer, vai para uma direção, para, e volta para trás.
     */
    public void movePatrol() {
        patrolCounter--;
        if (patrolCounter <= 0) {
            _directionFactor = -_directionFactor;
            patrolCounter = patrolTime;
            
        }
        else if (patrolCounter > 300 && !isAtEdge()){
            exactX += (int) (_directionFactor * speed * 1.5f);
            
            if (_directionFactor < 0) SetFacingRight(false);
            else SetFacingRight(true);
            SetMoving(true);
        }
        else {
            SetMoving(false);
        }
    }
    
    /**
     * moveAggressive - movimentação na direção do alvo atual, com um ligeiro boost na velocidade.
     */
    public void moveAggressive() {
        if (currentTarget != null) {
            _directionFactor = currentTarget.getX() < getX() ? -1 : 1;
        }
        
        exactX += (int) (_directionFactor * speed * 3.5f);
    
        if (_directionFactor < 0) SetFacingRight(false);
        else SetFacingRight(true);
        SetMoving(true);
        
    }
    
    /**
     * animate - método com toda a lógica das animações, condições das mesmas, velocidade de todos os frames,
     * e em alguns casos ANIMATION EVENTS.
     */
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
