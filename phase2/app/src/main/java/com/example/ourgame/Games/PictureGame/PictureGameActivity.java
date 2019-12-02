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

import com.example.ourgame.Languages.Language;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;

public class PictureGameActivity extends AppCompatActivity implements PictureGameView {

    private Language language;
    private PictureGamePresenter presenter;

    // Views (UI elements) to keep track of
    private ImageView imageToGuess;
    private TextView guessResultText;
    private EditText guessEditText;
    private Button continueButton;
    private Button enterButton;
    private TextView numAttemptsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        imageToGuess = findViewById(R.id.imageToGuess);
        continueButton = findViewById(R.id.continueButton);
        guessEditText = findViewById(R.id.guessEditText);
        guessResultText = findViewById(R.id.guessResultText);
        enterButton = findViewById(R.id.enterButton);
        numAttemptsText = findViewById(R.id.numAttemptsText);

        presenter = new PictureGamePresenter(this, new PictureGame(this));
    }

    @Override
    public void setLanguage(Language lang) {
        language = lang;
    }

    @Override
    public void setInitial() {
        TextView titleText = findViewById(R.id.titleText);
        TextView numAttemptsLabel = findViewById(R.id.numAttemptsLabel);
        titleText.setText(language.getPictureTitle());
        numAttemptsLabel.setText(language.getPictureNumAttempts());
        continueButton.setText(language.getContinue());
        enterButton.setText(language.getEnter());
        guessEditText.setHint(language.typeAnswer());

        // set theme
        ConstraintLayout constraintLayout = findViewById(R.id.picturegameLayout);
        constraintLayout.setBackgroundResource(new DataWriter(this).getThemeData());
    }

    @Override
    public void displayLevel(String numAttempts, int levelImage) {
        displayNumAttempts(numAttempts);
        imageToGuess.setImageResource(levelImage);
        continueButton.setVisibility(View.GONE);
        guessResultText.setText("");
        guessEditText.setText("");
        guessEditText.setEnabled(true);
        enterButton.setEnabled(true);
    }

    @Override
    public void displayNumAttempts(String numAttempts){
        numAttemptsText.setText(numAttempts);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    // call when enter button is pressed, player is entering the guess they inputted in edit text
    public void onEnterGuessPressed(View view) {
        String guess = guessEditText.getText().toString();
        presenter.guessEntered(guess);
    }

    @Override
    public void displayCorrectGuess() {
        guessResultText.setText(language.getTileResultTextCorrect());
        continueButton.setVisibility(View.VISIBLE);
        guessEditText.setEnabled(false);
        enterButton.setEnabled(false);
    }

    @Override
    public void displayIncorrectGuess() {
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
    }

    public void onContinuePressed(View view) {
        presenter.nextLevel();
    }


    @Override
    public void displayUsedAllAttempts() {
        guessResultText.setText(language.getPictureNoMoreAttempts());
        continueButton.setVisibility(View.VISIBLE);
        guessEditText.setEnabled(false);
        enterButton.setEnabled(false);
    }

}
