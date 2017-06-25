package code.Logic.Abstract;

import code.GUI.Cell.CellRenderer;
import code.GUI.Map.MapRender;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */

abstract public class Creature {

    public int x = 0, y = 0, color = 0;
    protected int age = 0, pregnancy = 0, PERIOD_OF_PREGNANT = 0, type = 0, PROBABLY_DIE;
    protected boolean pregnant = false;
    public char face = ' ';

    public Creature(int x, int y, char face, int color, int type, int PERIOD_OF_PREGNANT, int PROBABLY_DIE) {
        this.x = x;
        this.y = y;
        this.face  = face;
        this.color = color;
        this.type = type;
        this.PROBABLY_DIE = PROBABLY_DIE;
        this.PERIOD_OF_PREGNANT = PERIOD_OF_PREGNANT;
    }

    public boolean act() {
        age++;
        if (xRandom.getBoolean(age / PROBABLY_DIE)) return false;
        return true;
    }
}
