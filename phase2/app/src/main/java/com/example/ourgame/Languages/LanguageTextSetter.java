package com.example.ourgame.Languages;

public class LanguageTextSetter {
    private Language textsetter;

    public LanguageTextSetter(String string){
        if(string.equals("english")){
            textsetter = new English();
        }
        else if(string.equals("french")){
            textsetter = new French();
        }
    }

    public Language getTextsetter() {
        return textsetter;
    }
}
