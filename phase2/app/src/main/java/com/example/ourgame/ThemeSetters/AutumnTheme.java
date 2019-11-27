package com.example.ourgame.ThemeSetters;

import android.graphics.drawable.Drawable;

import com.example.ourgame.R;

class AutumnTheme implements Theme {

    @Override
    public int mainActivityLayout() {
        return R.drawable.autumn1;
    }

    @Override
    public int SettingsActivityLayout() {
        return R.drawable.autumn2;
    }

    @Override
    public int HangmanActivityLayout() {
        return R.drawable.autumn3;
    }

    @Override
    public int PictureGameIntroLayout() {
        return R.drawable.autumn4;
    }

    @Override
    public int PictureGameLayout() {
        return R.drawable.autumn5;
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
