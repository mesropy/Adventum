package com.example.ourgame.Themes;

public class ThemeBuilder {

    private Theme theme;

    public ThemeBuilder(String string){
        if (string.equals("autumn")){
            theme = new AutumnTheme();
        }else if (string.equals("winter")) {
            theme = new WinterTheme();
        }else{
            theme = new SummerTheme();
        }
    }

    public Theme getTheme() {
        return theme;
    }
}
