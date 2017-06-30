package code.GUI.World;

import code.GUI.Map.Map;
import code.Logic.Abstract.Creature;
import code.Logic.Objects.Plant;
import code.MyMath.Point;
import code.MyMath.xRandom;

import java.util.LinkedList;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class World {

    public int[][] landscape;
    public Creature[][] ref;

    public LinkedList<Creature> creatures;

    public LinkedList<Point> grass;
    public LinkedList<Point> waterHigh;
    public LinkedList<Point> groundHigh;

    public World() {
        landscape = new int[Map.MAP_HIGHT][Map.MAP_WIDTH];
        creatures = new LinkedList();
        grass = new LinkedList();
        waterHigh = new LinkedList();
        groundHigh = new LinkedList();
    }

    public void initRef() {
        ref = new Creature[Map.MAP_HIGHT][Map.MAP_WIDTH];
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                ref[i][j] = null;
    }

    public void reset() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++) {
                landscape[i][j] = 0;
                ref = null;
            }
        creatures.clear();
        grass.clear();
        waterHigh.clear();
        groundHigh.clear();
    }

    public void initGroundHigh() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (landscape[i][j] == Map.LANDSCAPE_GROUND_HIGH) groundHigh.add(new Point(i, j));
    }

    public Point getGroundHigh() {
        int index = xRandom.getIntInRange(0, groundHigh.size() - 1);
        return groundHigh.get(index);
    }

    public void replant(int cnt) {
        for (int i=1; i<=cnt; i++) addNewTree();
    }

    private void addNewTree() {
        int type = xRandom.getIntInRange(1, 10);
        if (type == 1)
            addPlant(Plant.PLANT_COLOR_KIWI, Plant.CREATURE_PLANT_KIWI, Plant.PERIOD_OF_PREGNANCY_KIWI, Plant.PLANT_CALORIES_KIWI);
        if (type == 2)
            addPlant(Plant.PLANT_COLOR_APPLE, Plant.CREATURE_PLANT_APPLE, Plant.PERIOD_OF_PREGNANCY_APPLE, Plant.PLANT_CALORIES_APPLE);
        if (type == 3)
            addPlant(Plant.PLANT_COLOR_BANANA, Plant.CREATURE_PLANT_BANANA, Plant.PERIOD_OF_PREGNANCY_BANANA, Plant.PLANT_CALORIES_BANANA);
        if (type == 4)
            addPlant(Plant.PLANT_COLOR_STRAWBERRY, Plant.CREATURE_PLANT_STRAWBERRY, Plant.PERIOD_OF_PREGNANCY_STRAWBERRY, Plant.PLANT_CALORIES_STRAWBERRY);
        if (type == 5)
            addPlant(Plant.PLANT_COLOR_PEAR, Plant.CREATURE_PLANT_PEAR, Plant.PERIOD_OF_PREGNANCY_PEAR, Plant.PLANT_CALORIES_PEAR);
        if (type == 6)
            addPlant(Plant.PLANT_COLOR_LEMON, Plant.CREATURE_PLANT_LEMON, Plant.PERIOD_OF_PREGNANCY_LEMON, Plant.PLANT_CALORIES_LEMON);
        if (type == 7)
            addPlant(Plant.PLANT_COLOR_ORANGE, Plant.CREATURE_PLANT_ORANGE, Plant.PERIOD_OF_PREGNANCY_ORANGE, Plant.PLANT_CALORIES_ORANGE);
        if (type == 8)
            addPlant(Plant.PLANT_COLOR_BLUEBERRY, Plant.CREATURE_PLANT_BLUEBERRY, Plant.PERIOD_OF_PREGNANCY_BLUEBERRY, Plant.PLANT_CALORIES_BLUEBERRY);
        if (type == 9)
            addPlant(Plant.PLANT_COLOR_COCONUT, Plant.CREATURE_PLANT_COCONUT, Plant.PERIOD_OF_PREGNANCY_COCONUT, Plant.PLANT_CALORIES_COCONUT);
        if (type == 10)
            addPlant(Plant.PLANT_COLOR_BELLADONNA, Plant.CREATURE_PLANT_BELLADONNA, Plant.PERIOD_OF_PREGNANCY_BELLADONNA, Plant.PLANT_CALORIES_ORANGE);
    }

    private void addPlant(int color, int type, int PERIOD_OF_PREGNANT, int calories) {
        Point pt = getEmptyGrass();
        Map.world.creatures.add(new Plant(pt, color, type, PERIOD_OF_PREGNANT, calories));
    }

    public void initGrass() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (landscape[i][j] == Map.LANDSCAPE_GRASS) addEmptyGrass(new code.MyMath.Point(i, j));
    }

    public void initWaterHigh() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (landscape[i][j] == Map.LANDSCAPE_WATER_HIGH) waterHigh.add(new Point(i, j));
    }

    public Point getWaterHigh() {
        return waterHigh.get(xRandom.getIntInRange(0, waterHigh.size() - 1));
    }

    public Point getEmptyGrass() {
        int index = xRandom.getIntInRange(0, grass.size() - 1);
        Point res = grass.get(index);
        while (!checkEmptyPosition(res)) {
            index = xRandom.getIntInRange(0, grass.size() - 1);
            res = grass.get(index);
        }
        return res;
    }

    public void addEmptyGrass(code.MyMath.Point pt) {
        grass.add(pt);
    }

    public boolean checkEmptyPosition(int x, int y) {
        return (ref[x][y] == null);
    }

    public boolean checkEmptyPosition(Point pt) {
        return checkEmptyPosition(pt.getX(), pt.getY());
    }
}
