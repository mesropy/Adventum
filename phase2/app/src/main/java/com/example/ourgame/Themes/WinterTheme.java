package com.example.ourgame.Themes;

import com.example.ourgame.R;

class WinterTheme implements Theme {

    @Override
    public int mainActivityLayout() {
        return R.drawable.winter1;
    }

    @Override
    public int SettingsActivityLayout() {
        return R.drawable.winter2;
    }

    @Override
    public int HangmanActivityLayout() {
        return R.drawable.winter3;
    }

    @Override
    public int PictureGameIntroLayout() {
        return R.drawable.winter4;
    }

    @Override
    public int PictureGameLayout() {
        return R.drawable.winter5;
    }

    @Override
    public int EndlessRunnerActivityLayout() {
        return 0;
    }

    @Override
    public int TileGameIntroLayout() {
        return 0;
    }

    @Override
    public int TileGameLayout() {
        return 0;
    }

    @Override
    public int StatisticsAvtivityLayout() {
        return 0;
    }

    @Override
    public int LearderBoardLayout() {
        return 0;
    }
}
