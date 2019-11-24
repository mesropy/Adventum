package com.example.ourgame.Games;

import com.example.ourgame.Statistics.DataWriter;

public abstract class Game {

    private int pointsEarned; // not total points
    private String user;
    private DataWriter data;

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
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DataWriter getData() {
        return data;
    }

    protected void setData(DataWriter data) {
        this.data = data;
    }
}
