package code.GUI.Cell;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */
public class CellInfoPanel extends JPanel {

    private JLabel info;

    public CellInfoPanel() {
        setupView();
        setupInfo();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Cell Info"));
        setPreferredSize(new Dimension(0, 100));
    }


    private void setupInfo() {
        info = new JLabel("-", SwingConstants.LEFT);
        add(info);
    }
}
