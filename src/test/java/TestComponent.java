import com.formdev.flatlaf.FlatIntelliJLaf;
import org.severle.ui.component.NoteComponent;

import javax.swing.*;
import java.awt.*;

public class TestComponent {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        NoteComponent note = new NoteComponent(100, 40);
        panel.add(note);

        frame.setContentPane(panel);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
