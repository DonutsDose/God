package code.GUI.Map;

import code.GUI.World.World;

/**
 * Created by DonutsDose-PC on 22.06.2017.
 */

public class MapRender {

    public final static int COMMON_COLOR_EMPTY          = 0xF0F0F0;
    private final static int LANDSCAPE_COLOR_WATER_LOW   = 0x60A4B1;
    private final static int LANDSCAPE_COLOR_WATER_HIGH  = 0x6CB8C6;
    private final static int LANDSCAPE_COLOR_GROUND_LOW  = 0xDDB985;
    private final static int LANDSCAPE_COLOR_GROUND_HIGH = 0xD1AF7D;
    private final static int LANDSCAPE_COLOR_GRASS       = 0xB3D77E;
    private final static int LANDSCAPE_COLOR_FRESH_WATER = 0x6699FF;

    public static int[][] background = new int[Map.MAP_HIGHT][Map.MAP_WIDTH];
    public static int[][] foreground = new int[Map.MAP_HIGHT][Map.MAP_WIDTH];
    public static char[][] value = new char[Map.MAP_HIGHT][Map.MAP_WIDTH];

    public static void update() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++) {
                foreground[i][j] = 0;
                value[i][j] = ' ';
            }
        updateValue();
        updateForeground();
    }

    public static void setBackground() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                background[i][j] = getBackgroundColor(World.landscape[i][j]);
    }

    private static void updateForeground() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (World.ref[i][j] != null) foreground[i][j] = World.ref[i][j].color;
    }

    private static void updateValue() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (World.ref[i][j] != null) value[i][j] = World.ref[i][j].face;
    }

    private static int getBackgroundColor(int data) {
        if (data == Map.LANDSCAPE_GRASS) return LANDSCAPE_COLOR_GRASS;
        if (data == Map.LANDSCAPE_WATER_LOW) return LANDSCAPE_COLOR_WATER_LOW;
        if (data == Map.LANDSCAPE_WATER_HIGH) return LANDSCAPE_COLOR_WATER_HIGH;
        if (data == Map.LANDSCAPE_GROUND_LOW) return LANDSCAPE_COLOR_GROUND_LOW;
        if (data == Map.LANDSCAPE_GROUND_HIGH) return LANDSCAPE_COLOR_GROUND_HIGH;
        if (data == Map.LANDSCAPE_GRASS) return LANDSCAPE_COLOR_GRASS;
        if (data == Map.LANDSCAPE_FRESH_WATER) return LANDSCAPE_COLOR_FRESH_WATER;
        return COMMON_COLOR_EMPTY;
    }
}
