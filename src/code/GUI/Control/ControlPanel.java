package code.GUI.Control;

import code.GUI.Main.MainPanel;
import code.Logic.Engine.Engine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class ControlPanel extends JPanel implements ChangeListener {

    JButton start, pause, stop, reset;

    public ControlPanel() {
        setupView();
        setupButton();
        setupSlider();
        setVisible(true);
    }

    private void setupSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
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
        start.setPreferredSize(new Dimension(25, 25));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                stop.setEnabled(false);
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
        pause.setPreferredSize(new Dimension(25, 25));
        pause.setEnabled(false);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.timer.stop();
                start.setEnabled(true);
                stop.setEnabled(false);
                reset.setEnabled(false);
                pause.setEnabled(false);
            }
        });
        pause.setVisible(true);
        add(pause);

        // STOP
        stop = new JButton();
        stop.setIcon(new ImageIcon("src\\resourse\\Image\\control-stop.png"));
        stop.setPreferredSize(new Dimension(25, 25));
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        stop.setVisible(true);
        stop.setEnabled(false);
        add(stop);

        // RESET
        reset = new JButton();
        reset.setIcon(new ImageIcon("src\\resourse\\Image\\control-reset.png"));
        reset.setPreferredSize(new Dimension(25, 25));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(false);
                stop.setEnabled(false);
                start.setEnabled(true);
                MainPanel.map.setupMap();
                MainPanel.map.repaint();
            }
        });
        reset.setVisible(true);
        add(reset);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension( 600, 0));
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
