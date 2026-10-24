import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Sprite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollableSprite extends Actor
{
    protected int exactX; //posição absoluta no mundo, apenas muda se o proprio objeto se mover
    
    /**
     * getExactX - retorna a posição absoluta do objeto no mundo.
     */
    public int getExactX() {
        return exactX;
    }
    
    /**
     * setLocationScroll - modifica a posição absoluta do objeto no mundo.
     */
    public void setLocationScroll(int x) {
        exactX = x;
    }
    
    /**
     * update - calcula e atualiza a posição X no ecrã do objeto com base na 
     * posição absoluta e a posição da câmera.
     */
    public void update(int cameraX) {
        int screenX = exactX - cameraX;
        setLocation(screenX, getY());
    }
    
    /**
     * replaceEntitiesAndPointsOnTop - recoloca todas as entidades e o starting point para aparecerem por cima.
     * USO EXEMPLO: ao sair de trás de um carro, é necessário que o jogador em questão seja redesenhado por cima do carro.
     */
    protected void replaceEntitiesAndPointsOnTop() {
        World world = getWorld();
        
        List<Entity> entities = world.getObjects(Entity.class);
        for (Entity entity : entities) {
            if ((entity instanceof Player p && !p.IsHidden()) || entity instanceof Enemy) {
                int eX = entity.getX();
                int eY = entity.getY();
                    
                world.removeObject(entity);   
                world.addObject(entity, eX, eY);
            }
        }
        
        StartPoint sp = world.getObjects(StartPoint.class).get(0);
        int spX = sp.getX();
        int spY = sp.getY();
                
        world.removeObject(sp);   
        world.addObject(sp, spX, spY);
    }
    
    /**
     * loadAnimation - monta um array com imagens que apresentam um determinado prefixo no nome do ficheiro.
     * também redimensiona as imagens do array no processo.
     */
    protected GreenfootImage[] loadAnimation(String prefix, int framesCount, int scale) {
        GreenfootImage[] images = new GreenfootImage[framesCount];
        for (int i = 0; i < framesCount; i++) {
            images[i] = new GreenfootImage(prefix + i + ".png");
            images[i].scale(images[i].getWidth() * scale, images[i].getHeight() * scale);
        }
        return images;
    }
    
    /**
     * flipAnimation - recebe um array de imagens (animação) e inverte cada uma das imagens para
     * gerar uma versão na direção oposta.
     */
    protected GreenfootImage[] flipAnimation(GreenfootImage[] original) {
        GreenfootImage[] flipped = new GreenfootImage[original.length];
        for (int i = 0; i < original.length; i++) {
            flipped[i] = new GreenfootImage(original[i]);
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
}
