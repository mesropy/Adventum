package com.example.ourgame.Games.RunningGame;

import android.graphics.Rect;

import java.util.List;

public interface EndlessRunnerView {

    void draw(Player player, List<Sprite> obstacles);

    Rect getScreen();

    void loseGame();

    EndlessRunnerThread getThread();
}