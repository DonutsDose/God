package code.Logic.Objects;

import code.GUI.Map.Map;
import code.Logic.Abstract.Animal;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 23.06.2017.
 */
public class Fish extends Animal {

    private static final int FISH_DELTA_OVER_PROBABLY = 20;
    private static final char FISH_FACE = '\u066D';
    private static final int FISH_COLOR = 0x336633;

    public Fish(int x, int y) {
        super(x, y, FISH_FACE, FISH_COLOR, CREATURE_ANIMAL_FISH, 0, 100, 0);
    }

    @Override
    public boolean act() {
        age++;
        if (xRandom.getBoolean(age / FISH_DELTA_OVER_PROBABLY)) return false;
        if (energy < (MAX_ENERGY>>1) && xRandom.getBoolean(Animal.PROBABLY_OF_SLEEPING)) sleep(); else move(0, 0);
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
