package code.GUI.World;

import code.GUI.Map.Map;
import code.Logic.Abstract.Animal;
import code.Logic.Objects.Plant;
import code.MyMath.Point;
import code.MyMath.xRandom;

import java.util.LinkedList;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class World {

    public int[][] landscape = new int[Map.MAP_HIGHT][Map.MAP_WIDTH];

    public LinkedList<Plant> plants = new LinkedList();

    public LinkedList<Animal> animals = new LinkedList();

    private LinkedList<Point> grass = new LinkedList();
    private LinkedList<Point> waterHigh = new LinkedList();
    private LinkedList<Point> groundHigh = new LinkedList();

    public void initGroundHigh() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (landscape[i][j] == Map.LANDSCAPE_GROUND_HIGH) groundHigh.add(new Point(i, j));
    }

    public Point getGroundOut() {
        int index = xRandom.getIntInRange(0, groundHigh.size() - 1);
        return groundHigh.get(index);
    }

    private boolean checkGround(int x, int y) {
        if (landscape[x][y] == Map.LANDSCAPE_GROUND_HIGH || landscape[x][y] == Map.LANDSCAPE_GROUND_LOW ||
                landscape[x][y] == Map.LANDSCAPE_GRASS) return true;
        return false;
    }

    public void replant(int cnt) {
        for (int i=1; i<=cnt; i++) addNewTree();
    }

    private void addNewTree() {
        int type = xRandom.getIntInRange(1, 10);
        if (type == 1)
            addPlant(Plant.PLANT_COLOR_KIWI, Plant.CREATURE_PLANT_KIWI, Plant.PERIOD_OF_PREGNANCY_KIWI, Plant.MIN_REPRODUCT_KIWI, Plant.MAX_REPRODUCT_KIWI, Plant.PLANT_CALORIES_KIWI);
        if (type == 2)
            addPlant(Plant.PLANT_COLOR_APPLE, Plant.CREATURE_PLANT_APPLE, Plant.PERIOD_OF_PREGNANCY_APPLE, Plant.MIN_REPRODUCT_APPLE, Plant.MAX_REPRODUCT_APPLE, Plant.PLANT_CALORIES_APPLE);
        if (type == 3)
            addPlant(Plant.PLANT_COLOR_BANANA, Plant.CREATURE_PLANT_BANANA, Plant.PERIOD_OF_PREGNANCY_BANANA, Plant.MIN_REPRODUCT_BANANA, Plant.MAX_REPRODUCT_BANANA, Plant.PLANT_CALORIES_BANANA);
        if (type == 4)
            addPlant(Plant.PLANT_COLOR_STRAWBERRY, Plant.CREATURE_PLANT_STRAWBERRY, Plant.PERIOD_OF_PREGNANCY_STRAWBERRY, Plant.MIN_REPRODUCT_STRAWBERRY, Plant.MAX_REPRODUCT_STRAWBERRY, Plant.PLANT_CALORIES_STRAWBERRY);
        if (type == 5)
            addPlant(Plant.PLANT_COLOR_PEAR, Plant.CREATURE_PLANT_PEAR, Plant.PERIOD_OF_PREGNANCY_PEAR, Plant.MIN_REPRODUCT_PEAR, Plant.MAX_REPRODUCT_PEAR, Plant.PLANT_CALORIES_PEAR);
        if (type == 6)
            addPlant(Plant.PLANT_COLOR_LEMON, Plant.CREATURE_PLANT_LEMON, Plant.PERIOD_OF_PREGNANCY_LEMON, Plant.MIN_REPRODUCT_LEMON, Plant.MAX_REPRODUCT_LEMON, Plant.PLANT_CALORIES_LEMON);
        if (type == 7)
            addPlant(Plant.PLANT_COLOR_ORANGE, Plant.CREATURE_PLANT_ORANGE, Plant.PERIOD_OF_PREGNANCY_ORANGE, Plant.MIN_REPRODUCT_ORANGE, Plant.MAX_REPRODUCT_ORANGE, Plant.PLANT_CALORIES_ORANGE);
        if (type == 8)
            addPlant(Plant.PLANT_COLOR_BLUEBERRY, Plant.CREATURE_PLANT_BLUEBERRY, Plant.PERIOD_OF_PREGNANCY_BLUEBERRY, Plant.MIN_REPRODUCT_BLUEBERRY, Plant.MAX_REPRODUCT_ORANGE, Plant.PLANT_CALORIES_BLUEBERRY);
        if (type == 9)
            addPlant(Plant.PLANT_COLOR_COCONUT, Plant.CREATURE_PLANT_COCONUT, Plant.PERIOD_OF_PREGNANCY_COCONUT, Plant.MIN_REPRODUCT_COCONUT, Plant.MAX_REPRODUCT_COCONUT, Plant.PLANT_CALORIES_COCONUT);
        if (type == 10)
            addPlant(Plant.PLANT_COLOR_BELLADONNA, Plant.CREATURE_PLANT_BELLADONNA, Plant.PERIOD_OF_PREGNANCY_BELLADONNA, Plant.MIN_REPRODUCT_BELLADONNA, Plant.MAX_REPRODUCT_BELLADONNA, Plant.PLANT_CALORIES_ORANGE);
    }

    private void addPlant(int color, int type, int PERIOD_OF_PREGNANT, int MIN_REPRODUCT, int MAX_REPRODUCT, int calories) {
        code.MyMath.Point pt = getEmptyGrass();
        Map.world.plants.add(new Plant(pt.getX(), pt.getY(), color, type, PERIOD_OF_PREGNANT, MIN_REPRODUCT, MAX_REPRODUCT, calories));
    }

    public void initEmptyGrass() {
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
        code.MyMath.Point res = grass.get(index);
        grass.remove(index);
        return res;
    }

    public void addEmptyGrass(code.MyMath.Point pt) {
        grass.add(pt);
    }
}
