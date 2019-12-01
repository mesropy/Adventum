package com.example.ourgame.Themes;

enum ThemeType{
    AUTUMN, SUMMER, WINTER
}


public interface Theme {
    ThemeType getTheme();
    int mainActivityLayout();
    int settingsActivityLayout();
    int hangmanActivityLayout();
    int pictureGameIntroLayout();
    int pictureGameLayout();
    int endlessRunnerActivityLayout();
    int reactionGameLayout();
    int tileGameIntroLayout();
    int tileGameLayout();
    int statisticsActivityLayout();
    int leaderBoardLayout();

}
