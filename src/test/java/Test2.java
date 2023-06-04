import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Log4j2
public class Test2 {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextField textField = new JFormattedTextField();
        textField.setLocation(0, 0);
        textField.setEditable(false);
        textField.setText("Text");
        textField.setSize(new Dimension(200, 40));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int count = e.getClickCount();
                if (count == 1) {
                    if (textField.isEditable()) {
                        textField.requestFocus();
                    }
                } else if (count == 2) {
                    log.debug("Click 2");
                    textField.setEditable(true);
                    textField.requestFocus();
                    textField.getCaret().setVisible(true);

                    log.debug("Focus: {}", textField.isFocusable());
                    log.debug("Editable: {}", textField.isEditable());
                }
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                log.debug("Focus lost");
                textField.setEditable(false);
                textField.setCaretPosition(0);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    log.debug("Typed Enter.");
                    textField.setEditable(false);
                    textField.setCaretPosition(0);
                }
            }
        });
        panel.add(textField);
        JButton button = new JButton();
        button.setLocation(50, 300);
        button.setSize(new Dimension(50, 50));
        panel.add(button);

        frame.setContentPane(panel);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
