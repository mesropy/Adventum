package com.example.ourgame.RunningGame;

import android.graphics.Rect;

public interface EndlessRunnerView {

    void drawPlayer(Player player);

    void drawBackground();

    void drawObstacle();

    Rect getScreen();

    EndlessRunnerThread getThread();
}
