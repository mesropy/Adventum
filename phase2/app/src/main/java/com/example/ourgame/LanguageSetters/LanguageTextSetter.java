package com.example.ourgame.LanguageSetters;

import android.content.Context;

import java.io.IOException;

public class LanguageTextSetter {
    private TextSetter textsetter;

    public LanguageTextSetter(String string, Context context){
        if(string.equals("english")){
            try {
                textsetter = new EnglishTextSetter(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(string.equals("french")){
            try {
                textsetter = new FrenchTextSetter(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TextSetter getTextsetter() {
        return textsetter;
    }
}
