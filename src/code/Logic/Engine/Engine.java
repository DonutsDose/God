package code.Logic.Engine;

import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.GUI.Map.MapRender;
import code.Logic.Objects.Fish;
import code.Logic.Objects.Rabbit;
import code.MyMath.Point;
import code.MyMath.xRandom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DonutsDose-PC on 22.06.2017.
 */

public class Engine{

    private static int date = 0;
    public static Timer timer = null;
    private static int timerDelay = 510;
    private static int replantTree = 0;

    //STATISTIC
    public static int fishCount = 0;
    public static int fishSum = 0;
    public static int rabbitCount = 0;
    public static int rabbitSum = 0;

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
        //DYNAMIC
        animalAct();
        //STATIC
        plantAct();
        //NEW
        Map.world.replant(replantTree);
        addNewFish();
        addNewRabbit();
        //-------
        MainPanel.mapInfoPanel.update(date, "<html>Fish : " + fishCount  + "<br> Rabbit : " + rabbitCount + "</html>");
        MapRender.update();
        MainPanel.map.repaint();
    }

    private static void animalAct() {
        for (int i=0; i<Map.world.animals.size(); i++)
            if (!Map.world.animals.get(i).act()) {
                Map.world.animals.remove(i);
                i--;
            }
    }

    private static void plantAct() {
        replantTree = 0;
        for (int i=0; i<Map.world.plants.size(); i++) {
            if (!Map.world.plants.get(i).act()) {
                Map.world.plants.remove(i);
                i--;
                replantTree++;
            }
        }
    }

    private static void addNewFish() {
        for (int i=1; i<=Map.FISH_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.FISH_CREATING_PROBABLY)) continue;
            Point pt = Map.world.getWaterHigh();
            if (Map.checkEmptyPosition(pt.getX(), pt.getY())) Map.world.animals.add(new Fish(pt.getX(), pt.getY()));
        }
    }

    private static void addNewRabbit() {
        for (int i=1; i<=Map.RABBIT_CREATING_COUNT; i++) {
            if (!xRandom.getBoolean(Map.RABBIT_CREATING_PROBABLY)) continue;
            Point pt = Map.world.getGroundOut();
            if (Map.checkEmptyPosition(pt.getX(), pt.getY())) Map.world.animals.add(new Rabbit(pt.getX(), pt.getY()));
        }
    }

    public static void changeDelay(int x) {
        x = (100 - x + 1) * 10;
        if (timer != null) timer.setDelay(x); else timerDelay = x;
    }
}
