package com.example.ourgame.Themes;

import com.example.ourgame.R;

class SummerTheme implements Theme {

    @Override
    public int mainActivityLayout() {
        return R.drawable.summer1;
    }

    @Override
    public int SettingsActivityLayout() {
        return R.drawable.summer2;
    }

    @Override
    public int HangmanActivityLayout() {
        return R.drawable.summer3;
    }

    @Override
    public int PictureGameIntroLayout() {
        return R.drawable.summer4;
    }

    @Override
    public int PictureGameLayout() {
        return R.drawable.summer5;
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
