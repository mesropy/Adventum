package com.example.ourgame.Games.PictureGame;

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
 * An activity class for the Picture Game instructions
 */
public class PictureInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_instructions);

        TextView instruction = findViewById(R.id.instructionsText);
        TextView title = findViewById(R.id.titleText4);
        TextView title2 = findViewById(R.id.title2);
        Button start = findViewById(R.id.gotItButton);

        DataWriter dataWriter = new DataWriter(this);
        String user = dataWriter.getUser();
        LanguageTextSetter text = new LanguageTextSetter(dataWriter.getLanguage(user), this);
        Language language = text.getTextsetter();

        instruction.setText(language.getPictureInstruction());
        title.setText(language.getPictureTitle());
        title2.setText(language.instruction());
        start.setText(language.start());

        ConstraintLayout constraintLayout = findViewById(R.id.pictureintroLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(dataWriter.getThemeData(user));
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.PictureGameIntroLayout());
    }

    public void onGotItPressed(View view){
        Intent intent = new Intent(this, PictureGameActivity.class);
        startActivity(intent);
    }
}
