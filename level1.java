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
        addObject(new street_top(), 209, 867);
        addObject(new street_top(), 696, 867);
        addObject(new street_top(), 1183, 867);
        addObject(new street_top(), 1670, 867);
        
        addObject(new farbuilding2(), 229, 636);
        addObject(new farbuilding2(), 540, 636);
        addObject(new farbuilding2(), 851, 636);
        addObject(new farbuilding2(), 1162, 636);
        addObject(new farbuilding2(), 1473, 636);
        addObject(new farbuilding2(), 1784, 636);
        
        addObject(new farbuilding(), 27, 635);
        addObject(new farbuilding(), 337, 635);
        addObject(new farbuilding(), 647, 635);
        addObject(new farbuilding(), 957, 635);
        addObject(new farbuilding(), 1267, 635);
        addObject(new farbuilding(), 1577, 635);
        
        addObject(new building(), 121, 610);
        addObject(new building(), 430, 610);
        addObject(new building(), 739, 610);
        addObject(new building(), 1048, 610);
        addObject(new building(), 1357, 610);
        addObject(new building(), 1666, 610);
        
        addObject(new street_bottom(), 209, 959);
        addObject(new street_bottom(), 696, 959);
        addObject(new street_bottom(), 1183, 959);
        addObject(new street_bottom(), 1670, 959);
        
        //addObject(new zig(), 798, 520);
    }
}
