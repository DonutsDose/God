package code.Logic.Abstract;

import code.GUI.Map.Map;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 23.06.2017.
 */

abstract public class AnimalPrimitive extends Creature {

    protected static final int PROBABLY_OF_SLEEPING = 45;

    public static final int CREATURE_ANIMAL_FISH = 11;
    public static final int CREATURE_ANIMAL_RABBIT = 12;

    protected int MAX_ENERGY;

    public AnimalPrimitive(Point pos, char face, int color, int type, int PERIOD_OF_PREGNANT, int MAX_ENERGY, int PROBABLY_DIE, boolean sex, int calories) {
        super(pos, face, color, type, PERIOD_OF_PREGNANT, PROBABLY_DIE, sex, calories);
        this.MAX_ENERGY = MAX_ENERGY;
        energy = MAX_ENERGY;
    }

    protected void moveQuietly() {
        int xShift = xRandom.getIntInRange(-1, 1);
        int yShift = xRandom.getIntInRange(-1, 1);
        if (xShift == 0 && yShift == 0) {
            sleep();
            return;
        }
        Point to = new Point(pos.getX() + xShift, pos.getY() + yShift);
        if (!move(to)) sleep();
    }

    abstract protected void sleep();
}
