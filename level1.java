import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class level1 extends Level
{

    /**
     * Constructor for objects of class level1.
     * 
     */
    public level1()
    {
        addObject(new street_top(), 209, 904);
        addObject(new street_bottom(), 209, 968);
        
        addObject(new street_top(), 696, 904);
        addObject(new street_bottom(), 696, 968);
        
        addObject(new street_top(), 1183, 904);
        addObject(new street_bottom(), 1183, 968);
        
        addObject(new street_top(), 1670, 904);
        addObject(new street_bottom(), 1670, 968);
        //addObject(new zig(), 798, 520);
    }
}
