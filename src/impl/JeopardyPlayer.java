package impl;

import intr.IPlayer;

public class JeopardyPlayer implements IPlayer {
    private final String name;
    private int score = 0;

    public JeopardyPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getNames() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int amount) {
        this.score = amount;
    }
}
