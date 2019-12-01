package com.example.ourgame.Games.TileGame;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.ourgame.Languages.Language;
import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;

import java.util.ArrayList;

public class TileGamePresenter {
    private TileGameView gameView;
    private TileGame tileGame;
    private ScreenLoader screenLoader;

    private int[] tileButtonIds = {
            R.id.tile1,
            R.id.tile2,
            R.id.tile3,
            R.id.tile4,
            R.id.tile5,
            R.id.tile6,
            R.id.tile7,
            R.id.tile8,
            R.id.tile9};

    private int[] tileButtonIds2 = {
            R.id.tile21,
            R.id.tile22,
            R.id.tile23,
            R.id.tile24,
            R.id.tile25,
            R.id.tile26,
            R.id.tile27,
            R.id.tile28,
            R.id.tile29,
            R.id.tile210,
            R.id.tile211,
            R.id.tile212,
            R.id.tile213,
            R.id.tile214,
            R.id.tile215,
            R.id.tile216};

    TileGamePresenter(TileGameView view, TileGame game){
        this.gameView = view;
        this.tileGame = game;

        LanguageTextSetter text = new LanguageTextSetter(
                (new DataWriter((Context)gameView)).getLanguage(), (Context) gameView);
        gameView.setLanguage(text.getTextSetter());

        initiateGame();

        screenLoader = new ScreenLoader((Context) gameView);
    }

    void onStartGame(){
        tileGame.setStartTime(System.currentTimeMillis());
        startRound();
    }
    /**
     * Method to determine what happens on button taps. Adds point or subtracts lives based on
     * the the tile button the user has tapped.
     *
     * @param view the view object that called this method
     */
    void onTileClick(View view) {
        Button button = (Button) view;

        Tile tile = tileGame.getTileByButton(button);

        if (tile != null) {
            tile.flipTile();

            if (tile.getIsCorrect()) {
                clickedOnARightTile();
            } else {
                clickedOnWrongTile();
            }
        }
    }

    /**
     * Method to determine what happens when a player taps an incorrect tile. Removes a life from
     * the round and checks if the player has no more lives, ending the game if so.
     */
    private void clickedOnWrongTile() {
        tileGame.loseRoundLife();

        if (tileGame.noMoreRoundLives()) {
            roundLost();
            if (tileGame.noMoreLives()) {
                gameOver();
            } else {
                restartRound();
            }
        } else {
            gameView.clickedOnWrongTileShow();
        }
    }

    /**
     * Method to determine what happens when player taps a correct green tile. Increases points and
     * checks if they have clicked all green tiles.
     */
    private void clickedOnARightTile() {

        tileGame.incrementCorrectPressed();

        if (tileGame.allRightTilesPressed()) {
            roundWon();
        } else {
            gameView.clickedOnARightTileShow();
        }
    }

    /**
     * Method to process what happens on a lost round. Remove the appropriate number of lives and
     * displays the hidden pattern to the player.
     */
    private void roundLost() {
        tileGame.loseLife();
        gameView.roundLost(tileGame.getCurrentLives());
        displayPatternRed();

    }

    /**
     * Method that gets called after a round has been won. Shows the player the next set of tiles
     * for them to memorize.
     */
    private void roundWon() {

        displayPatternRed();

        gameView.roundWon();
        tileGame.addTilePoint();
        if (tileGame.getTilePoints() == 10) {
            tileGame.resetShowTime();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    levelUp();
                }
            }, tileGame.getPatternEndShowTime());
        }
        restartRound();

    }

    /**
     * Method to restart a round after the player has passed a round or failed a round.
     */
    private void restartRound() {
        tileGame.resetRoundLives();
        tileGame.resetCorrectPressed();
        waitThenStartLevel();
    }

    /**
     * Method to start the round by shuffling positions of tiles and displaying them before hiding
     * them.
     */
    void startRound() {
        tileGame.shuffleTiles();
        displayPattern();
        waitThenHidePattern();
        gameView.startRoundShow();
    }

    /**
     * Method to be called when the player loses all their lives and brings them to the game over
     * screen.
     */
    private void gameOver() {
        tileGame.updatePoints();
        //records the time spent playing this game in seconds
        int playTime = Math.toIntExact((System.currentTimeMillis() - tileGame.getStartTime()) / 1000);
        tileGame.setPlayTime(playTime);
        tileGame.saveStatistics();

        // go to next game
        screenLoader.loadStatisticsAfterGame(3000);
    }

    /**
     * Method to display the pattern to the player for them to memorize the locations of correct
     * green tiles.
     */
    private void displayPattern() {

        ArrayList<Tile> temp = tileGame.getTiles();

        for (Tile tile : temp) {

            if (tile.getIsCorrect()) {
                tile.flipTile();
            } else {
                tile.unFlipTile();
            }
            tile.setClickable(false);
        }
    }

    /**
     * Method to display the pattern to the player if they have lost a round. Shows them the correct
     * locations of green tiles along with red tiles for the incorrect locations.
     */
    private void displayPatternRed() {

        ArrayList<Tile> temp = tileGame.getTiles();

        for (Tile tile : temp) {
            tile.flipTile();
            tile.setClickable(false);
        }
    }

    /**
     * Method that delays the game so that the player has a chance to view and memorize the
     * locations of tiles before they become hidden.
     */
    private void waitThenHidePattern() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                ArrayList<Tile> temp = tileGame.getTiles();
                for (Tile tile : temp) {
                    tile.unFlipTile();
                    tile.setClickable(true);
                }
            }
        }, tileGame.getPatternShowTime());
    }

    /**
     * Method to delay the game a bit before starting a new round. Used to show the player the
     * correct pattern they have guessed.
     */
    private void waitThenStartLevel() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startRound();
            }
        }, tileGame.getPatternEndShowTime());
    }

    /**
     * Setup the next level of the game by removing the 3x3 tiles and adding the new 4x4 tiles.
     */
    private void levelUp() {
        ArrayList<Tile> firstRoundTiles = tileGame.getTiles();
        for (Tile tile : firstRoundTiles) {
            tile.setVisibility(false);
            tile.setClickable(false);
        }

        ArrayList<Tile> secondRoundTiles = new ArrayList<>();
        for (int value : tileButtonIds2) {
            secondRoundTiles.add(new Tile(gameView.getTile(value), true));
        }

        tileGame.setTileTypes(tileButtonIds2.length);
        tileGame.addTiles(secondRoundTiles);
        for (Tile tile : secondRoundTiles) {
            tile.setVisibility(true);
            tile.setClickable(true);
        }
        tileGame.setOnClickListeners((TileGameActivity)gameView);
    }



    private void initiateGame(){
        gameView.setInitial();

        ArrayList<Tile> tiles = new ArrayList<>();
        for (int tileButtonId : tileButtonIds) {
            tiles.add(new Tile(gameView.getTile(tileButtonId), true));
        }
        tileGame.addTiles(tiles);
        tileGame.setOnClickListeners((TileGameActivity)gameView);
        tileGame.setTileTypes(tileButtonIds.length);

        for (int value : tileButtonIds2) {
            gameView.getTile(value).setVisibility(View.INVISIBLE);
            gameView.getTile(value).setClickable(false);
        }
    }

}
