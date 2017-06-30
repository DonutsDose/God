package code.GUI.Control;

import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Abstract.Creature;
import code.Logic.Engine.Engine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static code.Logic.Engine.Engine.selected;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class ControlPanel extends JPanel implements ChangeListener {

    public static boolean isOpenBearEvent = false;
    public static boolean isOpenWolfEvent = false;
    public static boolean isOpenTigerEvent = false;
    public static boolean isOpenHumanEvent = false;

    public static JButton start, pause, reset, flag;
    public static JCheckBox bearBox, wolfBox, tigerBox, humanBox;
    public static JPanel checkBox;

    public ControlPanel() {
        setupView();
        setupButton();
        setupSlider();
        setupCheckBox();
        setVisible(true);
    }

    private void setupCheckBox() {
        checkBox = new JPanel();
        checkBox.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBox.setVisible(true);
        add(checkBox);
        checkBox.add(new JLabel("Event Filter:"));
        bearBox = new JCheckBox("Bear");
        bearBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (isOpenBearEvent) isOpenBearEvent = false; else isOpenBearEvent = true;
            }
        });
        bearBox.setVisible(true);
        checkBox.add(bearBox);

        wolfBox = new JCheckBox("Wolf");
        wolfBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (isOpenWolfEvent) isOpenWolfEvent = false; else isOpenWolfEvent = true;
            }
        });
        wolfBox.setVisible(true);
        checkBox.add(wolfBox);

        tigerBox = new JCheckBox("Tiger");
        tigerBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (isOpenTigerEvent) isOpenTigerEvent = false; else isOpenTigerEvent = true;
            }
        });
        tigerBox.setVisible(true);
        checkBox.add(tigerBox);

        humanBox = new JCheckBox("Human");
        humanBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (isOpenHumanEvent) isOpenHumanEvent = false; else isOpenHumanEvent = true;
            }
        });
        humanBox.setVisible(true);
        checkBox.add(humanBox);
    }

    private void setupSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
        slider.setPreferredSize(new Dimension(173, 50));
        Engine.changeDelay(80);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setVisible(true);
        add(slider);
    }

    private void setupButton() {
        // START
        start = new JButton();
        start.setIcon(new ImageIcon("src\\resourse\\Image\\control-start.png"));
        start.setPreferredSize(new Dimension(22, 25));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                start.setEnabled(false);
                reset.setEnabled(false);
                Engine.start();
            }
        });
        start.setVisible(true);
        add(start);

        // PAUSE
        pause = new JButton();
        pause.setIcon(new ImageIcon("src\\resourse\\Image\\control-pause.png"));
        pause.setPreferredSize(new Dimension(22, 25));
        pause.setEnabled(false);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.timer.stop();
                start.setEnabled(true);
                reset.setEnabled(true);
                pause.setEnabled(false);
            }
        });
        pause.setVisible(true);
        add(pause);

        // FLAG
        flag = new JButton();
        flag.setIcon(new ImageIcon("src\\resourse\\Image\\control-flag.png"));
        flag.setPreferredSize(new Dimension(22, 25));
        flag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Engine.existSelected) {
                    Engine.existSelected = false;
                    Engine.selected.isSelected = false;
                    Engine.updateMapInfo();
                    return;
                }
                int x = MainPanel.map.getSelectedRow(), y = MainPanel.map.getSelectedColumn();
                if (x != -1 && y != -1) {
                    Creature obj = Map.findCreature(x, y);
                    if (obj != null) {
                        Engine.existSelected = true;
                        Engine.selected = obj;
                        obj.isSelected = true;
                    }
                }
                Engine.updateMapInfo();
            }
        });
        flag.setVisible(true);
        add(flag);
        
        // RESET
        reset = new JButton();
        reset.setIcon(new ImageIcon("src\\resourse\\Image\\control-reset.png"));
        reset.setPreferredSize(new Dimension(22, 25));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(false);
                start.setEnabled(true);
                Engine.reset();
                MainPanel.mapInfoPanel.reset();
                MainPanel.cellInfoPanel.reset();
                MainPanel.eventPanel.reset();
                MainPanel.map.setupMap();
                MainPanel.map.repaint();
            }
        });
        reset.setVisible(true);
        add(reset);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(100, 70));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int x = source.getValue();
            Engine.changeDelay(x);
        }
    }
}
