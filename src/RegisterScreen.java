import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;

    public RegisterScreen() {
        setTitle("book&meet – Rejestracja");
        setSize(1300, 800);
        setMinimumSize(new Dimension(900, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        Color leftPanelColor = new Color(112, 72, 98);
        Color rightPanelColor = new Color(227, 127, 115);
        Color accentText = new Color(255, 210, 190);

        // --- PANEL LEWY ---
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(leftPanelColor);

        JLabel logoLabel = new JLabel("book&meet");
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        logoLabel.setForeground(accentText);
        leftPanel.add(logoLabel);

        // --- PANEL PRAWY ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(rightPanelColor);

        JPanel formPanel = new JPanel(null);
        formPanel.setPreferredSize(new Dimension(400, 260));
        formPanel.setOpaque(false);

        // --- Pola tekstowe ---
        loginField = new JTextField();
        passwordField = new JPasswordField();

        stylizeField(loginField);
        stylizeField(passwordField);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(20, 10, 200, 20);

        JLabel passLabel = new JLabel("Hasło:");
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(20, 80, 200, 20);

        loginField.setBounds(20, 35, 360, 30);
        passwordField.setBounds(20, 105, 360, 30);

        // --- Przezroczyste przyciski ---
        JButton registerButton = new JButton("Zarejestruj");
        JButton backButton = new JButton("Powrót");
        stylizeButton(registerButton);
        stylizeButton(backButton);

        registerButton.setBounds(20, 160, 150, 40);
        backButton.setBounds(230, 160, 150, 40);

        formPanel.add(loginLabel);
        formPanel.add(passLabel);
        formPanel.add(loginField);
        formPanel.add(passwordField);
        formPanel.add(registerButton);
        formPanel.add(backButton);

        rightPanel.add(formPanel);

        add(leftPanel);
        add(rightPanel);

        // --- Akcje przycisków ---
        registerButton.addActionListener(e -> registerUser());
        backButton.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        setVisible(true);
    }

    private void registerUser() {
        String login = loginField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola!");
            return;
        }

        if (UserManager.isUserExists(login)) {
            JOptionPane.showMessageDialog(this, "Użytkownik o tym loginie już istnieje!");
            return;
        }

        String userID = UserManager.generateUserID();
        User user = new User(userID, login, password, Role.USER);
        UserManager.saveUser(user);

        JOptionPane.showMessageDialog(this, "Zarejestrowano pomyślnie!");
        dispose();
        new LoginScreen();
    }

    // --- Stylizacja pól ---
    private void stylizeField(JTextField field) {
        field.setOpaque(false);
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        field.setFont(new Font("SansSerif", Font.PLAIN, 18));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
    }

    // --- Stylizacja przycisków ---
    private void stylizeButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);   // zawsze przezroczyste
        btn.setOpaque(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color hoverOverlay = new Color(255, 255, 255, 40);  // bardzo lekka biel
        Color normalOverlay = new Color(0, 0, 0, 0);
    }
}
