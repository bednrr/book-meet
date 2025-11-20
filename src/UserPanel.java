import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel extends JFrame {

    public UserPanel(String username) {
        setTitle("Book&Meet – Panel użytkownika");
        setSize(1200, 750);
        setMinimumSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(241, 241, 241, 255));

        // --- LOGO ---
        JLabel bookMeet = new JLabel("book&meet");
        bookMeet.setFont(new Font("SansSerif", Font.BOLD, 28));
        bookMeet.setForeground(new Color(112, 72, 98));
        bookMeet.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(bookMeet, BorderLayout.NORTH);

        // --- MAIN PANEL ---
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;

        // --- LEWA KOLUMNA ---
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 10, 10, 10);
        gbcLeft.fill = GridBagConstraints.BOTH;

        JPanel topLeft = createCard("", "");
        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        gbcLeft.weightx = 1.0; gbcLeft.weighty = 0.3;
        leftPanel.add(topLeft, gbcLeft);

        JPanel middleLeft = createCard("", "");
        gbcLeft.gridy = 1; gbcLeft.weighty = 0.4;
        leftPanel.add(middleLeft, gbcLeft);

        JPanel bottomLeft = createCard("", "");
        gbcLeft.gridy = 2; gbcLeft.weighty = 0.3;
        leftPanel.add(bottomLeft, gbcLeft);

        // --- PRAWA KOLUMNA ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(10, 10, 10, 10);
        gbcRight.fill = GridBagConstraints.BOTH;

        // Górny rząd – dwa panele w jednym wierszu, ale bez rozszerzania całej kolumny
        JPanel topRow = new JPanel(new GridLayout(1, 2, 20, 0));
        topRow.setOpaque(false);

        topRow.add(createCard("", ""));
        topRow.add(createCard("", ""));

        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.gridwidth = 2;
        gbcRight.weightx = 0.5;
        gbcRight.weighty = 0.4;

        rightPanel.add(topRow, gbcRight);


        // Środkowy kafelek
        JPanel middleRight = createCard("", "");
        gbcRight.gridx = 0; gbcRight.gridy = 1;
        gbcRight.gridwidth = 2;
        gbcRight.weightx = 1.0; gbcRight.weighty = 0.3;
        rightPanel.add(middleRight, gbcRight);

        // Dolny kafelek
        JPanel bottomRight = createCard("", "");
        gbcRight.gridx = 0; gbcRight.gridy = 2;
        gbcRight.gridwidth = 2;
        gbcRight.weightx = 1.0; gbcRight.weighty = 0.3;
        rightPanel.add(bottomRight, gbcRight);


        // --- DODANIE PANELI ---
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0.5;
        gbcMain.weighty = 1.0;
        gbcMain.insets = new Insets(30, 150, 30, 0);
        mainPanel.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.insets = new Insets(30, 0, 30, 150);
        mainPanel.add(rightPanel, gbcMain);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }


    private JPanel createCard(String title, String desc) {
        JPanel card = new RoundedPanel(25, new Color(255, 255, 255, 230));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 22));
        lblTitle.setForeground(new Color(40, 40, 40));

        JLabel lblDesc = new JLabel("<html>" + desc + "</html>");
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblDesc.setForeground(new Color(100, 100, 100));

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(lblTitle, BorderLayout.NORTH);
        textPanel.add(lblDesc, BorderLayout.CENTER);

        card.add(textPanel, BorderLayout.CENTER);

        // Hover efekt
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(255, 255, 255, 255));
                card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(255, 255, 255, 230));
                card.setCursor(Cursor.getDefaultCursor());
            }
        });

        return card;
    }


    private static class RoundedPanel extends JPanel {
        private final int radius;

        public RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            setBackground(bgColor);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            int shadowOffset = 4;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(new Color(0, 0, 0, 5));
            g2.fillRoundRect(
                    shadowOffset, shadowOffset,
                    width - shadowOffset * 2,
                    height - shadowOffset * 2,
                    radius + 10, radius + 10
            );

            g2.setColor(getBackground());
            g2.fillRoundRect(
                    0, 0,
                    width - shadowOffset * 2,
                    height - shadowOffset * 2,
                    radius, radius
            );

            g2.dispose();
        }
    }


}
