package com.example.ourgame.Menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Games.HangmanGame.HangmanActivity;
import com.example.ourgame.Games.PictureGame.PictureGameActivity;
import com.example.ourgame.Games.ReactionGame.ReactionGameActivity;
import com.example.ourgame.Games.RunningGame.EndlessRunnerActivity;
import com.example.ourgame.Games.TileGame.TileGameActivity;
import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.login.Login;

/**
 * An activity class for the main homepage the user is brought to once they register or sign in
 */
public class MainActivity extends AppCompatActivity {

    public String user;
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
        ImageView characterImage = findViewById(R.id.characterImage);

        data = new DataWriter(this);
        user = data.getCurrentUser();
        setLanguage();

        characterImage.setImageResource(data.getCharacterData());

        ThemeBuilder themeBuilder = new ThemeBuilder(data.getThemeData());
        Theme theme = themeBuilder.getTheme();
        ConstraintLayout constraintLayout = findViewById(R.id.mainactivityLayout);
        constraintLayout.setBackgroundResource(theme.mainActivityLayout());
    }

    private void setLanguage() {
        LanguageTextSetter languageTextSetter = new LanguageTextSetter(data.getLanguage(),
                this);
        Language language = languageTextSetter.getTextSetter();

        String welcomeMessage = language.getWelcomeMessage(user);
        playButton.setText(language.getPlayButton());
        statisticsButton.setText(language.statistics());
        leaderBoardButton.setText(language.getMainLeaderBoard());
        settingsButton.setText(language.getMainSettings());
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


    // clear all previous users and go back to login page
    public void clearData(View view){
        data.clearUserData();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, TileGameActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Picture Game -- TESTING ONLY
     */
    public void playPictureGame(View view) {
        Intent intent = new Intent(this, PictureGameActivity.class);
        startActivity(intent);
        finish();
    }

}
