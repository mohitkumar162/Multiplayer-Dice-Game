import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class GameEngine {
    private ArrayList<Player> players;
    private Dice dice;

    public GameEngine(ArrayList<Player> players) {
        this.players = players;
        this.dice = new Dice();
    }

    public String playRound() {
        int highestRoll = 0;
        Player winner = null;
        StringBuilder sb = new StringBuilder();
        for (Player p : players) {
            int roll = dice.roll();
            sb.append("🎲 ").append(p.showName()).append(" rolled: ").append(roll).append("\n");
            if (roll > highestRoll) {
                highestRoll = roll;
                winner = p;
            } else if (roll == highestRoll) {
                winner = null; // tie
            }
        }
        if (winner != null) {
            sb.append("🏆 Winner: ").append(winner.showName()).append("!\n");
            winner.incrementWin();
        } else {
            sb.append("🤝 It's a tie! No winner this round.\n");
        }
        saveResultToFile(sb.toString());
        return sb.toString();
    }

    public String showResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("📊 Game Results:\n");
        for (Player p : players) {
            sb.append("✨ ").append(p.showName())
                    .append(" won ").append(p.showWins()).append(" rounds.\n");
        }
        return sb.toString();
    }

    private void saveResultToFile(String result) {
        try (FileWriter writer = new FileWriter("results.txt", true)) {
            writer.write(result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
