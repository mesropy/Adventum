package com.example.ourgame.Languages;

public class LanguageTextSetter {
    private Language textSetter;

    public LanguageTextSetter(String string){
        if(string.equals("english")){
            textSetter = new English();
        }
        else if(string.equals("french")){
            textSetter = new French();
        }
    }

    public Language getTextSetter() {
        return textSetter;
    }
}
