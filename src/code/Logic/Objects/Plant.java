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
    public static final int PLANT_CALORIES_KIWI       = 1220;
    public static final int PLANT_CALORIES_APPLE      = 1040;
    public static final int PLANT_CALORIES_BANANA     = 1780;
    public static final int PLANT_CALORIES_STRAWBERRY = 660;
    public static final int PLANT_CALORIES_PEAR       = 1140;
    public static final int PLANT_CALORIES_LEMON      = 580;
    public static final int PLANT_CALORIES_ORANGE     = 940;
    public static final int PLANT_CALORIES_BLUEBERRY  = 880;
    public static final int PLANT_CALORIES_COCONUT    = 4540;
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
    public static final int PERIOD_OF_PREGNANCY_APPLE = 60;
    public static final int PERIOD_OF_PREGNANCY_BANANA = 75;
    public static final int PERIOD_OF_PREGNANCY_STRAWBERRY = 32;
    public static final int PERIOD_OF_PREGNANCY_PEAR = 75;
    public static final int PERIOD_OF_PREGNANCY_LEMON = 50;
    public static final int PERIOD_OF_PREGNANCY_ORANGE = 125;
    public static final int PERIOD_OF_PREGNANCY_BLUEBERRY = 87;
    public static final int PERIOD_OF_PREGNANCY_COCONUT = 150;
    public static final int PERIOD_OF_PREGNANCY_BELLADONNA = 30;

    public static final int PROBABLY_DIE = 600;
    public static final int PROBABLY_TO_REPRODUCT = 65;
    public static final int PRODUCT_OVER = 10;

    private static final char PRODUCT_FACE = 't';
    private static final char NON_PRODUCT_FACE = 'T';

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
            return death();
        }
        reproductFunction();
        if (product) {
            NORMAL_FACE = PRODUCT_FACE;
            color = productColor;
        } else {
            NORMAL_FACE = NON_PRODUCT_FACE;
            color = 0;
        }
        return ok();
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

    @Override
    public int getPass(int x, int y) {return 0;}
}
