package com.example.ourgame.Games.TileGame;

import java.util.ArrayList;

class LifeCalculator {
    private int lives = 3;
    private int currentLives;
    private int currentNumRoundLives;
    private int livesPerRound = 2;

    private int correctPressed;
    private int numRightTiles;
    // whether or not the tile is green / "right", goes in order
    // from left to right, then top to bottom
    private ArrayList<Boolean> rightTile = new ArrayList<>();

    LifeCalculator(){
        currentLives = lives;
        currentNumRoundLives = livesPerRound;
        correctPressed = 0;
        numRightTiles = 4;
    }

    int getCurrentLives() {
        return currentLives;
    }

    boolean noMoreLives() {
        return currentLives <= 0;
    }

    void loseLife() {
        currentLives--;
    }

    void resetRoundLives() {
        this.currentNumRoundLives = livesPerRound;
    }

    void loseRoundLife() {
        currentNumRoundLives--;
    }

    boolean noMoreRoundLives() {
        return currentNumRoundLives <= 0;
    }

    void resetCorrectPressed() {
        this.correctPressed = 0;
    }

    void incrementCorrectPressed() {
        correctPressed++;
    }

    boolean allRightTilesPressed() {
        return correctPressed == numRightTiles;
    }

    ArrayList<Boolean> getRightTile() {
        return rightTile;
    }

    int getLives() {
        return lives;
    }

    /**
     * Sets up the tiles in this game by setting them to either be correct green tiles or incorrect
     * red tiles the player can tap.
     *
     * @param numberOfTiles the number of tiles used in this TileGame
     */
    void setTileTypes(int numberOfTiles) {

        rightTile.clear();
        numRightTiles = numberOfTiles / 2;
        for (int i = 0; i < numberOfTiles; i++) {

            if (i < numRightTiles) {
                rightTile.add(true);
            } else {
                rightTile.add(false);
            }
        }
    }
}
