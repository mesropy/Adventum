package com.example.ourgame.Games;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Languages.Language;
import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Utilities.WriteData;

public class InstructionsActivity extends AppCompatActivity {

    private ScreenLoader screenLoader;
    private WriteData data;
    private Language language;
    private String gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        screenLoader = new ScreenLoader(this);
        data = new DataWriter(this);
        LanguageTextSetter text = new LanguageTextSetter(data.getLanguage(data.getUser()), this);
        language = text.getTextsetter();

        gameName = getIntent().getStringExtra("game");

        setTexts();
        setTheme();
    }

    private void setTexts() {
        TextView gameNameTitleText = findViewById(R.id.gameNameText);
        TextView instructionsText = findViewById(R.id.instructionsText);
        TextView instructionsSubtitleText = findViewById(R.id.instructionsSubtitleText);
        Button playGameButton = findViewById(R.id.playGameButton);

        gameNameTitleText.setText(getGameTitle());
        instructionsText.setText(getInstructions());

        // set languages for these
        //instructionsSubtitleText.setText(language.getInstructionsSubtitle());
        //playGameButton.setText(language.getPlayGameButton());
    }

    private void setTheme(){
        ConstraintLayout constraintLayout = findViewById(R.id.instructionsLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(data.getThemeData(data.getUser()));
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.mainActivityLayout());
    }

    private String getInstructions() {
        switch (gameName) {
            case "Hangman":
                return "";
            //language.getHangmanInstruction();
            case "Picture":
                return language.getPictureInstruction();
            case "Reaction":
                return language.getReactionMessageInstruction();
            case "Running":
                return " ";
            //language.getRunnerInstruction();
            default:  // Tile
                // TODO: combine other 2 instructions and the images
                return language.getTileIntroduction1();
        }
    }

    private String getGameTitle(){
        switch (gameName) {
            case "Hangman":
                return language.getHangmanTitle();
            //language.getHangmanInstruction();
            case "Picture":
                return language.getPictureTitle();
            case "Reaction":
                return language.getReactionTitle();
            case "Running":
                // language.getRunningTitle();
                return "";
            //language.getRunnerInstruction();
            default:  // Tile
                return language.getTileTitle();
        }
    }

    public void onPlayGamePressed(View view) {
        screenLoader.loadGame(gameName);
    }

}
