import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextArea resultsTextArea;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private int playerWins, computerWins, ties;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setLayout(new BorderLayout());

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        rockButton = new JButton(new ImageIcon(getClass().getResource("rock_icon.png")));
        paperButton = new JButton(new ImageIcon(getClass().getResource("paper_icon.png")));
        scissorsButton = new JButton(new ImageIcon(getClass().getResource("scissors_icon.png")));
        quitButton = new JButton("Quit");

        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);
        add(buttonsPanel, BorderLayout.NORTH);

        // Results Panel
        resultsTextArea = new JTextArea(10, 40);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);
        add(statsPanel, BorderLayout.SOUTH);

        // Button Action Listeners
        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void playGame(String playerMove) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        String computerMove = moves[random.nextInt(moves.length)];

        // Determine winner
        String result;
        if (playerMove.equals(computerMove)) {
            result = "It's a tie!";
            ties++;
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            result = "Player wins!";
            playerWins++;
        } else {
            result = "Computer wins!";
            computerWins++;
        }

        // Update stats and display result
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
        resultsTextArea.append(playerMove + " vs " + computerMove + " - " + result + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}