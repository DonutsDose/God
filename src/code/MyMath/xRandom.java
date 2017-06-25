package code.MyMath;

import java.util.Random;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class xRandom {

    private static Random random = new Random();

    public static int getIntInRange(int min, int max) {
        if (min > max) min = max;
        return (min + random.nextInt(max - min + 1));
    }

    public static boolean getBoolean(int probably) {
        probably = Math.min(Math.max(probably, 1), 100);
        return (random.nextInt(100) + 1 <= probably);
    }
}
