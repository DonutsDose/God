package code.GUI.Map;

import code.GUI.Formatter.Formatter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class MapInfoPanel extends JPanel {

    private JLabel info;

    public MapInfoPanel() {
        setupView();
        setupInfo();
        setVisible(true);
    }

    public final void update(int days) {
        info.setText(Formatter.formatDate(days));
    }

    public void reset() {
        info.setText("-");
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Map Info"));
        setPreferredSize(new Dimension(0, 100));
    }

    private void setupInfo() {
        info = new JLabel("-", SwingConstants.LEFT);
        add(info);
    }
}
