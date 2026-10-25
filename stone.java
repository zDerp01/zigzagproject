import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class stone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class stone extends Others
{
    private boolean _goingRight;
    private zag _owner;
    private int _gravity, _gravityTimer;
    
    public stone(boolean goingRight, zag owner) {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 3, image.getHeight() * 3);
        setImage(image);
        
        _goingRight = goingRight;
        _owner = owner;
    }
    /**
     * Act - do whatever the stone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        Level level = (Level) getWorld();
        
        if (!_goingRight) {
            exactX -= 7;
        }
        else {
            exactX += 7;
        }
        
        update(level.getCameraX());
        
        fall();
    }
    
    public void fall() {
        int distanceToOwner = Math.abs(_owner.getExactX() - getExactX());
        _gravityTimer++;
        
        if (isTouching(Ground.class)) {
            breakStone();
            return;
        }
        
        if (distanceToOwner >= 250) {
            if (_gravityTimer >= 20) {
                _gravityTimer = 0;
                _gravity++;
            }
        }
        
        setLocation(getX(), getY() + _gravity);
    }
    
    public void breakStone() {
        World world = getWorld();
        List<Enemy> enemies = world.getObjects(Enemy.class);
        for (Enemy enemy : enemies) {
            if (Math.abs(enemy.getExactX() - getExactX()) <= 200) {
                if (enemy.IsFacingRight() && enemy.getExactX() >= getExactX()) {
                    if (enemy instanceof capanga c) {
                        c.SetPatrolCounter(0);
                    }
                    else if (enemy instanceof commander cd) {
                        cd.SetPatrolCounter(0);
                    }
                    
                    ((Level) world).scoreController.AddRandomScore();
                }
                else if (!enemy.IsFacingRight() && enemy.getExactX() < getExactX()) {
                    if (enemy instanceof capanga c) {
                        c.SetPatrolCounter(0);
                    }
                    else if (enemy instanceof commander cd) {
                        cd.SetPatrolCounter(0);
                    }
                    
                    ((Level) world).scoreController.AddRandomScore();
                }
            }
        }
        
        world.removeObject(this);
    }
}
