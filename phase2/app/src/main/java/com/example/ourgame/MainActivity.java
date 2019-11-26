package com.example.ourgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourgame.Games.HangmanGame.HangmanActivity;
import com.example.ourgame.Games.PictureGame.PictureInstructions;
import com.example.ourgame.Games.ReactionGame.ReactionGameActivity;
import com.example.ourgame.Games.RunningGame.EndlessRunnerActivity;
import com.example.ourgame.Games.TileGame.TileGameInstructions;
import com.example.ourgame.Statistics.DataWriter;

/**
 * An activity class for the main homepage the user is brought to once they register or sign in
 */
public class MainActivity extends AppCompatActivity {

    public static String user;
    private DataWriter data;
    private ScreenLoader screenLoader;

    private TextView welcomeText;
    private Button playButton;
    private Button statisticsButton;
    private Button leaderBoardButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenLoader = new ScreenLoader(this);

        welcomeText = findViewById(R.id.welcomeText);
        playButton = findViewById(R.id.playButton);
        statisticsButton = findViewById(R.id.statisticsButton);
        leaderBoardButton = findViewById(R.id.leaderBoardButton);
        settingsButton = findViewById(R.id.settingsButton);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        data = new DataWriter(this);
        if (data.getUser().equals("Not found")){
            data.setUser(user);
        }

        setLanguage();
    }

    private void setLanguage() {
        String language = data.getLanguage(user);

        String welcomeMessage;
        if (language.equals("english")) {
            welcomeMessage = "Welcome " + user + "!";
            playButton.setText("Play");
            statisticsButton.setText("Statistics");
            leaderBoardButton.setText("LeaderBoard");
            settingsButton.setText("Settings");
        } else if (language.equals("french")) {
            welcomeMessage = "Bienvenu " + user + "!";
            playButton.setText("Jouer");
            statisticsButton.setText("Statistiques");
            leaderBoardButton.setText("Classement");
            settingsButton.setText("Réglages");
        } else { // spanish
            welcomeMessage = "Welcome " + user + "!";
        }
        welcomeText.setText(welcomeMessage);
    }


    /**
     * Method to send the user to play the next game
     *
     * @param view the button object that was tapped
     */
    public void playGame(View view) {
        screenLoader.loadNextGame();
    }

    public void checkStats(View view) {
        screenLoader.loadStatistics();
    }

    public void checkLeaderBoard(View view) {
        screenLoader.loadLeaderBoard();
    }

    public void openSettings(View view) {
        screenLoader.loadSettings();
    }

    /**
     * Sends the user to the Reaction Time Game -- TESTING ONLY
     */
    public void playReactionGame(View view){
        Intent intent = new Intent(this, ReactionGameActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Running Game -- TESTING ONLY
     */
    public void playRunningGame(View view){
        Intent intent = new Intent(this, EndlessRunnerActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Hangman Game -- TESTING ONLY
     */
    public void playHangmanGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Tile Game -- TESTING ONLY
     */
    public void playTileGame(View view) {
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Picture Game -- TESTING ONLY
     */
    public void playPictureGame(View view) {
        Intent intent = new Intent(this, PictureInstructions.class);
        startActivity(intent);
        finish();
    }

}
