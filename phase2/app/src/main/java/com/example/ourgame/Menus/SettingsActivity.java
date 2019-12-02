package com.example.ourgame.Menus;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Languages.Language;
import com.example.ourgame.Languages.LanguageFactory;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private DataWriter data;
    private ScreenLoader screenLoader;

    private ImageView characterImage;
    private RadioGroup languageRadioGroup;
    private LinearLayout themesLayout;
    private ImageButton[] themeButtons;

    private int[] characterImageIds = {R.drawable.boy, R.drawable.female, R.drawable.girl,
            R.drawable.male, R.drawable.kid};
    // correspond to themes in theme linear layout
    private int[] themeImageIds = {R.drawable.summer_woodland, R.drawable.autumn_woodland,
            R.drawable.dessert};

    private int themeButtonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        characterImage = findViewById(R.id.characterImage);

        data = new DataWriter(this);
        screenLoader = new ScreenLoader(this);

        // theme
        ConstraintLayout constraintLayout = findViewById(R.id.settingsActivityLayout);
        constraintLayout.setBackgroundResource(data.getThemeData());

        // initialize theme buttons and set on click listeners for each of them
        themesLayout = findViewById(R.id.themeButtons);
        int count = themesLayout.getChildCount();
        themeButtons = new ImageButton[count];
        for (int i = 0; i < count; i++) {
            themeButtons[i] = (ImageButton) themesLayout.getChildAt(i);
            themeButtons[i].setOnClickListener(this);
        }

        setLanguage();
        displayCurrentSettings();
    }

    private void setLanguage(){
        Language language = new LanguageFactory(data.getLanguage(), this).getTextSetter();

        TextView titleText = findViewById(R.id.titleText);
        TextView languageText = findViewById(R.id.languageText);
        TextView themeText = findViewById(R.id.themeText);
        Button changeCharacterButton = findViewById(R.id.changeCharacterButton);
        Button backButton = findViewById(R.id.backButton);

        titleText.setText(language.getMainSettings());
        languageText.setText(language.getLanguageText());
        themeText.setText(language.getThemeText());
        changeCharacterButton.setText(language.getChangeCharacter());
        backButton.setText(language.save());
    }


    private void displayCurrentSettings() {

        //character
        characterImage.setImageResource(data.getCharacterData());

        //language

        // loop through radio group and compare tags with getLanguage
        RadioButton selectedLanguageButton;

        if (data.getLanguage().equals(getString(R.string.language_french))) {
            selectedLanguageButton = findViewById(R.id.french);
        } else { // if (data.getLanguage(user).equals("english")) {
            selectedLanguageButton = findViewById(R.id.english);
        }
        selectedLanguageButton.setChecked(true);

        // theme
        int currentThemeImageId = data.getThemeData();
        themeButtonIndex = 0;
        for (int i = 0; i < themeImageIds.length; i ++){
            if (themeImageIds[i] == currentThemeImageId){
                themeButtonIndex = i;
                break;
            }
        }
        displaySelectedTheme();
    }


    // when change character pressed, change the selected character and display it
    public void onChangeCharacterPressed(View view) {

        int nextCharacterId = characterImageIds[0];
        for(int i = 0; i < characterImageIds.length -1; i ++){
            if(data.getCharacterData() == characterImageIds[i]) {
                nextCharacterId = characterImageIds[i + 1];
            }
        }
        characterImage.setImageResource(nextCharacterId);
        data.setCharacterData(nextCharacterId);
    }

    // only called when a theme button is pressed, display the selected theme
    @Override
    public void onClick(View view) {
        themeButtonIndex = themesLayout.indexOfChild(view);
        data.setThemeData(themeImageIds[themeButtonIndex]);
        displaySelectedTheme();
    }

    // highlight the selected theme button
    private void displaySelectedTheme(){
        // add grey tint to all theme buttons, and remove any filters from
        // the selected one
        for(ImageButton themeButton : themeButtons){
            int themeIndex = themesLayout.indexOfChild(themeButton);
            if (themeButtonIndex == themeIndex) {
                themeButton.clearColorFilter();
            } else {
                addGreyTintFilter(themeButton);
            }
        }
    }

    // add grey tint filter to given image button
    private void addGreyTintFilter(ImageButton button){
        button.setColorFilter(Color.rgb(123, 123, 123),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    // save selected theme, language and character with data saver, and
    // go to main menu
    public void onBackButtonPressed(View view) {
        // save selected language
        RadioButton checkedLanguageButton =
                findViewById(languageRadioGroup.getCheckedRadioButtonId());
        String selectedLanguage = checkedLanguageButton.getTag().toString();
        data.setLanguage(selectedLanguage);

        // load main menu
        screenLoader.loadMainMenu();
    }
}
