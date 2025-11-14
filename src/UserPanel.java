import javax.swing.*;
public class UserPanel extends JFrame {
    public UserPanel() {
        setTitle("Panel Użytkownik");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Witaj Użytkowniku!", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
