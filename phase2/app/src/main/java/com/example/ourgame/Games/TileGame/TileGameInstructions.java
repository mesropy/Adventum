package com.example.ourgame.Games.TileGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;

/**
 * An Activity class for the Instructions of the Tile Game
 */
public class TileGameInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game_instructions);

        TextView title = findViewById(R.id.titleText2);
        TextView title2 = findViewById(R.id.titleText);
        TextView intro1 = findViewById(R.id.instructions1);
        TextView intro2 = findViewById(R.id.instructions2);
        TextView intro3 = findViewById(R.id.instructions3);
        Button start = findViewById(R.id.start);

        DataWriter dataWriter = new DataWriter(this);
        String user = dataWriter.getCurrentUser();
        LanguageTextSetter text = new LanguageTextSetter(dataWriter.getLanguage(), this);
        Language language = text.getTextSetter();
        title.setText(language.getTileTitle());
        title2.setText(language.instruction());
        start.setText(language.start());
        intro1.setText(language.getTileIntroduction1());
        intro2.setText(language.getTileIntroduction2());
        intro3.setText(language.getTileIntroduction3());

        ConstraintLayout constraintLayout = findViewById(R.id.tileIntroLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(dataWriter.getThemeData());
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.hangmanActivityLayout());
    }

    /**
     * Proceeds to the actual Tile Game once the user reads the instructions and taps the play
     * button
     * @param view the button that was tapped by the user
     */
    public void playTileGameView(View view) {
        Intent intent = new Intent(this, TileGameActivity.class);
        startActivity(intent);
        finish();
    }
}
