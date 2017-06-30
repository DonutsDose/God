package code.Logic.Abstract;

import code.GUI.Cell.CellRenderer;
import code.GUI.Map.MapRender;
import code.MyMath.Point;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */

abstract public class Creature {

    public Point pos;
    public int color = 0, type = 0, readyToReproduct = 0, calories = 0;
    protected int age = 0, PERIOD_OF_PREGNANT = 0, PROBABLY_DIE;
    protected boolean sex, pregnant = false;
    public char face = ' ';

    public Creature(Point pos, char face, int color, int type, int PERIOD_OF_PREGNANT, int PROBABLY_DIE, boolean sex, int calories) {
        this.pos = pos;
        this.calories = calories;
        this.face  = face;
        this.color = color;
        this.type = type;
        this.sex = sex;
        this.PROBABLY_DIE = PROBABLY_DIE;
        this.PERIOD_OF_PREGNANT = PERIOD_OF_PREGNANT;
    }

    public boolean act() {
        age++;
        if (xRandom.getBoolean(age / PROBABLY_DIE)) return false;
        return true;
    }

    abstract public boolean canBeEaten();
    abstract public void eaten();
    abstract public String getInformation();
}
