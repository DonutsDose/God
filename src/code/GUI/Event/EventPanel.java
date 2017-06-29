package code.GUI.Event;

import code.GUI.Formatter.Formatter;
import code.Logic.Engine.Engine;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by DonutsDose-PC on 21.06.2017.
 */

public class EventPanel extends JScrollPane {

    JList<String> events = new JList(new DefaultListModel<String>());

    public EventPanel() {
        setupView();
        setupEvents();
        setVisible(true);
    }

    private void setupView() {
        setBorder(new TitledBorder("Events Info"));
        setPreferredSize(new Dimension(0, 150));
    }

    private void setupEvents() {
        events.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        events.setLayoutOrientation(JList.VERTICAL);
        events.setBackground(new Color(0xEEEEEEE));
        setViewportView(events);
    }

    public void update(String event) {
        ((DefaultListModel<String>)events.getModel()).addElement(formatEvent(Engine.date, event));
        int lastIndex = events.getModel().getSize() - 1;
        if (lastIndex >= 0) {
            events.ensureIndexIsVisible(lastIndex);
        }
    }

    private String formatEvent(int days, String event) {
        return " [ " + Formatter.formatDateShort(days)  + " ] - " + event;
    }

    public void reset() {
        ((DefaultListModel<String>)events.getModel()).clear();
    }
}
