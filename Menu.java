import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    Button playBtn = new Button(), exitBtn = new Button();
    audio_controller audioController = new audio_controller(true);

    /**
     * Menu - construtor do menu, que simplesmente coloca os botões invisiveis por cima da imagem de fundo.
     */
    public Menu()
    {    
        super(1550, 900, 1, false);
        
        addObject(playBtn, getWidth() / 2 + 480, getHeight() / 2 - 60);
        addObject(exitBtn, getWidth() / 2 + 480, getHeight() / 2 + 120);
        
        addObject(audioController, getWidth()-1540, getHeight() - 835);
    }
    
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        OnClick();
    }
    
    /**
     * OnClick - controla o resultado da interação com os botões.
     */
    public void OnClick() {
        if (playBtn.Clicked()) {
            Greenfoot.setWorld(new level1(Greenfoot.getRandomNumber(5000)));
            audioController.StopMusic();
        }
        else if (exitBtn.Clicked()) {
            System.exit(0);
        }
    }
}
