package com.example.ourgame.Languages;

import android.content.Context;

import com.example.ourgame.R;

import java.io.IOException;

public class LanguageTextSetter {
    private Language textSetter;

    public LanguageTextSetter(String string, Context context){
        if(string.equals(context.getString(R.string.language_english))){
            textSetter = new English(context);
        }
        else if(string.equals(context.getString(R.string.language_french))){
            textSetter = new French(context);
        }
    }

    public Language getTextSetter() {
        return textSetter;
    }
}
