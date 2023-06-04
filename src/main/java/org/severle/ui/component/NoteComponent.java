package org.severle.ui.component;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@Log4j2
public class NoteComponent extends JComponent {
    private boolean isPressed = false;
    private boolean isResizable = false;
    private int mouseX;
    private int mouseY;
    private int pressedMouseX;
    private int pressedMouseY;

    {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mouseX = e.getX();
                mouseY = e.getY();

                if (isResizable) {
                    // Resize it.
                    int dx = mouseX - getWidth();
                    int newWidth = getWidth() + dx;
                    if (newWidth >= getMinimumSize().width) {
                        setSize(newWidth, getHeight());
                        revalidate();
                        repaint();
                    }
                } else {
                    // Move it.
                    int dx = mouseX - pressedMouseX;
                    int dy = mouseY - pressedMouseY;
                    Point location = getLocation();
                    location.x += dx;
                    location.y += dy;
                    setLocation(location);
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();

                isResizable = (mouseX < getWidth() && getWidth() - mouseX < 10) && (mouseY < getHeight() && mouseY > 0);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isPressed) {
                    isPressed = true;
                    pressedMouseX = e.getX();
                    pressedMouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isPressed) {
                    isPressed = false;
                }
            }
        });
        this.setMinimumSize(new Dimension(20, 40));
    }

    public NoteComponent() {
    }

    public NoteComponent(int width, int height) {
        this.setSize(width, height);
    }

    public NoteComponent(Dimension dimension) {
        this.setSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = ((Graphics2D) g);
        int width = getWidth();
        int height = getHeight();
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, width, height);
    }
}
