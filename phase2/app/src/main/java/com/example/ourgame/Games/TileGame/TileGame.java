package com.example.ourgame.Games.TileGame;

import android.content.Context;

import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.Games.Game;
import com.example.ourgame.MainActivity;
import com.example.ourgame.R;

import java.util.ArrayList;
import java.util.Collections;


class TileGame extends Game {

    private int lives = 3;
    private int currentLives;
    private int currentNumRoundLives;
    private int livesPerRound = 2;

    private int points;
    private int correctPressed;

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

    private int playTime = 0;

    TileGame(Context activity) {
        super();

        currentLives = lives;
        currentNumRoundLives = livesPerRound;
        points = 0;
        correctPressed = 0;
        numRightTiles = 4;
        patternShowTime = 3000;
        patternEndShowTime = 2000;
        rightTileImageId = R.drawable.flipped_right;
        wrongTileImageId = R.drawable.flipped_wrong;
        unflippedTileImageId = R.drawable.unflipped;
        setData(new DataWriter(activity));
    }

    /**
     * Called once the Tile Game activity ends. Updates and saves the statistics of this game to be
     * displayed in the player's statistics page.
     */
    @Override
    protected void updateStatistics() {

        int statPoints = 2 * points / 10;

        getData().addPoints(getUser(), statPoints);
        getData().addPlayTime(getUser(), playTime);
        //need to fix getString(R.string.tile_game)
        getData().addLastGame(getUser(), "Tile");

        if (updateRanking()) {
            getData().increaseRanking(getUser());
        }
    }

    void setInitTiles(int numberOfTiles){
        rightTile.clear();

        numRightTiles = numberOfTiles / 2;
        for(int i = 0; i < numRightTiles; i++){
            rightTile.add(true);
        }
        for(int i = numRightTiles; i < numberOfTiles; i++){
            rightTile.add(false);
        }

    }

    private boolean updateRanking() {
        return playTime <= (50 * 3 * patternShowTime * patternEndShowTime) &&
                currentLives == lives; // didn't lose any lives / won all rounds
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

    // call to save stats
    int getPoints() {
        return points;
    }

    void addPoint() {
        points++;
    }

    void setPlayTime(int playTime) {
        this.playTime = playTime;
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

    void shuffleTiles() {
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
        int time = patternShowTime;
        if(patternShowTime > 500) {
            patternShowTime = patternShowTime - 200;
        }
        return time;
    }

    void resetShowTime() {
        patternShowTime = 3000;
    }

    int getPatternEndShowTime() {
        return patternEndShowTime;
    }

    public int getCurrentLives() {
        return currentLives;
    }
}
