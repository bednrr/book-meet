import javax.swing.*;
public class RecepcjonistaPanel extends JFrame {
    public RecepcjonistaPanel() {
        setTitle("Panel Recepcjonista");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Witaj Recepcjonisto!", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
