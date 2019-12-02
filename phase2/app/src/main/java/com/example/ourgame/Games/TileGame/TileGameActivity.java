package com.example.ourgame.Games.TileGame;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;


import java.util.ArrayList;

/**
 * The Activity class for a Memory Tile Game
 */

public class TileGameActivity extends AppCompatActivity implements TileGameView, View.OnClickListener{

    private TileGamePresenter presenter;
//    private TileGame tileGame;
    private Language language;

    private TextView livesText;
    private TextView resultText;

    /**
     * Method to initialize items in this activity. Initializes all required variables, objects
     * etc.
     *
     * @param savedInstanceState the previous state or activity that can be restored
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game);

        livesText = findViewById(R.id.livesText);
        resultText = findViewById(R.id.resultText);

        presenter = new TileGamePresenter(this, new TileGame(this));


    }

    /**
     * Method that gets called to randomize and display green tileImageIds upon the player's first
     * starting round.
     */
    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStartGame();
    }

    /**
     * Method to determine what happens on button taps. Adds point or subtracts lives based on
     * the the tile button the user has tapped.
     *
     * @param view the view object that called this method
     */
    @Override
    public void onClick(View view) {
        presenter.onTileClick(view);
    }

    /**
     * Method to determine what happens when a player taps an incorrect tile. Removes a life from
     * the round and checks if the player has no more lives, ending the game if so.
     */
    public void clickedOnWrongTileShow() {
        resultText.setText(language.getTileResultTextInCorrect());
    }

    /**
     * Method to determine what happens when player taps a correct green tile. Increases points and
     * checks if they have clicked all green tiles.
     */
    public void clickedOnARightTileShow() {
        resultText.setText(language.getTileResultTextCorrect());
    }

    /**
     * Method to process what happens on a lost round. Remove the appropriate number of lives and
     * displays the hidden pattern to the player.
     */
    public void roundLost(int life) {
        String s = language.getTileLivesRemain() + life + " /3";
        livesText.setText(s);
        resultText.setText(language.getTileResultTextLost());

    }

    public void startRoundShow() {
        resultText.setText("");
    }
    /**
     * Method that gets called after a round has been won. Shows the player the next set of tiles
     * for them to memorize.
     */
    public void roundWon() {
        resultText.setText(language.getTileResultTextWon());

    }

    @Override
    public void setLanguage(Language lang) {
        language = lang;
    }

    @Override
    public void setInitial() {

        ConstraintLayout constraintLayout = findViewById(R.id.backGround2);
        constraintLayout.setBackgroundResource(new DataWriter(this).getThemeData());

        TextView title = findViewById(R.id.titleText);
        title.setText(language.getTileTitle());
        String s = language.getTileLivesRemain() + "3 /3";
        livesText.setText(s);
    }

    public Button getTile(int id){
        return findViewById(id);
    }
}