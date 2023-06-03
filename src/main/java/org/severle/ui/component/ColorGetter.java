package org.severle.ui.component;

import javax.swing.*;
import java.awt.*;

public class ColorGetter {
    public static Color foreground;
    public static Color background;
    public static Color selectionForeground;
    public static Color selectionBackground;

    static {
        loadColors();
    }

    public static void loadColors() {
        JTable table = new JTable();
        foreground = table.getForeground();
        background = table.getBackground();
        selectionForeground = table.getSelectionForeground();
        selectionBackground = table.getSelectionBackground();
    }
}
