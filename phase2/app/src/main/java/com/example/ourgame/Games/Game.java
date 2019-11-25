package com.example.ourgame.Games;

import com.example.ourgame.Statistics.WriteData;

public abstract class Game {

    private int pointsEarned; // not total points
    private String user;
    private WriteData data;

    protected Game() {
        this.pointsEarned = 0;
    }

    protected int getPointsEarned() {
        return pointsEarned;
    }

    protected void addPointsEarned(int points) {
        pointsEarned += points;
    }

    /**
     * Update the statistics of this game, as well as the overall game. Call this at the
     * end of each level or at the end of the game
     */
    protected abstract void updateStatistics();

    public String getUser() {
        return data.getUser();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public WriteData getData() {
        return data;
    }

    protected void setData(WriteData data) {
        this.data = data;
    }
}
