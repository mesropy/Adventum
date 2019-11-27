package com.example.ourgame.LanguageSetters;

public class LanguageTextSetter {
    private TextSetter textsetter;

    public LanguageTextSetter(String string){
        if(string.equals("english")){
            textsetter = new EnglishTextSetter();
        }
        else if(string.equals("french")){
            textsetter = new FrenchTextSetter();
        }
    }

    public TextSetter getTextsetter() {
        return textsetter;
    }
}
