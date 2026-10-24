import greenfoot.*;
import java.util.Random;

public class level2 extends Level
{
    private int _levelSeed;
    
    /**
     * level2 - o construtor do nível, adiciona todo o ambiente, controllers, entidades,
     * e também randomiza alguns objetos com base numa seed.
     */
    public level2(int seed)
    {
        setLevelSeed(seed);
        Random rng = new Random(seed);
        
        // SCENARIO ---------------------------------------
        addScrollObject(new ceil(), 1000, 210);
        
        addScrollObject(new floor_top(), 209, 777);
        addScrollObject(new floor_top(), 696, 777);
        addScrollObject(new floor_top(), 1183, 777);
        addScrollObject(new floor_top(), 1670, 777);
        addScrollObject(new floor_top(), 2157, 777);
        addScrollObject(new floor_top(), 2644, 777);
        addScrollObject(new floor_top(), 3131, 777);
        addScrollObject(new floor_top(), 3618, 777);
        addScrollObject(new floor_top(), 4125, 777);
        
        addScrollObject(new level2_wall2(), 209, 577);
        addScrollObject(new level2_wall(), 977, 577);
        addScrollObject(new level2_wall(), 1745, 577);
        addScrollObject(new level2_wall(), 2513, 577);
        addScrollObject(new level2_wall(), 3281, 577);
        addScrollObject(new level2_wall(), 4049, 577);
        addScrollObject(new level2_wall(), 4817, 577);
        
        for (int i = 0; i < rng.nextInt(7) + 2; i++) {
            int randomX = rng.nextInt(3400) + 300;
            addScrollObject(new trash_bin(), randomX, 747);
        }
        
        for (int i = 0; i < rng.nextInt(10) + 1; i++) {
            int randomX = rng.nextInt(3400) + 300;
            addScrollObject(new contentor(), randomX, 710);
        }
        
        addScrollObject(new floor_bottom(), 209, 869);
        addScrollObject(new floor_bottom(), 696, 869);
        addScrollObject(new floor_bottom(), 1183, 869);
        addScrollObject(new floor_bottom(), 1670, 869);
        addScrollObject(new floor_bottom(), 2157, 869);
        addScrollObject(new floor_bottom(), 2644, 869);
        addScrollObject(new floor_bottom(), 3131, 869);
        addScrollObject(new floor_bottom(), 3618, 869);
        addScrollObject(new floor_bottom(), 4125, 869);
        
        
        // ENTITIES AND POINTS ---------------------------------------
        for (int i = 0; i < rng.nextInt(4) + 2; i++) {
            int randomX = rng.nextInt(3200) + 600;
            addScrollObject(new commander(), randomX, 785);
        }
        
        addScrollObject(new zig(), 143, 785);
        addScrollObject(new zag(), 103, 785);
        addScrollObject(new StartPoint(), 123, 1053);
        
        // CONTROLLERS ---------------------------------------
        addObject(lifeController, getWidth()-1520, getHeight() - 870);
        addObject(clueController, getWidth()-1540, getHeight() - 835);
    }
}