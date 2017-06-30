package code.GUI.Main;

import code.GUI.Cell.CellInfoPanel;
import code.GUI.Control.ControlPanel;
import code.GUI.Event.EventPanel;
import code.GUI.Map.Map;
import code.GUI.Map.MapInfoPanel;

import javax.swing.*;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class MainPanel extends JPanel {

    private static JPanel rightPanel, leftPanel;
    public static Map map;
    public static MapInfoPanel mapInfoPanel;
    public static EventPanel eventPanel;
    public static CellInfoPanel cellInfoPanel;

    public MainPanel() {
        setupView();
        setupPanels();
        setupMap();
        setupLeftPanel();
        setupRightPanel();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void setupPanels() {
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        add(leftPanel);
        add(rightPanel);
    }

    private void setupLeftPanel() {
        mapInfoPanel = new MapInfoPanel();
        eventPanel = new EventPanel();
        cellInfoPanel = new CellInfoPanel();
        leftPanel.add(mapInfoPanel);
        leftPanel.add(cellInfoPanel);
        leftPanel.add(new ControlPanel());
        leftPanel.add(eventPanel);
    }

    private void setupMap() {
        map = new Map();
    }

    private void setupRightPanel() {
        rightPanel.add(map);
    }
}
