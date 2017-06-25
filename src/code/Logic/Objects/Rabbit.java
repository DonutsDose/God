package code.Logic.Objects;

import code.GUI.Map.Map;
import code.Logic.Abstract.Animal;
import code.Logic.Engine.Engine;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 25.06.2017.
 */
public class Rabbit extends Animal {

    private static final char RABBIT_FACE = '\u1D25';
    private static final int RABBIT_COLOR = 0x333333;
    private static final int RABBIT_PROBABLY_DIE = 800;
    private static final int RABBIT_MAX_ENERGY = 300;

    public Rabbit(int x, int y) {
        super(x, y, RABBIT_FACE, RABBIT_COLOR, CREATURE_ANIMAL_RABBIT, 0, RABBIT_MAX_ENERGY, 0, RABBIT_PROBABLY_DIE);
        Engine.rabbitCount++;
    }

    @Override
    public boolean act() {
        if (!super.act()) {
            Engine.rabbitCount--;
            return false;
        }
        if (energy < (MAX_ENERGY>>1) && xRandom.getBoolean(Animal.PROBABLY_OF_SLEEPING)) sleep(); else move(0, 0);
        return true;
    }

    @Override
    protected void setPassability() {
        passability[Map.LANDSCAPE_GROUND_LOW] = 25;
        passability[Map.LANDSCAPE_GROUND_HIGH] = 35;
        passability[Map.LANDSCAPE_GROUND_HIGH] = 40;
    }

    @Override
    protected void sleep() {
        energy = MAX_ENERGY;
    }
}
