package code.GUI.World;

import code.GUI.Map.Map;
import code.Logic.Objects.Plant;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class WorldCreator {

    private static World world;

    private static final int LEVEL_OF_SMOOTHING = 1;
    private static final int SQUARE_OF_GROUND = 4500;
    private static final int SQUARE_OF_GRASS = 2000;
    private static final int DELTA_GROUND_OVER_PROBABLY = 5;
    private static final int DELTA_GRASS_OVER_PROBABLY = 15;
    private static final int GROUND_WIDTH = 4;
    private static final int WATER_WIDTH = 4;
    private static final int PROBABLY_OF_CREATING_TREE = 60;
    private static final int KIWI_COUNT = 6;
    private static final int APPLE_COUNT = 10;
    private static final int BANANA_COUNT = 7;
    private static final int STRAWBERRY_COUNT = 5;
    private static final int PEAR_COUNT = 7;
    private static final int LEMON_COUNT = 3;
    private static final int ORANGE_COUNT = 7;
    private static final int BLUEBERRY_COUNT = 8;
    private static final int COCONUT_COUNT = 6;
    private static final int BELLADONNA_COUNT = 6;

    public static World createWorld() {
        world = new World();
        addWater();
        addGroundHigh();
        smoothing();
        addGroundLow();
        addGrass();
        addWaterHigh();
        addFreshWater();
        world.initEmptyGrass();
        world.initWaterHigh();
        world.initGroundHigh();
        addPlants();
        return world;
    }

    private static void addPlants() {
        addPlant(KIWI_COUNT, Plant.PLANT_COLOR_KIWI, Plant.CREATURE_PLANT_KIWI, Plant.PERIOD_OF_PREGNANCY_KIWI, Plant.MIN_REPRODUCT_KIWI, Plant.MAX_REPRODUCT_KIWI, Plant.PLANT_CALORIES_KIWI);
        addPlant(APPLE_COUNT, Plant.PLANT_COLOR_APPLE, Plant.CREATURE_PLANT_APPLE, Plant.PERIOD_OF_PREGNANCY_APPLE, Plant.MIN_REPRODUCT_APPLE, Plant.MAX_REPRODUCT_APPLE, Plant.PLANT_CALORIES_APPLE);
        addPlant(BANANA_COUNT, Plant.PLANT_COLOR_BANANA, Plant.CREATURE_PLANT_BANANA, Plant.PERIOD_OF_PREGNANCY_BANANA, Plant.MIN_REPRODUCT_BANANA, Plant.MAX_REPRODUCT_BANANA, Plant.PLANT_CALORIES_BANANA);
        addPlant(STRAWBERRY_COUNT, Plant.PLANT_COLOR_STRAWBERRY, Plant.CREATURE_PLANT_STRAWBERRY, Plant.PERIOD_OF_PREGNANCY_STRAWBERRY, Plant.MIN_REPRODUCT_STRAWBERRY, Plant.MAX_REPRODUCT_STRAWBERRY, Plant.PLANT_CALORIES_STRAWBERRY);
        addPlant(PEAR_COUNT, Plant.PLANT_COLOR_PEAR, Plant.CREATURE_PLANT_PEAR, Plant.PERIOD_OF_PREGNANCY_PEAR, Plant.MIN_REPRODUCT_PEAR, Plant.MAX_REPRODUCT_PEAR, Plant.PLANT_CALORIES_PEAR);
        addPlant(LEMON_COUNT, Plant.PLANT_COLOR_LEMON, Plant.CREATURE_PLANT_LEMON, Plant.PERIOD_OF_PREGNANCY_LEMON, Plant.MIN_REPRODUCT_LEMON, Plant.MAX_REPRODUCT_LEMON, Plant.PLANT_CALORIES_LEMON);
        addPlant(ORANGE_COUNT, Plant.PLANT_COLOR_ORANGE, Plant.CREATURE_PLANT_ORANGE, Plant.PERIOD_OF_PREGNANCY_ORANGE, Plant.MIN_REPRODUCT_ORANGE, Plant.MAX_REPRODUCT_ORANGE, Plant.PLANT_CALORIES_ORANGE);
        addPlant(BLUEBERRY_COUNT, Plant.PLANT_COLOR_BLUEBERRY, Plant.CREATURE_PLANT_BLUEBERRY, Plant.PERIOD_OF_PREGNANCY_BLUEBERRY, Plant.MIN_REPRODUCT_BLUEBERRY, Plant.MAX_REPRODUCT_ORANGE, Plant.PLANT_CALORIES_BLUEBERRY);
        addPlant(COCONUT_COUNT, Plant.PLANT_COLOR_COCONUT, Plant.CREATURE_PLANT_COCONUT, Plant.PERIOD_OF_PREGNANCY_COCONUT, Plant.MIN_REPRODUCT_COCONUT, Plant.MAX_REPRODUCT_COCONUT, Plant.PLANT_CALORIES_COCONUT);
        addPlant(BELLADONNA_COUNT, Plant.PLANT_COLOR_BELLADONNA, Plant.CREATURE_PLANT_BELLADONNA, Plant.PERIOD_OF_PREGNANCY_BELLADONNA, Plant.MIN_REPRODUCT_BELLADONNA, Plant.MAX_REPRODUCT_BELLADONNA, Plant.PLANT_CALORIES_ORANGE);
    }

    private static void addPlant(int count, int color, int type, int PERIOD_OF_PREGNANT, int MIN_REPRODUCT, int MAX_REPRODUCT, int calories) {
        for (int i=1; i<=count; i++)
            if (xRandom.getBoolean(PROBABLY_OF_CREATING_TREE)) {
                Point pt = world.getEmptyGrass();
                world.plants.add(new Plant(pt.getX(), pt.getY(), color, type, PERIOD_OF_PREGNANT, MIN_REPRODUCT, MAX_REPRODUCT, calories));
            }
    }

    private static void addFreshWater() {
        boolean[][] used = new boolean[Map.MAP_HIGHT][Map.MAP_WIDTH];
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                used[i][j] = false;
        Queue<Point> q = new ArrayDeque();
        for (int i=0; i<Map.MAP_HIGHT; i++) {
            q.add(new Point(i, 0));
            q.add(new Point(i, Map.MAP_WIDTH - 1));
        }
        for (int i=1; i<Map.MAP_WIDTH - 1; i++) {
            q.add(new Point(0, i));
            q.add(new Point(Map.MAP_HIGHT - 1, i));
        }
        while (!q.isEmpty()) {
            int x = q.peek().getX();
            int y = q.peek().getY();
            if (!used[x][y] && (world.landscape[x][y] == Map.LANDSCAPE_WATER_HIGH || world.landscape[x][y] == Map.LANDSCAPE_WATER_LOW)) {
                used[x][y] = true;
                if (xMath.inMap(x - 1, y)) q.add(new Point(x - 1, y));
                if (xMath.inMap(x + 1, y)) q.add(new Point(x + 1, y));
                if (xMath.inMap(x, y - 1)) q.add(new Point(x, y - 1));
                if (xMath.inMap(x, y + 1)) q.add(new Point(x, y + 1));
                if (xMath.inMap(x - 1, y - 1)) q.add(new Point(x - 1, y - 1));
                if (xMath.inMap(x + 1, y + 1)) q.add(new Point(x + 1, y + 1));
                if (xMath.inMap(x + 1, y - 1)) q.add(new Point(x + 1, y - 1));
                if (xMath.inMap(x - 1, y + 1)) q.add(new Point(x - 1, y + 1));
            }
            q.poll();
        }
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (!used[i][j] && (world.landscape[i][j] == Map.LANDSCAPE_WATER_HIGH || world.landscape[i][j] == Map.LANDSCAPE_WATER_LOW)) {
                    world.landscape[i][j] = Map.LANDSCAPE_FRESH_WATER;
                }
    }

    private static void addWaterHigh() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (world.landscape[i][j] == Map.LANDSCAPE_WATER_LOW &&
                        (aroundValue(i, j, Map.LANDSCAPE_GROUND_HIGH, WATER_WIDTH) || aroundValue(i, j, Map.LANDSCAPE_GROUND_LOW, WATER_WIDTH)))
                    world.landscape[i][j] = Map.LANDSCAPE_WATER_HIGH;
    }

    private static void addGroundLow() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (world.landscape[i][j] == Map.LANDSCAPE_GROUND_HIGH && aroundValue(i, j, Map.LANDSCAPE_WATER_LOW, GROUND_WIDTH))
                    world.landscape[i][j] = Map.LANDSCAPE_GROUND_LOW;
    }

    private static boolean aroundValue(int x, int y, int data, int width) {
        for (int i=x-2 * width; i<=x+2 * width; i++)
            for (int j=y-2 * width; j<=y+2 * width; j++)
                if (xMath.inMap(i, j) && (world.landscape[i][j] == data) && (Math.abs(i - x) + Math.abs(j - y)) < width) return true;
        return false;
    }

    private static void smoothing() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (getAroundSimilar(i, j) <= LEVEL_OF_SMOOTHING)
                    world.landscape[i][j] = getValueToChange(i, j);
    }

    private static int getValueToChange(int x, int y) {
        if (xMath.inMap(x - 1, y) && (world.landscape[x - 1][y] != world.landscape[x][y])) return world.landscape[x - 1][y];
        if (xMath.inMap(x + 1, y) && (world.landscape[x + 1][y] != world.landscape[x][y])) return world.landscape[x + 1][y];
        if (xMath.inMap(x, y - 1) && (world.landscape[x][y - 1] != world.landscape[x][y])) return world.landscape[x][y - 1];
        if (xMath.inMap(x, y + 1) && (world.landscape[x][y + 1] != world.landscape[x][y])) return world.landscape[x][y + 1];
        return world.landscape[x][y];
    }

    private static int getAroundSimilar(int x, int y) {
        int res = 0;
        for (int i=x-1; i<=x+1; i++)
            for (int j=y-1; j<=y+1; j++)
                if (xMath.inMap(i, j)) {
                    if (world.landscape[x][y] == world.landscape[i][j]) res++;
                } else res++;
        return res;
    }

    private static void addWater() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                world.landscape[i][j] = Map.LANDSCAPE_WATER_LOW;
    }

    private static void addGrass() {
        int cnt = 0;
        while (cnt < SQUARE_OF_GRASS) {
            int x = xRandom.getIntInRange(0, Map.MAP_HIGHT - 1);
            int y = xRandom.getIntInRange(0, Map.MAP_WIDTH - 1);
            cnt += makeIslandGrass(x, y, SQUARE_OF_GRASS - cnt);
        }
    }

    private static int makeIslandGrass(int xSt, int ySt, int need) {
        int res = 0;
        Queue<Point> q = new ArrayDeque();
        q.add(new Point(xSt, ySt));
        while (!q.isEmpty() && res < need) {
            int x = q.peek().getX();
            int y = q.peek().getY();
            if (world.landscape[x][y] == Map.LANDSCAPE_GROUND_HIGH || world.landscape[x][y] == Map.LANDSCAPE_GROUND_LOW) {
                res++;
                world.landscape[x][y] = Map.LANDSCAPE_GRASS;
                if (!xRandom.getBoolean((Math.abs(xSt - x) + Math.abs(ySt - y)) * DELTA_GRASS_OVER_PROBABLY)) {
                    if (xMath.inMap(x + 1, y)) q.add(new Point(x + 1, y));
                    if (xMath.inMap(x - 1, y)) q.add(new Point(x - 1, y));
                    if (xMath.inMap(x, y - 1)) q.add(new Point(x, y - 1));
                    if (xMath.inMap(x, y + 1)) q.add(new Point(x, y + 1));
                }
            }
            q.poll();
        }
        return res;
    }

    private static void addGroundHigh() {
        int cnt = 0;
        while (cnt < SQUARE_OF_GROUND) {
            int x = xRandom.getIntInRange(0, Map.MAP_HIGHT - 1);
            int y = xRandom.getIntInRange(0, Map.MAP_WIDTH - 1);
            cnt += makeIslandGroundHigh(x, y, SQUARE_OF_GROUND - cnt);
        }
    }

    private static int makeIslandGroundHigh(int xSt, int ySt, int need) {
        int res = 0;
        Queue<Point> q = new ArrayDeque();
        q.add(new Point(xSt, ySt));
        while (!q.isEmpty() && res < need) {
            int x = q.peek().getX();
            int y = q.peek().getY();
            if (world.landscape[x][y] == Map.LANDSCAPE_WATER_LOW) {
                res++;
                world.landscape[x][y] = Map.LANDSCAPE_GROUND_HIGH;
                if (!xRandom.getBoolean((Math.abs(xSt - x) + Math.abs(ySt - y)) * DELTA_GROUND_OVER_PROBABLY)) {
                    if (xMath.inMap(x + 1, y)) q.add(new Point(x + 1, y));
                    if (xMath.inMap(x - 1, y)) q.add(new Point(x - 1, y));
                    if (xMath.inMap(x, y - 1)) q.add(new Point(x, y - 1));
                    if (xMath.inMap(x, y + 1)) q.add(new Point(x, y + 1));
                }
            }
            q.poll();
        }
        return res;
    }
}
