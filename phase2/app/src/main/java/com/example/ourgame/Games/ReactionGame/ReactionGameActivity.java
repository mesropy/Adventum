package com.example.ourgame.Games.ReactionGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Languages.Language;

import java.util.Random;

/**
 * An activity class for the Reaction Time Game
 */
public class ReactionGameActivity extends AppCompatActivity implements ReactionGameView  {

    private ReactionGamePresenter presenter;
    private Language language;

    private ConstraintLayout currentLayout;
    private TextView message;
    private TextView title;
    private TextView countText;
    private TextView averageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time);

        currentLayout = findViewById(R.id.main_layout);
        message = findViewById(R.id.instructionText);
        title = findViewById(R.id.titleText);
        countText = findViewById(R.id.numLevelsText);
        countText.setVisibility(View.INVISIBLE);
        averageText = findViewById(R.id.averageText);
        averageText.setVisibility(View.INVISIBLE);

        presenter = new ReactionGamePresenter(this, new ReactionGame(this), new DataWriter(this));
    }

    public void setInitial() {
        TextView tapToContinue = findViewById(R.id.continueText);
        title.setText(language.getReactionTitle());
        tapToContinue.setText(language.getReactionContinueText());
    }

    /**
     * A method to determine what state the screen will go to when tapped, depending on the
     * previous state
     * @param view the button object that was tapped
     */
    public void screenTapped(View view){
      presenter.onScreenTapped();
    }

    @Override
    public void showWaiting() {
        message.setText(language.getReactionMessageWait());
        currentLayout.setBackgroundResource(R.color.error_red);
    }

    @Override
    public void showTime(long time) {
        String timeString;
        timeString = time + "ms";
        message.setText(timeString);
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }

    @Override
    public void showTooSoon() {
        message.setText(language.getReactionMessageTooSoon());
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }

    @Override
    public void showGo() {
        message.setText(language.getReactionMessageGo());
        currentLayout.setBackgroundResource(R.color.go_green);
    }

    @Override
    public void updateScreen(long avg, int count) {
        String countString = count + "/5";
        countText.setText(countString);
        String averageString = avg + "ms";
        averageText.setText(averageString);
    }

    @Override
    public void setLang(Language lang) {
        language = lang;
    }

    @Override
    public void showInstructions() {
        message.setText(language.getReactionMessageInstruction());
    }

    /**
     * A method to display the text and titles on screen after the instructions
     */
    @Override
    public void showStart() {
        message.setTextSize(36);
        title.setVisibility(View.INVISIBLE);
        countText.setVisibility(View.VISIBLE);
        averageText.setVisibility(View.VISIBLE);
    }
}
