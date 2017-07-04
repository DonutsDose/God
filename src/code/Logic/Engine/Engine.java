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
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

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
    public static int tigerCount = WorldCreator.TIGERS_COUNT;
    public static int humanCount = WorldCreator.HUMANS_COUNT;

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
        World.replant(replantTree);
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
        LinkedList<SortSegment> seg = new LinkedList();
        seg.add(new SortSegment(fishCount, "Fish"));
        seg.add(new SortSegment(rabbitCount, "Rabbit"));
        seg.add(new SortSegment(bearCount, "Bear"));
        seg.add(new SortSegment(wolfCount, "Wolf"));
        seg.add(new SortSegment(tigerCount, "Tiger"));
        seg.add(new SortSegment(humanCount, "Human"));
        seg.sort(SortSegment::compareTo);
        String msg = "<html>Locked flag: " + existSelected;
        for (int i=0; i<seg.size(); i++)
            msg += "<br> " + (i + 1) + "." + seg.get(i).name + ": " + seg.get(i).x;
        msg += "</html>";
        MainPanel.mapInfoPanel.update(date, msg);
    }

    private static void death() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (World.ref[i][j] != null && !World.ref[i][j].exist) {
                    switch (World.ref[i][j].type) {
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
                        case AnimalSapiens.CREATURE_ANIMAL_TIGER:
                            tigerCount--;
                            break;
                        case AnimalSapiens.CREATURE_ANIMAL_HUMAN:
                            humanCount--;
                            break;
                    }
                    World.ref[i][j] = null;
                }
        if (bearCount == 0) {
            MainPanel.eventPanel.update("Last bear death(");
            bearCount = -1;
        }
        if (wolfCount == 0) {
            MainPanel.eventPanel.update("Last wolf death(");
            wolfCount = -1;
        }
        if (tigerCount == 0) {
            MainPanel.eventPanel.update("Last tiger death(");
            tigerCount = -1;
        }
        if (humanCount == 0) {
            MainPanel.eventPanel.update("Last human death(");
            humanCount = -1;
        }
    }

    private static void resetAll() {
        borned.clear();
    }

    private static void addBorned() {
        for (int i=0; i<borned.size(); i++)
            if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_BEAR) {
                World.ref[borned.get(i).pos.getX()][borned.get(i).pos.getY()] = new Bear(borned.get(i).pos, xRandom.getBoolean(50));
                bearCount++;
            } else if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_WOLF) {
                World.ref[borned.get(i).pos.getX()][borned.get(i).pos.getY()] = new Wolf(borned.get(i).pos, xRandom.getBoolean(50));
                wolfCount++;
            } else if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_TIGER) {
                World.ref[borned.get(i).pos.getX()][borned.get(i).pos.getY()] = new Tiger(borned.get(i).pos, xRandom.getBoolean(50));
                tigerCount++;
            } else if (borned.get(i).type == AnimalSapiens.CREATURE_ANIMAL_HUMAN) {
                World.ref[borned.get(i).pos.getX()][borned.get(i).pos.getY()] = new Human(borned.get(i).pos, xRandom.getBoolean(50));
                humanCount++;
            }
    }

    private static void creaturesAct() {
        for (int i=0; i<Map.MAP_HIGHT; i++)
            for (int j=0; j<Map.MAP_WIDTH; j++)
                if (World.ref[i][j] != null && World.ref[i][j].exist) {
                    World.ref[i][j].exist = World.ref[i][j].act();
                }
    }

    private static void addNewFish() {
        for (int i=1; i<=Map.FISH_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.FISH_CREATING_PROBABLY)) continue;
            Point pt = World.getWaterHigh();
            if (World.checkEmptyPosition(pt.getX(), pt.getY())) {
                World.ref[pt.getX()][pt.getY()] = new Fish(pt);
                fishCount++;
            }
        }
    }

    private static void addNewRabbit() {
        for (int i=1; i<=Map.RABBIT_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.RABBIT_CREATING_PROBABLY)) continue;
            Point pt = World.getGroundHigh();
            if (World.checkEmptyPosition(pt.getX(), pt.getY())) {
                World.ref[pt.getX()][pt.getY()] = new Rabbit(pt);
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
        wolfCount = WorldCreator.WOLFS_COUNT;
        tigerCount = WorldCreator.TIGERS_COUNT;
        humanCount = WorldCreator.HUMANS_COUNT;
    }
}
