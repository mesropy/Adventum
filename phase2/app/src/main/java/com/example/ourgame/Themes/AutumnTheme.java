package com.example.ourgame.Themes;

import com.example.ourgame.R;

class AutumnTheme implements Theme {

    @Override
    public ThemeType getTheme(){
        return ThemeType.AUTUMN;
    }

    @Override
    public int mainActivityLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int settingsActivityLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int hangmanActivityLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int pictureGameIntroLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int pictureGameLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int endlessRunnerActivityLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int tileGameIntroLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int tileGameLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int statisticsActivityLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int leaderBoardLayout() {
        return R.drawable.autumn_woodland;
    }

    @Override
    public int reactionGameLayout(){
        return R.drawable.autumn_woodland;
    }
}
