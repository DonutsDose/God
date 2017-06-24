package code.Logic.Objects;

import code.Logic.Abstract.Creature;
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
    public static final int PLANT_CALORIES_KIWI       = 61;
    public static final int PLANT_CALORIES_APPLE      = 52;
    public static final int PLANT_CALORIES_BANANA     = 89;
    public static final int PLANT_CALORIES_STRAWBERRY = 33;
    public static final int PLANT_CALORIES_PEAR       = 57;
    public static final int PLANT_CALORIES_LEMON      = 29;
    public static final int PLANT_CALORIES_ORANGE     = 47;
    public static final int PLANT_CALORIES_BLUEBERRY  = 44;
    public static final int PLANT_CALORIES_COCONUT    = 354;
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
    //min max reproduct
    public static final int MIN_REPRODUCT_KIWI = 4,         MAX_REPRODUCT_KIWI = 6;
    public static final int MIN_REPRODUCT_APPLE = 5,        MAX_REPRODUCT_APPLE = 6;
    public static final int MIN_REPRODUCT_BANANA = 2,       MAX_REPRODUCT_BANANA = 4;
    public static final int MIN_REPRODUCT_STRAWBERRY = 7,   MAX_REPRODUCT_STRAWBERRY = 8;
    public static final int MIN_REPRODUCT_PEAR = 5,         MAX_REPRODUCT_PEAR = 6;
    public static final int MIN_REPRODUCT_LEMON = 2,        MAX_REPRODUCT_LEMON = 3;
    public static final int MIN_REPRODUCT_ORANGE = 5,       MAX_REPRODUCT_ORANGE = 7;
    public static final int MIN_REPRODUCT_BLUEBERRY = 8,    MAX_REPRODUCT_BLUEBERRY = 8;
    public static final int MIN_REPRODUCT_COCONUT = 0,      MAX_REPRODUCT_COCONUT = 3;
    public static final int MIN_REPRODUCT_BELLADONNA = 4,   MAX_REPRODUCT_BELLADONNA = 5;
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

    public static final int PLANT_DELTA_OVER_PROBABLY = 400;
    public static final int PLANT_PREGNANT_PROBABLY = 45;
    public static final int PRODUCT_OVER = 10;

    private int MIN_REPRODUCT, MAX_REPRODUCT, calories, productOver = 0;
    public boolean product = false;

    public Plant(int x, int y, int color, int type, int PERIOD_OF_PREGNANT, int MIN_REPRODUCT, int MAX_REPRODUCT, int calories) {
        super(x, y, 'T', color, type, PERIOD_OF_PREGNANT);
        this.MIN_REPRODUCT = MIN_REPRODUCT;
        this.MAX_REPRODUCT = MAX_REPRODUCT;
        this.calories = calories;
    }

    @Override
    public boolean act() {
        age++;
        if (xRandom.getBoolean(age / PLANT_DELTA_OVER_PROBABLY)) return false;
        if (productOver == 0) product = false; else productOver--;
        if (!pregnant) pregnant = xRandom.getBoolean(PLANT_PREGNANT_PROBABLY);
        if (pregnant) {
            pregnancy++;
            if (pregnancy == PERIOD_OF_PREGNANT) {
                product = true;
                productOver = PRODUCT_OVER;
                pregnancy = 0;
                pregnant = false;
            }
        }
        return true;
    }
}
