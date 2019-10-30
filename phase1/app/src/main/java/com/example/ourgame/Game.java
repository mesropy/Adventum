package com.example.ourgame;

abstract class Game {

    private int numLevels;
    private int currentLevel;
    private int playTime; // TODO: set up timer with this
    private int pointsEarned; // from this game
    private String instructions; // (put in text file later)

    Game() {
        this.currentLevel = 0;
        this.pointsEarned = 0;
    }

    // try to find a better way to do this:
    abstract void initializeNumLevels();

    abstract void initializeInstructions();

    int getNumLevels() {
        return numLevels;
    }

    void setNumLevels(int numLevels) {
        this.numLevels = numLevels;
    }

    int getCurrentLevel() {
        return currentLevel;
    }

    // TODO: use these methods with timer

    int getPlayTime() {
        return playTime;
    }

    void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    int getPointsEarned() {
        return pointsEarned;
    }

    void addPointsEarned(int points) {
        pointsEarned += points;
    }

    String getInstructions() {
        return instructions;
    }

    void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // TODO: add methods for timer (start, stop, etc.)


    // note: if need to change stats or do other
    void nextLevel() {
        currentLevel++;

        // TODO: stop timer

        updateStatistics();
    }

    /**
     * Update the statistics of this game, as well as the overall game. Call this at the
     * end of each level or at the end of the game
     */
    abstract void updateStatistics();

    /**
     * @return whether or not we've reached the last level
     */
    boolean reachedLastLevel() {
        return currentLevel + 1 >= numLevels;
    }

}
