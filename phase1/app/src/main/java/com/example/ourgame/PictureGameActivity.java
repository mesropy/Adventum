package com.example.ourgame;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PictureGameActivity extends AppCompatActivity {

    private PictureGame pictureGame;

    // Views (UI elements) to keep track of
    private ImageView imageToGuess;
    private TextView guessResultText;
    private EditText guessEditText;
    private Button continueButton;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        pictureGame = new PictureGame();

        imageToGuess = findViewById(R.id.imageToGuess);
        guessEditText = findViewById(R.id.guessEditText);
        guessResultText = findViewById(R.id.guessResultText);
        continueButton = findViewById(R.id.continueButton);
        enterButton = findViewById(R.id.enterButton);

        imageToGuess.setImageResource(pictureGame.getCurrentImageResource());
        continueButton.setVisibility(View.GONE);
    }

    // finish this:
    /*
    private void displayInstructions(){
        Intent intent = new Intent(this, Instructions.class);
        intent.putExtra("instructions", pictureGame.getInstructions());
        startActivity(intent);
    }
    */

    // call when enter button is pressed, player is entering the guess they inputted in edit text
    public void onEnterGuessPressed(View view) {

        String guess = guessEditText.getText().toString();
        if (pictureGame.guessCorrect(guess)) {
            onCorrectGuess();
        } else {
            onIncorrectGuess();
        }
    }

    private void onCorrectGuess() {
        guessResultText.setText(pictureGame.getCorrectGuessMessage());
        // can later highlight animal in image (not doing this now)
        // TODO: display stats for this level
        // TODO: increase points (for game not overall)

        // Show next level button
        continueButton.setVisibility(View.VISIBLE);
        // make guess edit text not editable, and remove enter button
        guessEditText.setEnabled(false);
        enterButton.setVisibility(View.GONE);
    }

    private void onIncorrectGuess() {
        guessResultText.setText(pictureGame.getIncorrectGuessMessage());

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

        pictureGame.incrementNumTries();
        if (pictureGame.levelFailed()){
            displayLevelFailed();
        }
    }

    public void onNextButtonPressed(View view) {
        if (pictureGame.reachedLastLevel()) {
            // TODO: calculate time taken
            // TODO: save all stats to file
            // TODO: transition to stats page, then to game over screen
        } else {
            pictureGame.nextLevel();
            pictureGame.resetNumTries();
            displayNextLevel();
        }
    }

    private void displayNextLevel() {
        // show next image
        imageToGuess.setImageResource(pictureGame.getCurrentImageResource());
        // remove next button, result text, and text in guess editText
        continueButton.setVisibility(View.GONE);
        guessResultText.setText("");
        guessEditText.setText("");
        // make guess editText editable, and enter button visible
        guessEditText.setEnabled(true);
        enterButton.setVisibility(View.VISIBLE);
    }

    private void displayLevelFailed(){
        // TODO: show that level failed
        // TODO: update stats? (not overall stats in file, but stats for this game)
        // Show next level button
        continueButton.setVisibility(View.VISIBLE);
        // make guess edit text not editable, and remove enter button
        guessEditText.setEnabled(false);
        enterButton.setVisibility(View.GONE);
    }
}
