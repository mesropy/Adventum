package com.example.ourgame.Games.PictureGame;

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
        TextSetter textSetter = text.getTextsetter();

        instruction.setText(textSetter.getPictureInstruction());
        title.setText(textSetter.getPictureTitle());
        title2.setText(textSetter.instruction());
        start.setText(textSetter.start());

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
