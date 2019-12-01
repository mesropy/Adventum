package com.example.ourgame.Themes;

import com.example.ourgame.R;

class SummerTheme implements Theme {

    @Override
    public ThemeType getTheme(){
        return ThemeType.SUMMER;
    }

    @Override
    public int mainActivityLayout() {
        return R.drawable.summer1;
    }

    @Override
    public int settingsActivityLayout() {
        return R.drawable.summer2;
    }

    @Override
    public int hangmanActivityLayout() {
        return R.drawable.summer3;
    }

    @Override
    public int pictureGameIntroLayout() {
        return R.drawable.summer4;
    }

    @Override
    public int pictureGameLayout() {
        return R.drawable.summer5;
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
