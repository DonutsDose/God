package code.Logic.Objects;

import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Abstract.AnimalSapiens;
import code.MyMath.Point;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 27.06.2017.
 */
public class Bear extends AnimalSapiens {

    private static final char BEAR_FACE = 'B';
    private static final int BEAR_COLOR = 0x45161C;
    private static final int BEAR_PERIOD_OF_PREGNANT = 270;
    private static final int BEAR_MAX_ENERGY = 10_000;
    private static final int BEAR_MAX_SATIETY = 3000;
    private static final int BEAR_PROBABLY_TO_DIE = 700;
    private static final int BEAR_AREA_OF_VISIBLE = 6;
    private static final int BEAR_PRODUCT_AGE = 200;
    private static final int BEAR_DELTA_SATIETY = 20;

    public Bear(Point pos, boolean sex) {
        super(pos, BEAR_FACE, BEAR_COLOR, AnimalSapiens.CREATURE_ANIMAL_BEAR, BEAR_PERIOD_OF_PREGNANT, BEAR_MAX_ENERGY, BEAR_MAX_SATIETY, BEAR_PROBABLY_TO_DIE, sex, BEAR_AREA_OF_VISIBLE, BEAR_PRODUCT_AGE, 0, BEAR_DELTA_SATIETY); //TODO calories
    }

    @Override
    public boolean act() {
        return super.act();
    }

    @Override
    protected void sleep() {
        energy += BEAR_MAX_ENERGY / 2;
    }

    @Override
    protected void initRation() {
        ration[Plant.CREATURE_PLANT_STRAWBERRY] = true;
        ration[Plant.CREATURE_PLANT_APPLE] = true;
        ration[Plant.CREATURE_PLANT_BELLADONNA] = true;
        ration[Plant.CREATURE_PLANT_BLUEBERRY] = true;
        ration[Plant.CREATURE_PLANT_PEAR] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_FISH] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_RABBIT] = true;
    }

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityBear[Map.world.landscape[x][y]];
    }

    @Override
    public void eaten() {}

    @Override
    public boolean canBeEaten() {
        return true;
    }
}
