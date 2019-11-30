package com.example.ourgame.Themes;

enum ThemeType{
    AUTUMN, SUMMER, WINTER
}


public interface Theme {
    ThemeType getTheme();
    int mainActivityLayout();
    int SettingsActivityLayout();
    int HangmanActivityLayout();
    int PictureGameIntroLayout();
    int PictureGameLayout();
    int EndlessRunnerActivityLayout();
    int TileGameIntroLayout();
    int TileGameLayout();
    int StatisticsAvtivityLayout();
    int LearderBoardLayout();

}
