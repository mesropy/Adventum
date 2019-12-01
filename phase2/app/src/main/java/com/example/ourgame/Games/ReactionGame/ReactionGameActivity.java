package com.example.ourgame.Games.ReactionGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;
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

    private TextView message;
    private TextView title;
    private TextView countText;
    private TextView averageText;
    private TextView tapToContinue;
    private ImageView filterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time);

        message = findViewById(R.id.instructionText);
        title = findViewById(R.id.titleText);
        countText = findViewById(R.id.numLevelsText);
        averageText = findViewById(R.id.averageText);
        tapToContinue = findViewById(R.id.continueText);
        filterImage = findViewById(R.id.filter);

        presenter = new ReactionGamePresenter(this, new ReactionGame(this));
    }

    public void setInitial() {
        countText.setVisibility(View.INVISIBLE);
        averageText.setVisibility(View.INVISIBLE);
        filterImage.setImageResource(R.color.transparent);
        title.setText(language.getReactionTitle());
        tapToContinue.setText(language.getReactionContinueText());

        // set theme
        ThemeBuilder themeBuilder = new ThemeBuilder((new DataWriter(this)).getThemeData());
        Theme theme = themeBuilder.getTheme();
        ConstraintLayout constraintLayout = findViewById(R.id.main_layout);
        constraintLayout.setBackgroundResource(theme.pictureGameLayout());
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
        filterImage.setImageResource(R.color.stop_red);
        tapToContinue.setText("");
    }

    @Override
    public void showTime(long time) {
        String timeString;
        timeString = time + "ms";
        message.setText(timeString);
        filterImage.setImageResource(R.color.transparent);
        tapToContinue.setText(language.getReactionContinueText());
    }

    @Override
    public void showTooSoon() {
        message.setText(language.getReactionMessageTooSoon());
        filterImage.setImageResource(R.color.transparent);
        tapToContinue.setText(language.getReactionContinueText());
    }

    @Override
    public void showGo() {
        message.setText(language.getReactionMessageGo());
        filterImage.setImageResource(R.color.go_green);
        tapToContinue.setText("");
    }

    @Override
    public void updateScreen(long avg, int count) {
        String countString = count + "/5";
        countText.setText(countString);
        String averageString = avg + "ms";
        averageText.setText(averageString);
    }

    @Override
    public void setLanguage(Language lang) {
        language = lang;
    }

    @Override
    public void showReady() {
        message.setText(language.getReactionReadyMessage());
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
