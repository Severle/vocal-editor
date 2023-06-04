package org.severle.ui.component;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Log4j2
public class JNote extends JPanel {
    private boolean selected = false;
    private static final Color COMMON_BACKGROUND_COLOR = Color.GREEN;
    private static final Color SELECTION_BACKGROUND_COLOR = Color.CYAN;
    private Color color = COMMON_BACKGROUND_COLOR.darker();

    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int count = e.getClickCount();
                if (count == 1) {
//                    log.debug("Mouse click.");
                    setSelected(!selected);
                    log.debug("Selection: {}", selected);
                    repaint();
                } else if (count == 2) {

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                log.debug("Mouse entered.");
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                color = COMMON_BACKGROUND_COLOR.brighter();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                log.debug("Mouse exited.");
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (!selected) {
                    color = COMMON_BACKGROUND_COLOR.darker();
                }
                repaint();
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            color = COMMON_BACKGROUND_COLOR.brighter();
        } else {
            color = COMMON_BACKGROUND_COLOR.darker();
        }
    }

    public JNote() {
    }

    public JNote(Dimension dimension) {
        this.setSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();

//        if (selected) {
//            g2d.setColor(SELECTION_BACKGROUND_COLOR);
//        } else {
//            g2d.setColor(COMMON_BACKGROUND_COLOR);
//        }
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
        FontMetrics fontMetrics = g2d.getFontMetrics();
    }

    private static int getTextAlignCenterX(int width, String text, FontMetrics fontMetrics) {
        return (width - fontMetrics.stringWidth(text)) / 2;
    }

    private static int getTextAlignCenterY(int height, FontMetrics fontMetrics) {
        int ascent = fontMetrics.getAscent();
        return (height - ascent) / 2 + ascent;
    }
}
