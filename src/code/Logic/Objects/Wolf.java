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

/**
 * Created by DonutsDose-PC on 30.06.2017.
 */
public class Wolf extends AnimalSapiens {

    private static final char FACE = 'F';
    private static final int COLOR_MEN = 0x8254BB;
    private static final int COLOR_WOMEN = 0x8FA0BD;
    private static final int COLOR_PREGNANT = 0xFF3333;
    private static final int PERIOD_OF_PREGNANT = 270;
    private static final int MAX_ENERGY = 4_500;
    private static final int MAX_SATIETY = 6000;
    private static final int PROBABLY_TO_DIE = 1000;
    private static final int AREA_OF_VISIBLE = 7;
    private static final int PRODUCT_AGE = 134;
    private static final int DELTA_SATIETY = 20;
    private static final int CALORIES = 4780;
    private static final int E_BATTLE = 2;
    private static final int A_BATTLE = 6;

    public Wolf(Point pos, boolean sex) {
        super(pos, FACE, sex ? COLOR_WOMEN : COLOR_MEN, AnimalSapiens.CREATURE_ANIMAL_WOLF, PERIOD_OF_PREGNANT, MAX_ENERGY, MAX_SATIETY, PROBABLY_TO_DIE, sex, AREA_OF_VISIBLE, PRODUCT_AGE, CALORIES, DELTA_SATIETY);
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
        return String.format("<html>Type: Wolf<br>Sex: %s<br>Age: %s<br>Energy: %s %%<br>Satiety: %s %%<br>Pregnant: %s<br>Will be able to reproduct after %s</html>", sex ? "man" : "woman", Formatter.formatDate(age), xMath.percent(MAX_ENERGY, energy), xMath.percent(MAX_SATIETY, satiety), pregnant, readyToReproduct);
    }

    @Override
    public void eaten() {}

    @Override
    public boolean canBeEaten() {return true;}

    @Override
    public int getPass(int x, int y) {
        return Map.passabilityWolf[World.landscape[x][y]];
    }

    @Override
    protected void sleep() {
        energy = MAX_ENERGY;
    }

    @Override
    public void event(String msg) {
        if (ControlPanel.isOpenWolfEvent) MainPanel.eventPanel.update("<WOLF> " + msg);
    }

    @Override
    protected void initRation() {
        ration[Plant.CREATURE_PLANT_BLUEBERRY] = true;
        ration[Plant.CREATURE_PLANT_STRAWBERRY] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_RABBIT] = true;
        ration[AnimalPrimitive.CREATURE_ANIMAL_FISH] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_BEAR] = true;
        ration[AnimalSapiens.CREATURE_ANIMAL_TIGER] = true;
    }
}
