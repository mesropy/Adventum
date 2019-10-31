package com.example.ourgame;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * The Activity class for a Memory Tile Game
 */

public class TileGameActivity extends AppCompatActivity implements View.OnClickListener {

    TileGame tileGame;

    private ArrayList<Integer> buttonsClicked = new ArrayList<>();
    private TextView livesText;
    private TextView resultText;
    private long startTime = 0;
    private long playTime = 0;

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

    private Button[] tileButtons;

    /**
     * Method to initialize items in this activity. Initializes onClickListener methods for
     * all tileButtonIds.
     *
     * @param savedInstanceState the previous state or activity that can be restored
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game);

        tileGame = new TileGame();

        livesText = findViewById(R.id.livesText);
        resultText = findViewById(R.id.resultText);

        // initialize tileButtons
        tileButtons = new Button[tileButtonIds.length];
        for (int i = 0; i < tileButtonIds.length; i++) {
            tileButtons[i] = findViewById(tileButtonIds[i]);
        }
        // add on click listener for each tile button
        for (Button tileButton : tileButtons) {
            tileButton.setOnClickListener(this);
        }

    }

    /**
     * Method that gets called to randomize and display green tileImageIds upon the player's first
     * starting round.
     */
    @Override
    protected void onStart() {
        startTime = System.currentTimeMillis();
        super.onStart();
        startRound();
    }

    /**
     * Method to determine what happens on button taps. Adds point or subtracts lives based on
     * the the tile button the user has tapped.
     *
     * @param view the view object that called this method
     */
    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        if (!buttonsClicked.contains(button.getId())) {
            buttonsClicked.add(button.getId());

            if (button.getTag() == "wrong") {
                clickedOnWrongTile(button);
            } else {
                clickedOnARightTile(button);
            }
        }
    }

    /**
     * Method to determine what happens when a player taps an incorrect tile. Removes a life from
     * the round and checks if the player has no more lives, ending the game if so.
     *
     * @param button the button object that has been tapped
     */
    private void clickedOnWrongTile(Button button) {
        // flip tile
        button.setBackgroundResource(tileGame.getWrongTileImageId());

        tileGame.loseRoundLife();

        if (tileGame.noMoreRoundLives()) {
            roundLost();
            restartRound();
        } else {
            resultText.setText("INCORRECT!");
        }

        if (tileGame.noMoreLives()) {
            // game ends
            //write into stats file here
            //records the time spent playing this game in minutes
            playTime = (System.currentTimeMillis() - startTime) / 60000;


            // TODO: go to next game?
            //Move onto instructions for PictureGame
            Intent intent = new Intent(this, PictureInstructions.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Method to determine what happens when player taps a correct green tile. Increases points and
     * checks if they have clicked all green tiles.
     *
     * @param button the button object that has been tapped
     */
    private void clickedOnARightTile(Button button) {
        // flip tile
        button.setBackgroundResource(tileGame.getRightTileImageId());

        tileGame.addPoint();
        tileGame.incrementCorrectPressed();

        if (tileGame.allRightTilesPressed()) {
            roundWon();
            restartRound();
        } else {
            resultText.setText("CORRECT!");
        }
    }

    /**
     * Method to process what happens on a lost round. Remove the appropriate number of lives and
     * displays the hidden pattern to the player.
     */
    private void roundLost() {
        tileGame.loseLife();
        livesText.setText(tileGame.getLivesRemainingText());
        resultText.setText(tileGame.getLoseLifeText());
        displayPatternRed();
    }

    /**
     * Method that gets called after a round has been won. Shows the player the next set of tiles
     * for them to memorize.
     */
    private void roundWon() {
        displayPattern(); // removes any red tiles (make this more efficient)
        resultText.setText("You passed this pattern!");
    }

    /**
     * Method to restart a round after the player has passed a round or failed a round.
     */
    private void restartRound() {
        tileGame.resetRoundLives();
        tileGame.resetCorrectPressed();
        buttonsClicked.clear();
        waitThenStartLevel();
    }

    /**
     * Method to start the round by shuffling positions of tiles and displaying them before hiding
     * them.
     */
    private void startRound() {
        tileGame.shuffleTiles();
        displayPattern();
        waitThenHidePattern();
        resultText.setText("");
    }

    /**
     * Method to display the pattern to the player for them to memorize the locations of correct
     * green tiles.
     */
    private void displayPattern() {
        for (int i = 0; i < tileButtons.length; i++) {
            Button tileButton = tileButtons[i];
            int tileImageId;

            // set image id to either right or wrong image
            if (tileGame.getRightTile().get(i)) { // this tile is right / green
                tileImageId = tileGame.getRightTileImageId();
                tileButton.setTag("right");
            } else { // otherwise is wrong, show unflipped (blue)
                tileImageId = tileGame.getUnflippedTileImageId();
                tileButton.setTag("wrong");
            }
            tileButton.setBackgroundResource(tileImageId);

            tileButton.setClickable(false);
        }
    }

    /**
     * Method to display the pattern to the player if they have lost a round. Shows them the correct
     * locations of green tiles along with red tiles for the incorrect locations.
     */
    private void displayPatternRed() {
        for (int i = 0; i < tileButtons.length; i++) {
            Button tileButton = tileButtons[i];
            int tileImageId;

            // set image id to either right or wrong image
            if (tileGame.getRightTile().get(i)) { // this tile is right / green
                tileImageId = tileGame.getRightTileImageId();
            } else { // otherwise is wrong
                tileImageId = tileGame.getWrongTileImageId();
            }
            tileButton.setBackgroundResource(tileImageId);

            tileButton.setClickable(false);
        }
    }

    /**
     * Method that delays the game so that the player has a chance to view and memorize the
     * locations of tiles before they become hidden.
     */
    private void waitThenHidePattern() {
        for (int tileButtonId : tileButtonIds) {
            final Button tileButton = findViewById(tileButtonId);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    tileButton.setBackgroundResource(R.drawable.unflipped);
                    tileButton.setClickable(true);
                }
            }, tileGame.getPatternShowTime());
        }
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

}
