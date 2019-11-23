package com.example.ourgame.Games;

public abstract class Game {

    private int pointsEarned; // not total points

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


}
