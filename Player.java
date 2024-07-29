import java.util.Objects;

public class Player {
    private final String name;
    private int score;
    private boolean isTurnToPlay;
    private boolean isFirst;
    private String token;

    public Player(String name, boolean isFirst) {
        this.score = 0;
        this.name = name;
        this.isFirst = isFirst;
        this.token = this.isFirst? "O" : "X";
        this.isTurnToPlay = isFirst;
    }

    public Player(String name) {
        this.score = 0;
        this.name = name;
        this.isFirst = false;
        this.token = "X";
        this.isTurnToPlay = false;
    }

    public Player(boolean isBot) {
        if (isBot)
            this.score = 0;
        this.isFirst = true;
        this.token = "O";
        this.name = "Computer";
        this.isTurnToPlay = true;

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isTurnToPlay() {
        return isTurnToPlay;
    }

    public String getToken() {
        return token;
    }

    public void setScore() {
        this.score ++;
    }

    public void setTurnToPlay(boolean isTurnToPlay) {
        this.isTurnToPlay = isTurnToPlay;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
        isTurnToPlay = isFirst;
        token = this.isFirst? "O" : "X";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
