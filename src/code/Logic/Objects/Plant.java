package code.Logic.Objects;

import code.GUI.Formatter.Formatter;
import code.GUI.Main.MainPanel;
import code.Logic.Abstract.Creature;
import code.Logic.Engine.Engine;
import code.MyMath.Point;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 22.06.2017.
 */

public class Plant extends Creature {

    //type
    public static final int CREATURE_PLANT_KIWI       = 1;
    public static final int CREATURE_PLANT_APPLE      = 2;
    public static final int CREATURE_PLANT_BANANA     = 3;
    public static final int CREATURE_PLANT_STRAWBERRY = 4;
    public static final int CREATURE_PLANT_PEAR       = 5;
    public static final int CREATURE_PLANT_LEMON      = 6;
    public static final int CREATURE_PLANT_ORANGE     = 7;
    public static final int CREATURE_PLANT_BLUEBERRY  = 8;
    public static final int CREATURE_PLANT_COCONUT    = 9;
    public static final int CREATURE_PLANT_BELLADONNA = 10;
    //calories
    public static final int PLANT_CALORIES_KIWI       = 610;
    public static final int PLANT_CALORIES_APPLE      = 520;
    public static final int PLANT_CALORIES_BANANA     = 890;
    public static final int PLANT_CALORIES_STRAWBERRY = 330;
    public static final int PLANT_CALORIES_PEAR       = 570;
    public static final int PLANT_CALORIES_LEMON      = 290;
    public static final int PLANT_CALORIES_ORANGE     = 470;
    public static final int PLANT_CALORIES_BLUEBERRY  = 440;
    public static final int PLANT_CALORIES_COCONUT    = 3540;
    public static final int PLANT_CALORIES_BELLADONNA = 0;
    //color
    public static final int PLANT_COLOR_KIWI          = 0x999900;
    public static final int PLANT_COLOR_APPLE         = 0xFF3333;
    public static final int PLANT_COLOR_BANANA        = 0xFFFF77;
    public static final int PLANT_COLOR_STRAWBERRY    = 0xFF0033;
    public static final int PLANT_COLOR_PEAR          = 0x999900;
    public static final int PLANT_COLOR_LEMON         = 0xFFFF00;
    public static final int PLANT_COLOR_ORANGE        = 0xFFCC00;
    public static final int PLANT_COLOR_BLUEBERRY     = 0x993399;
    public static final int PLANT_COLOR_COCONUT       = 0x663300;
    public static final int PLANT_COLOR_BELLADONNA    = 0x330000;
    //period of pregnancy
    public static final int PERIOD_OF_PREGNANCY_KIWI  = 34;
    public static final int PERIOD_OF_PREGNANCY_APPLE = 133;
    public static final int PERIOD_OF_PREGNANCY_BANANA = 150;
    public static final int PERIOD_OF_PREGNANCY_STRAWBERRY = 68;
    public static final int PERIOD_OF_PREGNANCY_PEAR = 130;
    public static final int PERIOD_OF_PREGNANCY_LEMON = 100;
    public static final int PERIOD_OF_PREGNANCY_ORANGE = 250;
    public static final int PERIOD_OF_PREGNANCY_BLUEBERRY = 345;
    public static final int PERIOD_OF_PREGNANCY_COCONUT = 150;
    public static final int PERIOD_OF_PREGNANCY_BELLADONNA = 60;

    public static final int PROBABLY_DIE = 600;
    public static final int PROBABLY_TO_REPRODUCT = 65;
    public static final int PRODUCT_OVER = 10;

    private int productOver = 0, productColor;
    public boolean product = false;

    public Plant(Point pos, int color, int type, int PERIOD_OF_PREGNANT, int calories) {
        super(pos, 'T', 0, type, PERIOD_OF_PREGNANT, PROBABLY_DIE, false, calories);
        productColor = color;
        this.PERIOD_OF_PREGNANT = PERIOD_OF_PREGNANT;
        readyToReproduct = PERIOD_OF_PREGNANT;
    }

    @Override
    public boolean act() {
        if (!super.act()) {
            Engine.replantTree++;
            MainPanel.map.world.addEmptyGrass(pos);
            return false;
        }
        reproductFunction();
        return true;
    }

    protected void reproductFunction() {
        if (productOver == 0) product = false; else productOver--;
        if (readyToReproduct == 0) {
            if (xRandom.getBoolean(PROBABLY_TO_REPRODUCT)) {
                product = true;
                productOver = PRODUCT_OVER;
            }
            readyToReproduct = PERIOD_OF_PREGNANT;
        } else readyToReproduct--;
        if (product) {
            face = 't';
            color = productColor;
        } else {
            face = 'T';
            color = 0;
        }
    }

    @Override
    public String getInformation() {
        return String.format("<html>Type: Tree %s<br>Age: %s<br>Product: %s<br>Product over after %s days<br>Next products: %s</html>", getStringType(), Formatter.formatDate(age), product, productOver, readyToReproduct);
    }

    private String getStringType() {
        switch (type) {
            case CREATURE_PLANT_KIWI : return "Kiwi";
            case CREATURE_PLANT_APPLE : return "Apple";
            case CREATURE_PLANT_BANANA : return "Banana";
            case CREATURE_PLANT_STRAWBERRY : return "Strawberry";
            case CREATURE_PLANT_PEAR : return "Pear";
            case CREATURE_PLANT_LEMON : return "Lemon";
            case CREATURE_PLANT_ORANGE : return "Orange";
            case CREATURE_PLANT_BLUEBERRY : return "Blueberry";
            case CREATURE_PLANT_COCONUT : return "Coconut";
            case CREATURE_PLANT_BELLADONNA : return "Belladonna";
        }
        return "-";
    }

    @Override
    public void eaten() {
        product = false;
        productOver = 0;
    }

    @Override
    public boolean canBeEaten() {
        return (product);
    }
}
