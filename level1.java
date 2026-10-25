import greenfoot.*;
import java.util.Random;

public class level1 extends Level
{
    /**
     * level1 - o construtor do nível, adiciona todo o ambiente, controllers, entidades,
     * e também randomiza alguns objetos com base numa seed.
     */
    public level1(int seed)
    {
        setLevelSeed(seed);
        Random rng = new Random(seed);
        
        // SCENARIO ---------------------------------------
        addScrollObject(new sky(), 1000, 210);
        
        addScrollObject(new street_top(), 209, 777);
        addScrollObject(new street_top(), 696, 777);
        addScrollObject(new street_top(), 1183, 777);
        addScrollObject(new street_top(), 1670, 777);
        addScrollObject(new street_top(), 2157, 777);
        addScrollObject(new street_top(), 2644, 777);
        addScrollObject(new street_top(), 3131, 777);
        addScrollObject(new curve_top(), 3618, 777);
        addScrollObject(new sidewalk_top(), 4125, 777);
        
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
        addScrollObject(new farbuilding2(), 3650, 546);
        addScrollObject(new farbuilding2(), 3961, 546);
        
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
        addScrollObject(new farbuilding(), 3747, 545);
        addScrollObject(new farbuilding(), 4057, 545);
        
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
        addScrollObject(new building(), 3829, 520);
        addScrollObject(new building(), 4138, 520);
        addScrollObject(new EndPoint(), 4200, 570);
        addScrollObject(new levelDoor(), 4249, 745);

        addScrollObject(new street_lamp(), 300, 720);
        addScrollObject(new street_lamp(), 800, 720);
        addScrollObject(new street_lamp(), 1300, 720);
        addScrollObject(new street_lamp(), 1800, 720);
        addScrollObject(new street_lamp(), 2300, 720);
        addScrollObject(new street_lamp(), 2800, 720);
        addScrollObject(new street_lamp(), 3300, 720);
        addScrollObject(new street_lamp(), 3800, 720);

        for (int i = 0; i < rng.nextInt(5) + 4; i++) {
            int randomX = rng.nextInt(3400) + 300;
            addScrollObject(new trash_bin(), randomX, 747);
        }
        
        for (int i = 0; i < rng.nextInt(2) + 1; i++) {
            int randomX = rng.nextInt(3400) + 300;
            addScrollObject(new phone_cabin(), randomX, 720);
        }
        
        for (int i = 0; i < rng.nextInt(10) + 4; i++) {
            int randomX = rng.nextInt(3400) + 300;
            addScrollObject(new car(), randomX, 775);
        }
        
        addScrollObject(new street_bottom(), 209, 869);
        addScrollObject(new street_bottom(), 696, 869);
        addScrollObject(new street_bottom(), 1183, 869);
        addScrollObject(new street_bottom(), 1670, 869);
        addScrollObject(new street_bottom(), 2157, 869);
        addScrollObject(new street_bottom(), 2644, 869);
        addScrollObject(new street_bottom(), 3131, 869);
        addScrollObject(new curve_bottom(), 3618, 869);
        addScrollObject(new sidewalk_bottom(), 4125, 869);
        
        
        // ENTITIES AND POINTS ---------------------------------------
        for (int i = 0; i < rng.nextInt(3) + 4; i++) {
            int randomX = rng.nextInt(3200) + 600;
            addScrollObject(new capanga(), randomX, 785);
        }
        
        addScrollObject(new zig(), 143, 785);
        addScrollObject(new zag(), 103, 785);
        addScrollObject(new StartPoint(), 123, 853);
        
        // CONTROLLERS ---------------------------------------
        addObject(lifeController, getWidth()-1520, getHeight() - 870);
        addObject(scoreController, getWidth()-115, getHeight() - 870);
        addObject(clueController, getWidth()-1540, getHeight() - 835);
        addObject(audioController, getWidth(), getHeight() - 100);
    }
}