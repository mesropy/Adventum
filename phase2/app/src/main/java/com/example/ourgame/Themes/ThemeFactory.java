package com.example.ourgame.Themes;

public class ThemeFactory {

    public Theme buildTheme(ThemeType themeType){
        return new AutumnTheme();
    }
}
