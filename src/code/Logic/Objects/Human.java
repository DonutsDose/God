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

public class Human extends AnimalSapiens {

    private static final char FACE = 'H';
    private static final int COLOR_MEN = 0x9B5F31;
    private static final int COLOR_WOMEN = 0x824312;
    private static final int COLOR_PREGNANT = 0xFF3333;
    private static final int PERIOD_OF_PREGNANT = 270;
    private static final int MAX_ENERGY = 3_300;
    private static final int MAX_SATIETY = 2_000;
    private static final int PROBABLY_TO_DIE = 4000;
    private static final int AREA_OF_VISIBLE = 8;
    private static final int PRODUCT_AGE = 270;
    private static final int DELTA_SATIETY = 10;
    private static final int CALORIES = 10630;
    private static final int E_BATTLE = 1;
    private static final int A_BATTLE = 4;

    public Human(Point pos, boolean sex) {
        super(pos, FACE, sex ? COLOR_WOMEN : COLOR_MEN, AnimalSapiens.CREATURE_ANIMAL_HUMAN, PERIOD_OF_PREGNANT, MAX_ENERGY, MAX_SATIETY, PROBABLY_TO_DIE, sex, AREA_OF_VISIBLE, PRODUCT_AGE, CALORIES, DELTA_SATIETY);
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
        return String.format("<html>Type: Human<br>Sex: %s<br>Age: %s<br>Energy: %s %%<br>Satiety: %s %%<br>Pregnant: %s<br>Will be able to reproduct after %s</html>", sex ? "man" : "woman", Formatter.formatDate(age), xMath.percent(MAX_ENERGY, energy), xMath.percent(MAX_SATIETY, satiety), pregnant, readyToReproduct);
    }

    @Override
    protected void sleep() {
        energy = MAX_ENERGY;
    }

    @Override
    protected void initRation() {
        for (int i=0; i<Map.MAX_CREATURE_TYPE; i++)
            ration[i] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_HUMAN] = false;
    }

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityHuman[Map.world.landscape[x][y]];
    }

    @Override
    public void eaten() {}

    @Override
    public boolean canBeEaten() {
        return true;
    }

    @Override
    public void event(String msg) {
        if (ControlPanel.isOpenHumanEvent) MainPanel.eventPanel.update("<HUMAN> " + msg);
    }
}
