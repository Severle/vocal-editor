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

    {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mouseX = e.getX();
                mouseY = e.getY();
                log.debug("MouseX: {}, MouseY: {}", mouseX, mouseY);

                if (isResizable) {
                    // Resize it.
                    int dx = mouseX - (getX() + getWidth());
                    log.debug("dx: {}", dx);
                    int newWidth = getWidth() + dx;
                    if (newWidth >= getMinimumSize().width) {
                        setSize(newWidth, getHeight());
                    }
                    revalidate();
                    repaint();
                } else {
                    // Move it.

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                log.debug("MouseX: {}, MouseY: {}", mouseX, mouseY);

                if ((mouseX < (getX() + getWidth()) && (getX() + getWidth()) - mouseX < 5) && (mouseY > getY() && mouseY < getY() + getHeight())) {
                    log.debug("In right bound.");
                    isResizable = true;
                } else {
                    isResizable = false;
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log.debug("Mouse clicked.");
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                log.debug("Mouse pressed.");
                if (!isPressed) {
                    isPressed = true;
                    log.debug("Pressed.");
                }
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                log.debug("Mouse released.");
                if (isPressed) {
                    isPressed = false;
                    log.debug("Release.");
                }
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                log.debug("Mouse entered.");
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                log.debug("Mouse exited.");
                super.mouseExited(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                log.debug("Mouse moved.In Mouse");
                super.mouseMoved(e);
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
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        setBackground(Color.CYAN);
    }
}
