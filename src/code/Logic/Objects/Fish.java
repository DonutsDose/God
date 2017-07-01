package code.Logic.Objects;

import code.GUI.Control.ControlPanel;
import code.GUI.Formatter.Formatter;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Engine.Engine;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 23.06.2017.
 */
public class Fish extends AnimalPrimitive {

    private static final int FISH_PROBABLY_DIE = 15;
    private static final int FISH_MAX_ENERGY = 203;
    private static final char FISH_FACE = '\u037D';
    private static final int FISH_COLOR = 0x336633;
    private static final int FISH_CALORIES = 3567;


    public Fish(Point pt) {
        super(pt, FISH_FACE, FISH_COLOR, CREATURE_ANIMAL_FISH, 0, FISH_MAX_ENERGY, FISH_PROBABLY_DIE, false, FISH_CALORIES);
    }

    @Override
    public boolean act() {
        if (!super.act()) return death();
        if (energy < (MAX_ENERGY>>1) && xRandom.getBoolean(AnimalPrimitive.PROBABLY_OF_SLEEPING)) sleep(); else moveQuietly();
        return ok();
    }

    @Override
    public String getInformation() {
        return String.format("<html>Type: Fish<br>Age: %s<br>Energy: %s %%</html>", Formatter.formatDate(age), xMath.percent(FISH_MAX_ENERGY, energy));
    }

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityFish[Map.world.landscape[x][y]];
    }

    @Override
    protected void sleep() {
        energy = MAX_ENERGY;
    }

    @Override
    public void eaten() {}

    @Override
    public boolean canBeEaten() {
        return true;
    }
}
