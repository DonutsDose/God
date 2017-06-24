package code.GUI.Cell;

import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.GUI.Map.MapRender;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by DonutsDose-PC on 20.06.2017.
 */

public class CellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(new Color(MapRender.background[row][column]));
        setForeground(new Color(MapRender.foreground[row][column]));
        setValue(MapRender.value[row][column]);
        return this;
    }
}
