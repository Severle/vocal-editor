package org.severle.ui.component;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Log4j2
public class PianoRolling extends JPanel implements MouseListener {
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void paint(Graphics g) {
        log.debug("Repaint Panel.");





        super.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        log.debug("Click.");
        this.setSelected(!this.selected);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
