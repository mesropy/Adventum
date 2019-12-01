package com.example.ourgame.Games.PictureGame;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;

public class PictureGameActivity extends AppCompatActivity {

    private PictureGame pictureGame;
    private Language language;
    private ScreenLoader screenLoader;

    // Views (UI elements) to keep track of
    private ImageView imageToGuess;
    private TextView guessResultText;
    private EditText guessEditText;
    private Button continueButton;
    private Button enterButton;
    private TextView numAttemptsText;

    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        pictureGame = new PictureGame(this);
        screenLoader = new ScreenLoader(this);

        imageToGuess = findViewById(R.id.imageToGuess);
        imageToGuess.setImageResource(pictureGame.getCurrentImageResource());
        continueButton = findViewById(R.id.continueButton);
        continueButton.setVisibility(View.GONE);

        // set language
        LanguageTextSetter text = new LanguageTextSetter(pictureGame.getLanguage(), this);
        language = text.getTextSetter();
        setLanguage();

        // set theme
        ThemeBuilder themeBuilder = new ThemeBuilder(pictureGame.getTheme());
        Theme theme = themeBuilder.getTheme();

        ConstraintLayout constraintLayout = findViewById(R.id.picturegameLayout);
        constraintLayout.setBackgroundResource(theme.pictureGameLayout());

    }

    private void setLanguage() {

        guessEditText = findViewById(R.id.guessEditText);
        guessResultText = findViewById(R.id.guessResultText);
        enterButton = findViewById(R.id.enterButton);
        numAttemptsText = findViewById(R.id.numAttemptsText);
        TextView titleText = findViewById(R.id.titleText);
        TextView numAttemptsLabel = findViewById(R.id.numAttemptsLabel);

        titleText.setText(language.getPictureTitle());
        numAttemptsLabel.setText(language.getPictureNumAttempts());
        continueButton.setText(language.getContinue());
        enterButton.setText(language.getEnter());
        numAttemptsText.setText(pictureGame.getNumAttemptsText());
        guessEditText.setHint(language.typeAnswer());
    }

    @Override
    protected void onStart() {
        startTime = System.currentTimeMillis();
        super.onStart();
    }

    // call when enter button is pressed, player is entering the guess they inputted in edit text
    public void onEnterGuessPressed(View view) {

        pictureGame.incrementNumAttempts();
        numAttemptsText.setText(pictureGame.getNumAttemptsText());

        String guess = guessEditText.getText().toString();

        if (pictureGame.guessCorrect(guess)) {
            onCorrectGuess();
        } else {
            onIncorrectGuess();
        }
    }

    private void onCorrectGuess() {
        guessResultText.setText(language.getTileResultTextCorrect());
        // can later highlight animal in image (not doing this now)
        pictureGame.addPoints();

        // Show next level button
        continueButton.setVisibility(View.VISIBLE);
        // make guess edit text not editable, and remove enter button
        guessEditText.setEnabled(false);
        enterButton.setVisibility(View.GONE);
    }

    private void onIncorrectGuess() {
        guessResultText.setText(language.getTileResultTextInCorrect());

        // if the guess editText is edited, remove the incorrectGuessMessage
        guessEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guessResultText.setText("");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (pictureGame.usedAllAttempts()){
            displayUsedAllAttempts();
        }
    }

    public void onNextButtonPressed(View view) {
        if (pictureGame.reachedLastLevel()) {
            gameOver();
        } else {
            pictureGame.nextLevel();
            pictureGame.resetNumAttempts();
            displayNextLevel();
        }
    }

    private void gameOver(){
        // go to game over page then stats page
        int playTime = Math.toIntExact((System.currentTimeMillis() - startTime) / 1000);
        pictureGame.setPlayTime(playTime);
        pictureGame.saveStatistics();
        screenLoader.loadStatisticsAfterGame();
    }

    private void displayNextLevel() {
        numAttemptsText.setText(pictureGame.getNumAttemptsText());
        // show next image
        imageToGuess.setImageResource(pictureGame.getCurrentImageResource());
        // remove continue button, result text, and text in guess editText
        continueButton.setVisibility(View.GONE);
        guessResultText.setText("");
        guessEditText.setText("");
        // make guess editText editable, and enter button visible
        guessEditText.setEnabled(true);
        enterButton.setVisibility(View.VISIBLE);
    }

    private void displayUsedAllAttempts(){
        guessResultText.setText(language.getPictureNoMoreAttempts());
        // Show next level button
        continueButton.setVisibility(View.VISIBLE);
        // make guess edit text not editable, and remove enter button
        guessEditText.setEnabled(false);
        enterButton.setVisibility(View.GONE);
    }
}
