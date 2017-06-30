package code.Logic.Abstract;

import code.GUI.Cell.CellRenderer;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.GUI.Map.MapRender;
import code.Logic.Engine.Engine;
import code.MyMath.Point;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */

abstract public class Creature {

    public static final char SELECTED_FACE = '$';

    public Point pos;
    public int color = 0, type = 0, readyToReproduct = 0, calories = 0;
    protected int age = 0, PERIOD_OF_PREGNANT = 0, PROBABLY_DIE;
    protected boolean sex, pregnant = false;
    public char face = ' ', NORMAL_FACE;
    public boolean isSelected = false;

    public Creature(Point pos, char NORMAL_FACE, int color, int type, int PERIOD_OF_PREGNANT, int PROBABLY_DIE, boolean sex, int calories) {
        this.pos = pos;
        this.calories = calories;
        this.face  = NORMAL_FACE;
        this.color = color;
        this.type = type;
        this.sex = sex;
        this.PROBABLY_DIE = PROBABLY_DIE;
        this.PERIOD_OF_PREGNANT = PERIOD_OF_PREGNANT;
        this.NORMAL_FACE = NORMAL_FACE;
        Map.world.ref[pos.getX()][pos.getY()] = this;
    }

    public boolean act() {
        age++;
        if (xRandom.getBoolean(age / PROBABLY_DIE)) return false;
        return true;
    }

    public boolean death() {
        if (isSelected) {
            isSelected = false;
            Engine.selected = null;
            Engine.existSelected = false;
        }
        Map.world.ref[pos.getX()][pos.getY()] = null;
        return false;
    }

    public boolean ok() {
        if (isSelected) {
            face = SELECTED_FACE;
            Map.uploadInfoCell(pos.getX(), pos.getY());
        } else face = NORMAL_FACE;
        return true;
    }

    abstract public boolean canBeEaten();
    abstract public void eaten();
    abstract public String getInformation();
}
