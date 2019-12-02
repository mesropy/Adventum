package com.example.ourgame.Games.HangmanGame;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.R;
import java.io.IOException;

public class HangmanActivity extends AppCompatActivity implements HangmanView{
    private HangmanPresenter presenter;
    private Language language;

    private TextView wordBlanks;
    private TextView resultText;
    private ImageView resultImage;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        wordBlanks = findViewById(R.id.wordBlanks);
        resultText = findViewById(R.id.resultText);
        resultImage = findViewById(R.id.resultImage);
        continueButton = findViewById(R.id.continueButton);

        try {
            presenter = new HangmanPresenter(this, new Hangman(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLetterPressed(View view) {
        presenter.onLetterPressed((Button) view);
    }

    public void OnContinueButtonPressed(View view) {
        presenter.onContinueButtonPressed();
    }


    @Override
    public void setLanguage(Language lang) {
        language = lang;
    }

    @Override
    public void setInitial() {
        continueButton.setText(language.getContinue());
        TextView title = findViewById(R.id.titleText);
        title.setText(language.getHangmanTitle());
    }

    @Override
    public void setUp(String wordBlanks, int imageId) {
        this.wordBlanks.setText(wordBlanks);
        resultText.setText("");

        resultImage.setImageResource(imageId);

        continueButton.setVisibility(View.GONE);
    }

    @Override
    public void setBackground(int themeImageId) {
        ConstraintLayout constraintLayout = findViewById(R.id.hangmanLayout);
        constraintLayout.setBackgroundResource(themeImageId);
}

    @Override
    public void guessLetter(Button letterButton) {
        // disable this letter button
        letterButton.setClickable(false);

        // change color to grey to show that it's disabled
        letterButton.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.lb_grey)));
    }

    @Override
    public void updateBlanks(String wordBlanks) {
        this.wordBlanks.setText(wordBlanks);
    }

    @Override
    public void updateImage(int imageId) {
        resultImage.setImageResource(imageId);
    }

    @Override
    public void showGameWon() {
        resultText.setText(language.getTileResultTextCorrect());
        continueButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGameLost() {
        resultText.setText(language.getPictureNoMoreAttempts());
        continueButton.setVisibility(View.VISIBLE);
    }
}
