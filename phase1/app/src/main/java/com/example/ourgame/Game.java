package com.example.ourgame;

abstract class Game {

    private int pointsEarned; // not total points

    Game() {
        this.pointsEarned = 0;
    }

    int getPointsEarned() {
        return pointsEarned;
    }

    void addPointsEarned(int points) {
        pointsEarned += points;
    }

    /**
     * Update the statistics of this game, as well as the overall game. Call this at the
     * end of each level or at the end of the game
     */
    abstract void updateStatistics();


}
