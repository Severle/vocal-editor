package org.severle.ui.component;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Log4j2
public class JNote extends JPanel {
    private boolean selected = false;
    private Color background = Color.CYAN.darker();

    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int count = e.getClickCount();
                if (count == 1) {
                    log.debug("Mouse click.");
                    setSelected(!selected);
                    log.debug("Selection: {}", selected);
                    repaint();
                } else if (count == 2) {

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                log.debug("Mouse entered.");
                background = background.brighter();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                log.debug("Mouse exited.");
                background = background.darker();
                repaint();
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public JNote() {
    }

    public JNote(Dimension dimension) {
        this.setSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        log.debug("paint component");

        log.debug("Repaint Panel.");
        int x = this.getX();
        int y = this.getY();
        log.debug("x: {}, y: {}", x, y);
        int width = this.getWidth();
        int height = this.getHeight();
        log.debug("Width: {}, Height: {}", width, height);
        float alignmentX = this.getAlignmentX();
        float alignmentY = this.getAlignmentY();
        log.debug("AlignmentX: {}, AlignmentY: {}", alignmentX, alignmentY);

        g.setColor(background);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y + height - 1, x + width, y + height - 1);
        FontMetrics fontMetrics = g.getFontMetrics();
        String text;
        if (selected) {
            text = "Selected";
        } else {
            text = "Unselected";
        }
        g.drawString(text, getTextAlignCenterX(width, text, fontMetrics), getTextAlignCenterY(height, fontMetrics));
        log.debug("Fill rect ok");
    }

    private static int getTextAlignCenterX(int width, String text, FontMetrics fontMetrics) {
        return (width - fontMetrics.stringWidth(text)) / 2;
    }

    private static int getTextAlignCenterY(int height, FontMetrics fontMetrics) {
        int ascent = fontMetrics.getAscent();
        return (height - ascent) / 2 + ascent;
    }
}
