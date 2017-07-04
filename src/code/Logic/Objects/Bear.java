package code.Logic.Objects;

import code.GUI.Control.ControlPanel;
import code.GUI.Formatter.Formatter;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.GUI.World.World;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Abstract.AnimalSapiens;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 27.06.2017.
 */

public class Bear extends AnimalSapiens {

    private static final char FACE = 'B';
    private static final int COLOR_MEN = 0x920066;
    private static final int COLOR_WOMEN = 0x45160C;
    private static final int COLOR_PREGNANT = 0xFF3333;
    private static final int PERIOD_OF_PREGNANT = 330;
    private static final int MAX_ENERGY = 10_000;
    private static final int MAX_SATIETY = 9_000;
    private static final int PROBABLY_TO_DIE = 1000;
    private static final int AREA_OF_VISIBLE = 5;
    private static final int PRODUCT_AGE = 200;
    private static final int DELTA_SATIETY = 25;
    private static final int CALORIES = 25630;
    private static final int E_BATTLE = 3;
    private static final int A_BATTLE = 2;

    public Bear(Point pos, boolean sex) {
        super(pos, FACE, sex ? COLOR_WOMEN : COLOR_MEN, AnimalSapiens.CREATURE_ANIMAL_BEAR, PERIOD_OF_PREGNANT, MAX_ENERGY, MAX_SATIETY, PROBABLY_TO_DIE, sex, AREA_OF_VISIBLE, PRODUCT_AGE, CALORIES, DELTA_SATIETY);
    }

    @Override
    public boolean act() {
        if (!super.act()) return death();
        if (pregnant) color = COLOR_PREGNANT; else color = sex ? COLOR_WOMEN : COLOR_MEN;
        return ok();
    }

    @Override
    public int getBattlePoints() {
        return (E_BATTLE * energy + A_BATTLE * age);
    }

    @Override
    public String getInformation() {
        return String.format("<html>Type: Bear<br>Sex: %s<br>Age: %s<br>Energy: %s %%<br>Satiety: %s %%<br>Pregnant: %s<br>Will be able to reproduct after %s</html>", sex ? "man" : "woman", Formatter.formatDate(age), xMath.percent(MAX_ENERGY, energy), xMath.percent(MAX_SATIETY, satiety), pregnant, readyToReproduct);
    }

    @Override
    protected void sleep() {
        energy = Math.min(energy + MAX_ENERGY / 5, MAX_ENERGY);
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
        ration[AnimalSapiens.CREATURE_ANIMAL_WOLF] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_TIGER] = true;
    }

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityBear[World.landscape[x][y]];
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
