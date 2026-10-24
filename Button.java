import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends UI
{
    /**
     * Button - construtor que dá setup numa imagem invisível para o botão.
     */
    public Button()
    {
        GreenfootImage img = new GreenfootImage(250, 90);
        //img.setColor(Color.GRAY);
        //img.fill();
        setImage(img);
    }
    
    /**
     * Clicked - retorna se este botão foi clicado com o rato.
     */
    public boolean Clicked() {
        return Greenfoot.mouseClicked(this);
    }
}
