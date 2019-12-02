package com.example.ourgame.Games.TileGame;

import android.content.Context;
import android.widget.Button;

import com.example.ourgame.Games.GameName;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Games.Game;
import com.example.ourgame.R;

import java.util.ArrayList;
import java.util.Collections;


class TileGame extends Game {

    private LifeCalculator lifeCalculator = new LifeCalculator();
    private ShowTimeManager showTimeManager = new ShowTimeManager();
    private TileManager tileManager = new TileManager();
    private PointCalculator pointCalculator = new PointCalculator();


    TileGame(Context context) {
        super(GameName.TILE, new DataWriter(context));
    }

    void updatePoints() {
        addPointsEarned(2 * getTilePoints() / 10);
    }

    void addTilePoint() {
        pointCalculator.addTilePoint();
    }

    int getTilePoints() {
        return pointCalculator.getTilePoints();
    }

    @Override
    public boolean canUpdateRanking() {
        return getPlayTime() <= (50 * 3 * showTimeManager.getPatternShowTime() * showTimeManager.getPatternEndShowTime()) &&
                lifeCalculator.getCurrentLives() == lifeCalculator.getLives(); // didn't lose any lives / won all rounds
    }

    /**
     * Removes the previous set of game tiles and adds in new tiles received by an ArrayList
     *
     * @param tiles an ArrayList containing the new set of tiles
     */
    void addTiles(ArrayList<Tile> tiles) {
        tileManager.addTiles(tiles);
    }

    /**
     * Return the tile object with the associated button object in this game's ArrayList of tiles.
     * Return null if the tile is not found.
     *
     * @param button the button of the tile object
     * @return the tile that contains the button object that was passed in
     */
    Tile getTileByButton(Button button) {
        return tileManager.getTileByButton(button);
    }

    /**
     * Sets the onClickListener methods for the tiles in this game.
     *
     * @param activity the TileGameActivity that the buttons are present in
     */
    void setOnClickListeners(TileGameActivity activity) {
        tileManager.setOnClickListeners(activity);
    }

    /**
     * Return an ArrayList containing the tiles of this game.
     *
     * @return an ArrayList containing tile objects
     */
    ArrayList<Tile> getTiles() {
        return tileManager.getTiles();
    }

    /**
     * Method to shuffle the locations of correct and incorrect tiles once the player loses or
     * wins a round.
     */
    void shuffleTiles() {
        Collections.shuffle(lifeCalculator.getRightTile());

        for (int i = 0; i < lifeCalculator.getRightTile().size(); i++) {

            if (lifeCalculator.getRightTile().get(i)) {
                tileManager.getTiles().get(i).setRightTile();
            } else {
                tileManager.getTiles().get(i).setWrongTile();
            }
        }
    }

    void setStartTime(long startTime) {
        showTimeManager.setStartTime(startTime);
    }

    long getStartTime() {
        return showTimeManager.getStartTime();
    }

    /**
     * Sets up the tiles in this game by setting them to either be correct green tiles or incorrect
     * red tiles the player can tap.
     *
     * @param numberOfTiles the number of tiles used in this TileGame
     */
    void setTileTypes(int numberOfTiles) {
        lifeCalculator.setTileTypes(numberOfTiles);
    }

    boolean noMoreLives() {
        return lifeCalculator.noMoreLives();
    }

    void loseLife() {
        lifeCalculator.loseLife();
    }

    void resetRoundLives() {
        lifeCalculator.resetRoundLives();
    }

    void loseRoundLife() {
        lifeCalculator.loseRoundLife();
    }

    boolean noMoreRoundLives() {
        return lifeCalculator.noMoreRoundLives();
    }

    void resetCorrectPressed() {
        this.lifeCalculator.resetCorrectPressed();
    }

    void incrementCorrectPressed() {
        lifeCalculator.incrementCorrectPressed();
    }

    boolean allRightTilesPressed() {
        return lifeCalculator.allRightTilesPressed();
    }

    ArrayList<Boolean> getRightTile() {
        return lifeCalculator.getRightTile();
    }

    int getPatternShowTime() {
        return showTimeManager.getPatternShowTime();
    }

    void resetShowTime() {
        showTimeManager.resetShowTime();
    }

    int getPatternEndShowTime() {
        return showTimeManager.getPatternEndShowTime();
    }

    int getCurrentLives() {
        return lifeCalculator.getCurrentLives();
    }
}
