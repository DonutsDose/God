package code.MyMath;

import code.GUI.Map.Map;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */
public class xMath {

    public static boolean inMap(int x, int y) {
        return (x >= 0 && y >= 0 && x < Map.MAP_HIGHT && y < Map.MAP_WIDTH);
    }


}
