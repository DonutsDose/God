package code.Logic.Objects;

import code.GUI.Control.ControlPanel;
import code.GUI.Formatter.Formatter;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Abstract.AnimalSapiens;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 27.06.2017.
 */
public class Bear extends AnimalSapiens {

    private static final char BEAR_FACE = 'B';
    private static final int BEAR_COLOR_MEN = 0x920066;
    private static final int BEAR_COLOR_WOMEN = 0x45160C;
    private static final int BEAR_COLOR_PREGNANT = 0xFF3333;
    private static final int BEAR_PERIOD_OF_PREGNANT = 330;
    private static final int BEAR_MAX_ENERGY = 10_000;
    private static final int BEAR_MAX_SATIETY = 5000;
    private static final int BEAR_PROBABLY_TO_DIE = 1000;
    private static final int BEAR_AREA_OF_VISIBLE = 3;
    private static final int BEAR_PRODUCT_AGE = 200;
    private static final int BEAR_DELTA_SATIETY = 25;

    public Bear(Point pos, boolean sex) {
        super(pos, BEAR_FACE, sex ? BEAR_COLOR_WOMEN : BEAR_COLOR_MEN, AnimalSapiens.CREATURE_ANIMAL_BEAR, BEAR_PERIOD_OF_PREGNANT, BEAR_MAX_ENERGY, BEAR_MAX_SATIETY, BEAR_PROBABLY_TO_DIE, sex, BEAR_AREA_OF_VISIBLE, BEAR_PRODUCT_AGE, 0, BEAR_DELTA_SATIETY); //TODO calories
    }

    @Override
    public boolean act() {
        if (!super.act()) return death();
        if (pregnant) color = BEAR_COLOR_PREGNANT; else color = sex ? BEAR_COLOR_WOMEN : BEAR_COLOR_MEN;
        return ok();
    }

    @Override
    public String getInformation() {
        return String.format("<html>Type: Bear<br>Sex: %s<br>Age: %s<br>Energy: %s %%<br>Satiety: %s %%<br>Pregnant: %s<br>Will be able to reproduct after %s</html>", sex ? "man" : "woman", Formatter.formatDate(age), xMath.percent(MAX_ENERGY, energy), xMath.percent(MAX_SATIETY, satiety), pregnant, readyToReproduct);
    }

    @Override
    protected void sleep() {
        energy += BEAR_MAX_ENERGY / 5;
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

    @Override
    public void event(String msg) {
        if (ControlPanel.isOpenBearEvent) MainPanel.eventPanel.update("<BEAR> " + msg);
    }
}
