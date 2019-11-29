package com.example.ourgame.Languages;

import android.content.Context;

import java.io.IOException;

public class LanguageTextSetter {
    private Language textsetter;

    public LanguageTextSetter(String string, Context context){
        if(string.equals("english")){
            try {
                textsetter = new English(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(string.equals("french")){
            try {
                textsetter = new French(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Language getTextsetter() {
        return textsetter;
    }
}
