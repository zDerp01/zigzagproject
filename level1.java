import greenfoot.*;

public class level1 extends Level
{
    public level1()
    {
        // SCENARIO ---------------------------------------
        addScrollObject(new sky(), 1800, 210);
        
        addScrollObject(new street_top(), 209, 777);
        addScrollObject(new street_top(), 696, 777);
        addScrollObject(new street_top(), 1183, 777);
        addScrollObject(new street_top(), 1670, 777);
        addScrollObject(new street_top(), 2157, 777);
        addScrollObject(new street_top(), 2644, 777);
        addScrollObject(new street_top(), 3131, 777);
        addScrollObject(new street_top(), 3618, 777);
        
        addScrollObject(new farbuilding2(), 229, 546);
        addScrollObject(new farbuilding2(), 540, 546);
        addScrollObject(new farbuilding2(), 851, 546);
        addScrollObject(new farbuilding2(), 1162, 546);
        addScrollObject(new farbuilding2(), 1473, 546);
        addScrollObject(new farbuilding2(), 1784, 546);
        addScrollObject(new farbuilding2(), 2095, 546);
        addScrollObject(new farbuilding2(), 2406, 546);
        addScrollObject(new farbuilding2(), 2717, 546);
        addScrollObject(new farbuilding2(), 3028, 546);
        addScrollObject(new farbuilding2(), 3339, 546);
        
        addScrollObject(new farbuilding(), 27, 545);
        addScrollObject(new farbuilding(), 337, 545);
        addScrollObject(new farbuilding(), 647, 545);
        addScrollObject(new farbuilding(), 957, 545);
        addScrollObject(new farbuilding(), 1267, 545);
        addScrollObject(new farbuilding(), 1577, 545);
        addScrollObject(new farbuilding(), 1887, 545);
        addScrollObject(new farbuilding(), 2197, 545);
        addScrollObject(new farbuilding(), 2507, 545);
        addScrollObject(new farbuilding(), 2817, 545);
        addScrollObject(new farbuilding(), 3127, 545);
        addScrollObject(new farbuilding(), 3437, 545);
        
        addScrollObject(new building(), 121, 520);
        addScrollObject(new building(), 430, 520);
        addScrollObject(new building(), 739, 520);
        addScrollObject(new building(), 1048, 520);
        addScrollObject(new building(), 1357, 520);
        addScrollObject(new building(), 1666, 520);
        addScrollObject(new building(), 1975, 520);
        addScrollObject(new building(), 2284, 520);
        addScrollObject(new building(), 2593, 520);
        addScrollObject(new building(), 2902, 520);
        addScrollObject(new building(), 3211, 520);
        addScrollObject(new building(), 3520, 520);
        
        addScrollObject(new street_lamp(), 547, 720);
        addScrollObject(new street_lamp(), 1147, 720);
        addScrollObject(new street_lamp(), 1447, 720);
        addScrollObject(new street_lamp(), 2347, 720);
        addScrollObject(new street_lamp(), 2947, 720);
        addScrollObject(new street_lamp(), 3247, 720);

        for (int i = 0; i < Greenfoot.getRandomNumber(6) + 2; i++) {
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            addScrollObject(new trash_bin(), randomX, 747);
        }
        
        for (int i = 0; i < Greenfoot.getRandomNumber(2) + 1; i++) {
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            addScrollObject(new phone_cabin(), randomX, 720);
        }
        
        for (int i = 0; i < Greenfoot.getRandomNumber(5) + 1; i++) {
            int randomX = Greenfoot.getRandomNumber(3000) + 300;
            addScrollObject(new car(), randomX, 775);
        }
        
        addScrollObject(new street_bottom(), 209, 869);
        addScrollObject(new street_bottom(), 696, 869);
        addScrollObject(new street_bottom(), 1183, 869);
        addScrollObject(new street_bottom(), 1670, 869);
        addScrollObject(new street_bottom(), 2157, 869);
        addScrollObject(new street_bottom(), 2644, 869);
        addScrollObject(new street_bottom(), 3131, 869);
        addScrollObject(new street_bottom(), 3618, 869);
        
        // ENTITIES AND POINTS ---------------------------------------
        addScrollObject(new capanga(), Greenfoot.getRandomNumber(3000) + 300, 786);
        
        addScrollObject(new zig(), 143, 815);
        addScrollObject(new zag(), 103, 815);
        addScrollObject(new StartPoint(), 123, 853);
        
        // CONTROLLERS ---------------------------------------
        addObject(new clue_controller(), 0, 0);
    }
}