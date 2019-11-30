package com.example.ourgame.Languages;

import android.content.Context;

import com.example.ourgame.R;

import java.io.IOException;

public class LanguageTextSetter {
    private Language textSetter;

    public LanguageTextSetter(String string, Context context){
        if(string.equals(context.getString(R.string.language_english))){
            try {
                textSetter = new English(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(string.equals(context.getString(R.string.language_french))){
            try {
                textSetter = new French(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Language getTextSetter() {
        return textSetter;
    }
}
