public class Player {
    private String name;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.wins = 0;
    }

    public int showWins() {
        return wins;
    }

    public String showName() {
        return name;
    }

    public void incrementWin() {
        wins++;
    }
}
