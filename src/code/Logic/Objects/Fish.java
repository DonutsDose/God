package code.Logic.Objects;

import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Engine.Engine;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 23.06.2017.
 */
public class Fish extends AnimalPrimitive {

    private static final int FISH_PROBABLY_DIE = 20;
    private static final int FISH_MAX_ENERGY = 203;
    private static final char FISH_FACE = '\u037D';
    private static final int FISH_COLOR = 0x336633;


    public Fish(int x, int y) {
        super(x, y, FISH_FACE, FISH_COLOR, CREATURE_ANIMAL_FISH, 0, FISH_MAX_ENERGY, 0, FISH_PROBABLY_DIE);
        Engine.fishCount++;
    }

    @Override
    public boolean act() {
        if (!super.act()) {
            Engine.fishCount--;
            return false;
        }
        if (energy < (MAX_ENERGY>>1) && xRandom.getBoolean(AnimalPrimitive.PROBABLY_OF_SLEEPING)) sleep(); else move(0, 0);
        return true;
    }

    @Override
    protected void setPassability() {
        passability[Map.LANDSCAPE_WATER_LOW] = 50;
        passability[Map.LANDSCAPE_WATER_HIGH] = 65;
    }

    @Override
    protected void sleep() {
        energy = MAX_ENERGY;
    }
}
