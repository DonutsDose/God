package code.GUI.Main;

import javax.swing.*;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class MainWindow extends JFrame {

    public MainWindow() {
        setupView();
        setupPanel();
        setVisible(true);
    }

    private void setupView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setIconImage(new ImageIcon("src\\resourse\\Image\\logo.jpg").getImage());
        setTitle("GOD");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupPanel() {
        add(new MainPanel());
    }
}
