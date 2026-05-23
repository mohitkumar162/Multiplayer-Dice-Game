import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class Executer {

    private static GameEngine engine;
    private static JTextArea playTextArea;
    private static JTextArea resultTextArea;
    private static ArrayList<Player> players;

    public static void main(String[] args) {
        initPlayers();
        SwingUtilities.invokeLater(Executer::initUI);
    }

    private static void initPlayers() {
        int n;
        while (true) {
            String resp = JOptionPane.showInputDialog(
                    null,
                    "👥 Enter number of players (1-4):",
                    "Player Setup 📝",
                    JOptionPane.QUESTION_MESSAGE
            );
            try {
                n = Integer.parseInt(resp);
                if (n >= 1 && n <= 4) break;
            } catch (Exception ignore) {}
        }

        players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name;
            do {
                name = JOptionPane.showInputDialog(
                        null,
                        "🧑🎤 Enter player " + (i + 1) + " name:",
                        "Player Setup",
                        JOptionPane.PLAIN_MESSAGE
                );
            } while (name == null || name.trim().isEmpty());
            players.add(new Player(name.trim()));
        }
        engine = new GameEngine(players);
    }

    private static void initUI() {
        JFrame frame = new JFrame("🎲 Dice Game Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 540);
        frame.setLocationRelativeTo(null);

        // Aesthetic gradient + soft shape background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Main gradient
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(237, 241, 250),
                        getWidth(), getHeight(), new Color(196, 213, 245)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Subtle translucent circles
                g2d.setColor(new Color(255, 210, 220, 60));
                g2d.fillOval(50, getHeight() - 160, 120, 120);
                g2d.setColor(new Color(180, 192, 214, 50));
                g2d.fillOval(getWidth() - 180, 30, 150, 150);
                g2d.setColor(new Color(206, 255, 201, 50));
                g2d.fillOval(getWidth()/2 - 100, getHeight()/2 - 100, 180, 80);
            }
        };
        backgroundPanel.setLayout(new BorderLayout(15, 15));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));

        // --- Play Panel ---
        JPanel playPanel = new JPanel(new BorderLayout(10, 10));
        playPanel.setOpaque(false);
        playPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(122, 191, 184), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        playTextArea = new JTextArea(12, 40);
        playTextArea.setEditable(false);
        playTextArea.setFont(new Font("Segoe UI Emoji", Font.BOLD, 17));
        playTextArea.setBackground(new Color(255, 255, 250, 215));
        playTextArea.setForeground(new Color(36, 38, 65));
        playTextArea.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(0, 204, 102), 2, true),
                "🎯 Game Feed",
                TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Segoe UI Emoji", Font.BOLD, 17)
        ));
        JScrollPane playScrollPane = new JScrollPane(playTextArea);

        JButton playButton = new JButton("▶ Play Round 🎲");
        playButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        playButton.setBackground(new Color(36, 179, 79));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playButton.setBorder(new CompoundBorder(
                new LineBorder(new Color(12, 119, 9), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        playButton.addActionListener(e -> {
            String roundResult = engine.playRound();
            playTextArea.append("🎉 Round Played:\n" + roundResult + "\n");
            playTextArea.setCaretPosition(playTextArea.getDocument().getLength());

            playPanel.setBackground(new Color(255, 252, 200));
            Timer timer = new Timer(400, evt -> playPanel.setBackground(null));
            timer.setRepeats(false);
            timer.start();
        });

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.setOpaque(false);
        playButtonPanel.add(playButton);

        playPanel.add(playButtonPanel, BorderLayout.NORTH);
        playPanel.add(playScrollPane, BorderLayout.CENTER);

        // --- Result Panel ---
        JPanel resultPanel = new JPanel(new BorderLayout(10, 10));
        resultPanel.setOpaque(false);
        resultPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(99, 104, 255), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        resultTextArea = new JTextArea(12, 40);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Segoe UI Emoji", Font.BOLD, 17));
        resultTextArea.setBackground(new Color(255, 255, 255, 220));
        resultTextArea.setForeground(new Color(90, 27, 90));
        resultTextArea.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(99, 202, 255), 2, true),
                "🏆 Game Leaderboard",
                TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Segoe UI Emoji", Font.BOLD, 17)
        ));
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        JButton resultButton = new JButton("🔃 Refresh Results");
        resultButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        resultButton.setBackground(new Color(99, 104, 255));
        resultButton.setForeground(Color.WHITE);
        resultButton.setFocusPainted(false);
        resultButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resultButton.setBorder(new CompoundBorder(
                new LineBorder(new Color(36, 38, 65), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        resultButton.addActionListener(e -> {
            String result = engine.showResults();
            resultTextArea.setText(result + "\n");
            resultTextArea.setCaretPosition(0);

            resultPanel.setBackground(new Color(218, 252, 255));
            Timer timer = new Timer(400, evt -> resultPanel.setBackground(null));
            timer.setRepeats(false);
            timer.start();
        });

        JPanel resultButtonPanel = new JPanel();
        resultButtonPanel.setOpaque(false);
        resultButtonPanel.add(resultButton);

        resultPanel.add(resultButtonPanel, BorderLayout.NORTH);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("🎲 Play Round", playPanel);
        tabbedPane.addTab("📋 See Results", resultPanel);

        backgroundPanel.add(tabbedPane, BorderLayout.CENTER);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}
