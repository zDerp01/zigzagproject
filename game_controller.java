import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class game_controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class game_controller extends Controllers
{
    private boolean pressedEnter;
    
    /**
     * game_controller - construtor que dá setup na imagem que aparece quando um inimigo avista
     * o jogador.
     */
    public game_controller() {
        GreenfootImage image = new GreenfootImage(500, 120);
        image.setColor(new Color(0, 0, 0, 180));
        image.fill();

        GreenfootImage title = new GreenfootImage("Foste Visto", 48, Color.WHITE, Color.BLACK);
        image.drawImage(title, (500 - title.getWidth()) / 2, 15);

        GreenfootImage subtitle = new GreenfootImage("Pressiona ENTER para tentar de novo", 24, Color.GRAY, Color.BLACK);
        image.drawImage(subtitle, (500 - subtitle.getWidth()) / 2, 70);

        setImage(image);
    }
    
    /**
     * addedToWorld - trata de restrigir todos os jogadores (congelar),
     * quando adicionado ao mundo.
     */
    @Override
    protected void addedToWorld(World world) {    
        List<Player> players = world.getObjects(Player.class);
        for (Player player : players) {
            player.SetIsRestricted(true);
        }
    }
    
    /**
     * Act - do whatever the game_controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.isKeyDown("enter") && !pressedEnter) {
            pressedEnter = true;
            if (getWorld() instanceof level1 currentLevel) currentLevel.lifeController.DecreaseLives(1);
            if (getWorld() instanceof level2 currentLevel) currentLevel.lifeController.DecreaseLives(2);
        }
    }
}
