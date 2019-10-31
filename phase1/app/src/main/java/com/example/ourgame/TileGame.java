package com.example.ourgame;

import java.util.ArrayList;
import java.util.Collections;

public class TileGame extends Game {

    private int lives;
    private int currentNumRoundLives;
    private int livesPerRound = 2;

    private int points;
    private int correctPressed;

    private int numTiles;
    private int numRightTiles;
    // whether or not the tile is green / "right", goes in order
    // from left to right, then top to bottom
    private ArrayList<Boolean> rightTile = new ArrayList<>();

    // tile images
    private int rightTileImageId;
    private int wrongTileImageId;
    private int unflippedTileImageId;

    // time in milliseconds that will show pattern before flipping tiles
    private int patternShowTime;
    // time in milliseconds that will show pattern before moving on to then next level / game
    private int patternEndShowTime;

    TileGame() {
        super();

        lives = 3;
        currentNumRoundLives = livesPerRound;
        points = 0;
        correctPressed = 0;
        numTiles = 9;
        numRightTiles = 4;
        patternShowTime = 1000;
        patternEndShowTime = 2000;
        rightTileImageId = R.drawable.flipped_right;
        wrongTileImageId = R.drawable.flipped_wrong;
        unflippedTileImageId = R.drawable.unflipped;

        // initialize rightTile based on num right/wrong tiles we have
        for (int i = 0; i < numRightTiles; i++) {
            rightTile.add(true);
        }
        for (int i = numRightTiles; i < numTiles; i++) {
            rightTile.add(false);
        }
    }

    @Override
    void updateStatistics() {

    }

    boolean noMoreLives(){
        return lives <= 0;
    }

    void loseLife() {
        lives--;
    }

    void resetRoundLives() {
        this.currentNumRoundLives = livesPerRound;
    }

    void loseRoundLife() {
        currentNumRoundLives--;
    }

    boolean noMoreRoundLives(){
        return currentNumRoundLives <= 0;
    }

    // call to save stats
    public int getPoints() {
        return points;
    }

    void addPoint() {
        points++;
    }

    void resetCorrectPressed() {
        this.correctPressed = 0;
    }

    void incrementCorrectPressed() {
        correctPressed++;
    }

    boolean allRightTilesPressed(){
        return correctPressed == numRightTiles;
    }

    ArrayList<Boolean> getRightTile() {
        return rightTile;
    }

    void shuffleTiles(){
        Collections.shuffle(rightTile);
    }

    int getRightTileImageId() {
        return rightTileImageId;
    }

    int getWrongTileImageId() {
        return wrongTileImageId;
    }

    int getUnflippedTileImageId() {
        return unflippedTileImageId;
    }

    int getPatternShowTime() {
        return patternShowTime;
    }

    int getPatternEndShowTime() {
        return patternEndShowTime;
    }

    String getLoseLifeText(){
        return "You lost a life!";
    }

    String getLivesRemainingText(){
        return "Lives Remaining: " + lives + "/3";
    }
}
