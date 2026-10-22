import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sprite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollableSprite extends Actor
{
    protected int exactX;
    
    public void setLocationScroll(int x) {
        this.exactX = x;
    }
    
    public void update(int cameraX) {
        int screenX = exactX - cameraX;
        super.setLocation(screenX, getY());
    }
    
    protected GreenfootImage[] loadAnimation(String prefix, int framesCount, int scale) {
        GreenfootImage[] images = new GreenfootImage[framesCount];
        for (int i = 0; i < framesCount; i++) {
            images[i] = new GreenfootImage(prefix + i + ".png");
            images[i].scale(images[i].getWidth() * scale, images[i].getHeight() * scale);
        }
        return images;
    }
    
    protected GreenfootImage[] flipAnimation(GreenfootImage[] original) {
        GreenfootImage[] flipped = new GreenfootImage[original.length];
        for (int i = 0; i < original.length; i++) {
            flipped[i] = new GreenfootImage(original[i]);
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
}
