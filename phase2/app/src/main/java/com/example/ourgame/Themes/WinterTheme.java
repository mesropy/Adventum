package com.example.ourgame.Themes;

import com.example.ourgame.R;

class WinterTheme implements Theme {

    @Override
    public ThemeType getTheme(){
        return ThemeType.WINTER;
    }

    @Override
    public int mainActivityLayout() {
        return R.drawable.winter1;
    }

    @Override
    public int settingsActivityLayout() {
        return R.drawable.winter2;
    }

    @Override
    public int hangmanActivityLayout() {
        return R.drawable.winter3;
    }

    @Override
    public int pictureGameIntroLayout() {
        return R.drawable.winter4;
    }

    @Override
    public int pictureGameLayout() {
        return R.drawable.winter5;
    }

    @Override
    public int endlessRunnerActivityLayout() {
        return 0;
    }

    @Override
    public int tileGameIntroLayout() {
        return 0;
    }

    @Override
    public int tileGameLayout() {
        return 0;
    }

    @Override
    public int statisticsActivityLayout() {
        return 0;
    }

    @Override
    public int leaderBoardLayout() {
        return 0;
    }

    @Override
    public int reactionGameLayout(){
        return 0;
    }
}
