package code.GUI.Map;

import code.GUI.Cell.CellRenderer;
import code.GUI.World.World;
import code.GUI.World.WorldCreator;
import code.Logic.Abstract.Creature;
import code.MyMath.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class Map extends JTable {

    public static final int MAP_HIGHT                 = 69;
    public static final int MAP_WIDTH                 = 108;
    public static final int CELL_SIZE                 = 10;
    public static final int LANDSCAPE_WATER_LOW       = 1;
    public static final int LANDSCAPE_WATER_HIGH      = 2;
    public static final int LANDSCAPE_GROUND_LOW      = 4;
    public static final int LANDSCAPE_GROUND_HIGH     = 5;
    public static final int LANDSCAPE_GRASS           = 6;
    public static final int LANDSCAPE_FRESH_WATER     = 7;

    public static final int MAX_CREATURE_TYPE          = 14;
    public static final int MAX_LANDSCAPE_TYPE         = 9;

    public static final int TREE                       = 1;
    public static final int ANIMAL_PRIMITIVE           = 2;
    public static final int ANIMAL_SAPIENSE            = 3;

    public static final int FISH_CREATING_PROBABLY = 15;
    public static final int FISH_CREATING_COUNT = 5;
    public static final int RABBIT_CREATING_PROBABLY = 10;
    public static final int RABBIT_CREATING_COUNT = 2;
    
    public static int[] passabilityFish = new int[MAX_LANDSCAPE_TYPE + 1];
    public static int[] passabilityRabbit = new int[MAX_LANDSCAPE_TYPE + 1];
    public static int[] passabilityBear = new int[MAX_LANDSCAPE_TYPE + 1];

    public static World world = null;

    public Map() {
        initPassability();
        setupView();
        setupMap();
        setVisible(true);
    }
    
    private void initPassability() {
        for (int i=0; i<=Map.MAX_LANDSCAPE_TYPE; i++) {
            passabilityBear[i] = -1;
            passabilityFish[i] = -1;
            passabilityRabbit[i] = -1;
        }
        //FISH
        passabilityFish[Map.LANDSCAPE_WATER_LOW] = 50;
        passabilityFish[Map.LANDSCAPE_WATER_HIGH] = 65;
        //Rabbit
        passabilityRabbit[Map.LANDSCAPE_GROUND_LOW] = 25;
        passabilityRabbit[Map.LANDSCAPE_GROUND_HIGH] = 35;
        passabilityRabbit[Map.LANDSCAPE_GROUND_HIGH] = 40;
        //Bear
        passabilityBear[Map.LANDSCAPE_GROUND_LOW] = 510;
        passabilityBear[Map.LANDSCAPE_GROUND_HIGH] = 640;
        passabilityBear[Map.LANDSCAPE_GRASS] = 840;
        passabilityBear[Map.LANDSCAPE_FRESH_WATER] = 1010;
        passabilityBear[Map.LANDSCAPE_WATER_HIGH] = 1035;
    }

    private void setupView() {
        setModel(new DefaultTableModel(MAP_HIGHT, MAP_WIDTH) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        setCellSelectionEnabled(false);
        setRowHeight(CELL_SIZE);
        for (int i=0; i<MAP_WIDTH; i++) {
            getColumnModel().getColumn(i).setMinWidth(CELL_SIZE);
            getColumnModel().getColumn(i).setMaxWidth(CELL_SIZE);
        }
        setBorder(BorderFactory.createLineBorder(new Color(0x333333)));
        setDefaultRenderer(Object.class, new CellRenderer());
    }

    public void setupMap() {
        world = WorldCreator.createWorld();
        MapRender.setBackground();
        MapRender.update();
    }

    public static int getType(Creature obj) {
        if (obj.type >= 1 && obj.type <= 10) return TREE;
        if (obj.type >= 11 && obj.type <= 12) return ANIMAL_PRIMITIVE;
        return ANIMAL_SAPIENSE;
    }
}
