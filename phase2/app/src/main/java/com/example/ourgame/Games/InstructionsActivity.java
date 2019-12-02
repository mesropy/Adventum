package com.example.ourgame.Games;

import android.content.Intent;
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
        LanguageTextSetter text = new LanguageTextSetter(data.getLanguage(), this);
        language = text.getTextSetter();

        Intent intent = getIntent();
        gameName = intent.getStringExtra("game");

        setTexts();
        setTheme();
    }

    private void setTexts() {
        setGameTitle();
        setInstructions();

        TextView instructionsSubtitleText = findViewById(R.id.instructionsSubtitleText);
        Button playGameButton = findViewById(R.id.playGameButton);
        instructionsSubtitleText.setText(language.getInstructionsSubtitle());
        playGameButton.setText(language.start());
    }


    private void setGameTitle(){
        String gameTitle;

        // TODO: remove switch block, based on how language implemented
        // gameTitle = language.getTitle();

        switch (gameName) {
            case "HANGMAN":
                gameTitle = language.getHangmanTitle();
                break;
            //language.getHangmanInstruction();
            case "PICTURE":
                gameTitle = language.getPictureTitle();
                break;
            case "REACTION":
                gameTitle = language.getReactionTitle();
                break;
            case "RUNNING":
                // language.getRunningTitle();
                gameTitle = "";
                break;
            //language.getRunnerInstruction();
            default:  // Tile
                gameTitle = language.getTileTitle();
                break;
        }

        TextView gameNameTitleText = findViewById(R.id.gameNameText);
        gameNameTitleText.setText(gameTitle);
    }

    private void setInstructions() {
        String instructions;

        // TODO: remove this switch block, based on how language is implemented
        // same as for setTitle()

        switch (gameName) {
            case "HANGMAN":
                instructions = "";
                break;
            //language.getHangmanInstruction();
            case "PICTURE":
                instructions = language.getPictureInstruction();
                break;
            case "REACTION":
                instructions = language.getReactionMessageInstruction();
                break;
            case "RUNNING":
                instructions = " ";
                break;
            //language.getRunnerInstruction();
            default:  // Tile
                instructions = language.getTileIntroduction1() + "\n" + language.getTileIntroduction2() + "\n" +
                language.getTileIntroduction3();
                break;
        }

        TextView instructionsText = findViewById(R.id.instructionsText);
        instructionsText.setText(instructions);
    }

    private void setTheme(){
        ConstraintLayout constraintLayout = findViewById(R.id.instructionsLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(data.getThemeData());
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.mainActivityLayout());
    }

    public void onPlayGamePressed(View view) {
        screenLoader.loadGame(gameName);
    }

}
