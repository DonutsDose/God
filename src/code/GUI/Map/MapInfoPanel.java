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

    public final void update(int days, String msg) {
        info.setText(String.format("<html>Date: %s<br>%s</html>", Formatter.formatDate(days), msg));
    }

    public void reset() {
        info.setText("-");
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Map Info"));
        setPreferredSize(new Dimension(300, 100));
    }

    private void setupInfo() {
        info = new JLabel("-", SwingConstants.LEFT);
        add(info);
    }
}
