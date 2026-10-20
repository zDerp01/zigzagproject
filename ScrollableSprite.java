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
}
