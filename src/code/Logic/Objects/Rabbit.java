package code.Logic.Objects;

import code.GUI.Formatter.Formatter;
import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Engine.Engine;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 25.06.2017.
 */
public class Rabbit extends AnimalPrimitive {

    private static final char RABBIT_FACE = '\u066D';
    private static final int RABBIT_COLOR = 0x696969;
    private static final int RABBIT_PROBABLY_DIE = 80;
    private static final int RABBIT_MAX_ENERGY = 300;
    private static final int RABBIT_CALORIES = 3600;


    public Rabbit(Point pt) {
        super(pt, RABBIT_FACE, RABBIT_COLOR, CREATURE_ANIMAL_RABBIT, 0, RABBIT_MAX_ENERGY, RABBIT_PROBABLY_DIE, false, RABBIT_CALORIES);
    }

    @Override
    public boolean act() {
        if (!super.act()) return death();
        if (energy < (MAX_ENERGY>>1) && xRandom.getBoolean(AnimalPrimitive.PROBABLY_OF_SLEEPING)) sleep(); else moveQuietly();
        return ok();
    }


    @Override
    public String getInformation() {
        return String.format("<html>Type: Rabbit<br>Age: %s<br>Energy: %s %%</html>", Formatter.formatDate(age), xMath.percent(RABBIT_MAX_ENERGY, energy));
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

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityRabbit[Map.world.landscape[x][y]];
    }
}
