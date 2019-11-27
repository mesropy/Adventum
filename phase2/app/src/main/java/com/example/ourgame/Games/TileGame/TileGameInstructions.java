package com.example.ourgame.Games.TileGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourgame.LanguageSetters.LanguageTextSetter;
import com.example.ourgame.R;
import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.LanguageSetters.TextSetter;
import com.example.ourgame.ThemeSetters.Theme;
import com.example.ourgame.ThemeSetters.ThemeBuilder;

/**
 * An Activity class for the Instructions of the Tile Game
 */
public class TileGameInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game_instructions);

        TextView title = findViewById(R.id.titleText2);
        TextView title2 = findViewById(R.id.title);
        TextView intro1 = findViewById(R.id.instructions1);
        TextView intro2 = findViewById(R.id.instructions2);
        TextView intro3 = findViewById(R.id.instructions3);
        Button start = findViewById(R.id.start);

        DataWriter dataWriter = new DataWriter(this);
        String user = dataWriter.getUser();
        LanguageTextSetter text = new LanguageTextSetter(dataWriter.getLanguage(user));
        TextSetter textSetter = text.getTextsetter();
        title.setText(textSetter.getTileTitle());
        title2.setText(textSetter.instruction());
        start.setText(textSetter.start());
        intro1.setText(textSetter.getTileIntroduction1());
        intro2.setText(textSetter.getTileIntroduction2());
        intro3.setText(textSetter.getTileIntroduction3());

        ConstraintLayout constraintLayout = findViewById(R.id.tileIntroLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(dataWriter.getThemeData(user));
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.HangmanActivityLayout());
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
