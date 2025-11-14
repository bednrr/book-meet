import javax.swing.*;
public class ITAdminPanel extends JFrame {
    public ITAdminPanel() {
        setTitle("Panel IT Admin");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Witaj IT Admin!", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
