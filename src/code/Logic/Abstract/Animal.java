package code.Logic.Abstract;

import code.GUI.Map.Map;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 23.06.2017.
 */

abstract public class Animal extends Creature {

    protected static final int MIN_ENERGY = 10;
    protected static final int PROBABLY_OF_SLEEPING = 45;

    protected static final int CREATURE_ANIMAL_FISH = 11;
    protected static final int CREATURE_ANIMAL_RABBIT = 12;

    protected boolean[] ration = new boolean[Map.MAX_CREATURE_TYPE];
    protected int[] passability = new int[Map.MAX_LANDSCAPE_TYPE];
    protected int MAX_ENERGY, energy, satiety, MAX_SATIETY;

    public Animal(int x, int y, char face, int color, int type, int PERIOD_OF_PREGNANT, int MAX_ENERGY, int MAX_SATIETY, int PROBABLY_DIE) {
        super(x, y, face, color, type, PERIOD_OF_PREGNANT, PROBABLY_DIE);
        this.MAX_ENERGY = MAX_ENERGY;
        this.MAX_SATIETY = MAX_SATIETY;
        energy = MAX_ENERGY;
        for (int i=0; i<Map.MAX_CREATURE_TYPE; i++) ration[i] = false;
        for (int i=0; i<Map.MAX_LANDSCAPE_TYPE; i++) passability[i] = -1;
        setPassability();
    }

    abstract protected void setPassability();

    protected void move(int xShift, int yShift) {
        if (xShift == 0 && yShift == 0) {
            xShift = xRandom.getIntInRange(-1, 1);
            yShift = xRandom.getIntInRange(-1, 1);
        }
        if (xShift == 0 && yShift == 0) {
            sleep();
            return;
        }
        int xTo = x + xShift;
        int yTo = y + yShift;
        if (!xMath.inMap(xTo, yTo) || passability[Map.world.landscape[xTo][yTo]] == -1 || !Map.checkEmptyPosition(xTo, yTo) ||
                passability[Map.world.landscape[xTo][yTo]] > energy) {
            sleep();
            return;
        }
        x = xTo;
        y = yTo;
        energy -= passability[Map.world.landscape[xTo][yTo]];
    }

    abstract protected void sleep();

}
