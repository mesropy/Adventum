package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ourgame.Statistics.DataWriter;

public class SettingsActivity extends AppCompatActivity {

    private String user;
    private DataWriter data;

    RadioGroup languageRadioGroup;
    RadioButton englishButton, frenchButton, spanishButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        englishButton = findViewById(R.id.englishButton);
        frenchButton = findViewById(R.id.frenchButton);
        spanishButton = findViewById(R.id.spanishButton);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        data = new DataWriter(this);

        /*
         TODO: select current language and theme, and show current character,
          for theme buttons: make not-selected buttons have grey tint
        */
        if (data.getLanguage(user).equals("french")) {
            frenchButton.setChecked(true);
        } else if (data.getLanguage(user).equals("english")) {
            englishButton.setChecked(true);
        } else { // spanish
            spanishButton.setChecked(true);
        }
    }

    // TODO: implement these

    // go to choose character page, then return to settings
    public void onChooseCharacterPressed(View view) {

    }

    // when a theme selected, keep track of it
    // remove grey tint of selected theme and make all other themes have grey tint
    public void onThemeSelected(View view) {

    }


    // save selected theme and language with data saver, and
    // go to main menu
    public void onSaveButtonPressed(View view) {
        // use this to save selected language
        int checkedLanguageId = languageRadioGroup.getCheckedRadioButtonId();
        if (checkedLanguageId == R.id.englishButton) {
            data.setLanguage(user, "english");
        } else if (checkedLanguageId == R.id.frenchButton) {
            data.setLanguage(user, "french");
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }
}
