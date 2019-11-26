package com.example.ourgame.Games.HangmanGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourgame.R;
import com.example.ourgame.Statistics.StatisticsActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HangmanActivity extends AppCompatActivity {

    private Hangman hangman;

    private TextView wordBlanks;
    private TextView resultText;
    private ImageView resultImage;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        try {
            hangman = new Hangman(getPossibleWords());
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordBlanks = findViewById(R.id.wordBlanks);
        resultText = findViewById(R.id.resultText);

        wordBlanks.setText(hangman.getWordBlanks());
        resultText.setText("");

        resultImage = findViewById(R.id.resultImage);
        resultImage.setImageResource(hangman.getImageId());

        continueButton = findViewById(R.id.continueButton);
        continueButton.setEnabled(false);

    }

    // returns possible words to guess in an array list, words from file
    private List<String> getPossibleWords() throws IOException {
        List<String> possibleWords = new ArrayList<>();
        InputStream inputStream = this.getResources().openRawResource(R.raw.hangman_words);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            possibleWords.add(nextLine);
            nextLine = bufferedReader.readLine();
        }
        return possibleWords;
    }

    protected void onLetterPressed(View view){
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
            resultText.setText("You win!");
        }
    }

    private void onIncorrectGuess(String letter) {
        hangman.updateGuessIncorrect(letter);
        resultImage.setImageResource(hangman.getImageId());
        if (hangman.isGameLost()){
            resultText.setText(R.string.no_more_attempts);
            continueButton.setEnabled(true);
        }
    }

    public void OnContinueButtonPressed(View view) {
        final Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("next activity", "main menu");
        startActivity(intent);
    }


}
