package code.Logic.Objects;

import code.GUI.Control.ControlPanel;
import code.GUI.Formatter.Formatter;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Abstract.AnimalSapiens;
import code.MyMath.Point;
import code.MyMath.xMath;

/**
 * Created by DonutsDose-PC on 01.07.2017.
 */
public class Tiger extends AnimalSapiens {

    private static final char FACE = 'G';
    private static final int COLOR_MEN = 0xEC6A13;
    private static final int COLOR_WOMEN = 0x925126;
    private static final int COLOR_PREGNANT = 0xFF3333;
    private static final int PERIOD_OF_PREGNANT = 340;
    private static final int MAX_ENERGY = 8_300;
    private static final int MAX_SATIETY = 7_000;
    private static final int PROBABLY_TO_DIE = 1100;
    private static final int AREA_OF_VISIBLE = 7;
    private static final int PRODUCT_AGE = 300;
    private static final int DELTA_SATIETY = 20;
    private static final int CALORIES = 12630;
    private static final int E_BATTLE = 5;
    private static final int A_BATTLE = 2;

    public Tiger(Point pos, boolean sex) {
        super(pos, FACE, sex ? COLOR_WOMEN : COLOR_MEN, AnimalSapiens.CREATURE_ANIMAL_TIGER, PERIOD_OF_PREGNANT, MAX_ENERGY, MAX_SATIETY, PROBABLY_TO_DIE, sex, AREA_OF_VISIBLE, PRODUCT_AGE, CALORIES, DELTA_SATIETY);
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
        return String.format("<html>Type: Tiger<br>Sex: %s<br>Age: %s<br>Energy: %s %%<br>Satiety: %s %%<br>Pregnant: %s<br>Will be able to reproduct after %s</html>", sex ? "man" : "woman", Formatter.formatDate(age), xMath.percent(MAX_ENERGY, energy), xMath.percent(MAX_SATIETY, satiety), pregnant, readyToReproduct);
    }

    @Override
    protected void sleep() {
        energy = Math.min(energy + MAX_ENERGY / 3, MAX_ENERGY);
    }

    @Override
    protected void initRation() {
        ration[Plant.CREATURE_PLANT_STRAWBERRY] = true;
        ration[Plant.CREATURE_PLANT_APPLE] = true;
        ration[Plant.CREATURE_PLANT_BELLADONNA] = true;
        ration[Plant.CREATURE_PLANT_BLUEBERRY] = true;
        ration[Plant.CREATURE_PLANT_PEAR] = true;
        ration[Plant.CREATURE_PLANT_KIWI] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_FISH] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_RABBIT] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_WOLF] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_BEAR] = true;
    }

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityTiger[Map.world.landscape[x][y]];
    }

    @Override
    public void eaten() {}

    @Override
    public boolean canBeEaten() {
        return true;
    }

    @Override
    public void event(String msg) {
        if (ControlPanel.isOpenTigerEvent) MainPanel.eventPanel.update("<TIGER> " + msg);
    }
}
