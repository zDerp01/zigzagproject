import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    private String _pressedKey;
    private int _cameraX = 0;
    private final int CAMERA_LIMIT = 2660;
    
    /**
     * getPressedKey - retorna a tecla pressionada no momento.
     */
    public String getPressedKey() {
        return _pressedKey;
    }
    
    /**
     * getCameraX - retorna a posição X da câmera.
     */
    public int getCameraX() {
        return _cameraX;
    }
    
    /**
     * setCameraX - muda a posição X da câmera.
     */
    public void setCameraX(int value) {
        _cameraX = value;
    }
    
    /**
     * getCameraLimit - retorna o limite direito máximo da câmera.
     */
    public int getCameraLimit() {
        return CAMERA_LIMIT;
    }

    /**
     * Level - construtor que dá set na escala 16:9 e dá uma cor ao background padrão.
     */
    public Level()
    {    
        super(1600, 900, 1, false);
        getBackground().setColor(new Color(40, 40, 50));
        getBackground().fill();
    }
    
    /**
     * Act - do whatever the Level wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        _pressedKey = Greenfoot.getKey();
    }
    
    /**
     * addScrollObject - adiciona o objeto obj nas coordenadas x, y, definindo também a coordenada exactX do objeto
     * para funcionar com o scroll da câmera.
     */
    public void addScrollObject(ScrollableSprite obj, int x, int y) {
        addObject(obj, x, y);
        obj.setLocationScroll(x);
    }
    
    /**
     * moveWorld - move todos os objetos do mundo (menos o zig por ser o controlador da câmera e o céu por estética).
     */
    public void moveWorld() {
        for (ScrollableSprite obj : getObjects(ScrollableSprite.class)) {
            if (obj instanceof zig || obj instanceof sky) continue;
            
            obj.update(_cameraX);
        }
    }
}
