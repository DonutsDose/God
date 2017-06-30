package code.MyMath;

import code.GUI.Map.Map;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */
public class xMath {

    public static boolean inMap(int x, int y) {
        return (x >= 0 && y >= 0 && x < Map.MAP_HIGHT && y < Map.MAP_WIDTH);
    }

    public static Point pointMinDist(Point st, Point a, Point b) {
        int distA = dist(st, a);
        int distB = dist(st, b);
        if (distA < distB) return a; else return b;
    }

    public static int dist(Point a, Point b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY()  - b.getY());
    }

    public static int maxDist(Point a, Point b) {
        return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
    }

    public static float percent(int max, int value) {
        return ((float)value * 100f / max);
    }

}
