package JogoEducacional;

public class GameState {
    private static int playerScore = 0;

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int score) {
        playerScore = score;
    }

    public static void addPoints(int points) {
        playerScore += points;
    }
    
    public static int playerpoints(){
        return playerScore;
    }
}
