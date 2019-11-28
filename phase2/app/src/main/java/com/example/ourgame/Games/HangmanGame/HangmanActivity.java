package com.example.ourgame.Games.HangmanGame;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.ScreenLoader;

import java.io.IOException;

public class HangmanActivity extends AppCompatActivity {

    private Hangman hangman;
    private ScreenLoader screenLoader;
    private Language language;

    private TextView wordBlanks;
    private TextView resultText;
    private ImageView resultImage;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        try {
            hangman = new Hangman(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        screenLoader = new ScreenLoader(this);


        wordBlanks = findViewById(R.id.wordBlanks);
        resultText = findViewById(R.id.resultText);

        wordBlanks.setText(hangman.getWordBlanks());
        resultText.setText("");

        resultImage = findViewById(R.id.resultImage);
        resultImage.setImageResource(hangman.getImageId());

        continueButton = findViewById(R.id.continueButton);
        continueButton.setVisibility(View.GONE);

        // set language
        LanguageTextSetter text = new LanguageTextSetter(hangman.getLanguage());
        language = text.getTextsetter();
        setLanguage();

    }

    private void setLanguage(){
        continueButton.setText(language.getContinue());
        TextView title = findViewById(R.id.title);
        title.setText(language.getHangmanTitle());
    }

    public void onLetterPressed(View view) {
        // when a letter is pressed, only do something if the game isn't over
        if(!hangman.isGameOver()) {
            String letter = ((Button) view).getText().toString().trim().toLowerCase();

            if (hangman.correctGuess(letter)) {
                onCorrectGuess(letter);
            } else {
                onIncorrectGuess(letter);
            }

            // disable this letter button
            view.setClickable(false);

            // change color to grey to show that it's disabled
            view.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.lb_grey)));
        }
    }

    private void onCorrectGuess(String letter) {
        hangman.updateGuessCorrect(letter);
        wordBlanks.setText(hangman.getWordBlanks());
        if (hangman.isGameWon()){
            onGameWon();
        }
    }

    private void onGameWon() {
        resultText.setText(language.getTileResultTextCorrect());
        hangman.saveStatistics();
        continueButton.setVisibility(View.VISIBLE);
    }

    private void onIncorrectGuess(String letter) {
        hangman.updateGuessIncorrect(letter);
        resultImage.setImageResource(hangman.getImageId());
        if (hangman.isGameLost()){
            onGameLost();
        }
    }

    private void onGameLost() {
        resultText.setText(language.getPictureNoMoreAttempts());
        hangman.saveStatistics();
        continueButton.setVisibility(View.VISIBLE);
    }

    public void OnContinueButtonPressed(View view) {
        screenLoader.loadStatisticsAfterGame();
    }


}
