package com.example.ourgame;

abstract class Game {

    private int playTime; // TODO: set up timer with this
    private int pointsEarned; // from this game

    Game() {
        this.pointsEarned = 0;
    }

    // TODO: use these methods with timer
    // TODO: add methods for timer (start, stop, etc.)

    int getPlayTime() {
        return playTime;
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
