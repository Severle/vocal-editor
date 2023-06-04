import javax.swing.*;
import java.awt.*;

public class Test3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);

        JButton button = new JButton();
        button.setSize(new Dimension(100, 40));
        button.setLocation(0, 0);
        panel.add(button);

        frame.setVisible(true);
    }
}
