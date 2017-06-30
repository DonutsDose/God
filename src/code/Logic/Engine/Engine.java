package code.Logic.Engine;

import code.GUI.Control.ControlPanel;
import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.GUI.Map.MapRender;
import code.GUI.World.World;
import code.GUI.World.WorldCreator;
import code.Logic.Abstract.AnimalPrimitive;
import code.Logic.Abstract.AnimalSapiens;
import code.Logic.Abstract.Creature;
import code.Logic.Objects.*;
import code.MyMath.Point;
import code.MyMath.xRandom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by DonutsDose-PC on 22.06.2017.
 */

public class Engine{

    public static int date = 0;
    public static Timer timer = null;
    private static int timerDelay = 510;
    public static int replantTree = 0;
    public static Creature selected = null;
    public static boolean existSelected = false;
    public static LinkedList<NewAnimal> borned = new LinkedList();

    //STATISTIC
    public static int fishCount = 0;
    public static int rabbitCount = 0;
    public static int bearCount = WorldCreator.BEARS_COUNT;
    public static int wolfCount = WorldCreator.WOLFS_COUNT;

    public static void start() {
        if (timer == null) {
            timer = new Timer(timerDelay, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    act();
                }
            });
        }
        timer.start();
    }

    public static void act() {
        date++;
        resetAll();
        borned.clear();
        //DYNAMIC
        creaturesAct();
        //NEW
        death();
        Map.world.replant(replantTree);
        replantTree = 0;
        addBorned();
        addNewFish();
        addNewRabbit();
        //-------
        updateMapInfo();
        MapRender.update();
        MainPanel.map.repaint();
    }

    public static void updateMapInfo() {
        MainPanel.mapInfoPanel.update(date, "<html>Fish: " + fishCount
                + "<br> Rabbit: " + rabbitCount
                + "<br> Bear: " + bearCount
                + "<br> Wolf: " + wolfCount
                + "<br> Locked flag: " + existSelected + "</html>");
    }

    private static void death() {
        for (int i=0; i<Map.world.creatures.size(); i++)
            if (!Map.world.creatures.get(i).exist) {
                switch (Map.world.creatures.get(i).type) {
                    case AnimalPrimitive.CREATURE_ANIMAL_FISH:
                        fishCount--;
                        break;
                    case AnimalPrimitive.CREATURE_ANIMAL_RABBIT:
                        rabbitCount--;
                        break;
                    case AnimalSapiens.CREATURE_ANIMAL_BEAR:
                        bearCount--;
                        break;
                    case AnimalSapiens.CREATURE_ANIMAL_WOLF:
                        wolfCount--;
                        break;
                }
                Map.world.creatures.remove(i);
            }
        if (bearCount == 0) {
            MainPanel.eventPanel.update("Last bear death(");
            bearCount = -1;
        }
    }

    private static void resetAll() {
        borned.clear();
    }

    private static void addBorned() {
        for (int i=0; i<borned.size(); i++)
            if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_BEAR) {
                Map.world.creatures.add(new Bear(borned.get(i).pos, xRandom.getBoolean(50)));
                bearCount++;
            } else if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_WOLF) {
                Map.world.creatures.add(new Wolf(borned.get(i).pos, xRandom.getBoolean(50)));
                wolfCount++;
            }
    }

    private static void creaturesAct() {
        for (int i=0; i<Map.world.creatures.size(); i++)
            if (Map.world.creatures.get(i).exist) Map.world.creatures.get(i).exist = Map.world.creatures.get(i).act();
    }

    private static void addNewFish() {
        for (int i=1; i<=Map.FISH_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.FISH_CREATING_PROBABLY)) continue;
            Point pt = Map.world.getWaterHigh();
            if (Map.world.checkEmptyPosition(pt.getX(), pt.getY())) {
                Map.world.creatures.add(new Fish(pt));
                fishCount++;
            }
        }
    }

    private static void addNewRabbit() {
        for (int i=1; i<=Map.RABBIT_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.RABBIT_CREATING_PROBABLY)) continue;
            Point pt = Map.world.getGroundHigh();
            if (Map.world.checkEmptyPosition(pt.getX(), pt.getY())) {
                Map.world.creatures.add(new Rabbit(pt));
                rabbitCount++;
            }
        }
    }

    public static void changeDelay(int x) {
        x = (100 - x + 1) * 10;
        if (timer != null) timer.setDelay(x); else timerDelay = x;
    }

    public static void reset() {
        date = 0;
        replantTree = 0;
        borned.clear();
        fishCount = 0;
        rabbitCount = 0;
        bearCount = WorldCreator.BEARS_COUNT;
    }
}
