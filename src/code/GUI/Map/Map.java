package code.GUI.Map;

import code.GUI.Cell.CellRenderer;
import code.GUI.World.World;
import code.GUI.World.WorldCreator;

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
    public static final int MAX_CREATURE_TYPE         = 12;
    public static final int MAX_LANDSCAPE_TYPE         = 9;
    public static final int FISH_CREATING_PROBABLY = 10;
    public static final int FISH_CREATING_COUNT = 5;

    public static World world = null;

    public Map() {
        setupView();
        setupMap();
        setVisible(true);
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

    public static boolean checkEmptyPosition(int x, int y) {
        for (int i=0; i<world.animals.size(); i++)
            if (world.animals.get(i).x == x && world.animals.get(i).y == y) return false;
        for (int i=0; i<world.plants.size(); i++)
            if (world.plants.get(i).x == x && world.plants.get(i).y == y) return false;
        return true;
    }
}
