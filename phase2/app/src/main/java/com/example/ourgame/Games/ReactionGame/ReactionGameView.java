package com.example.ourgame.Games.ReactionGame;

import com.example.ourgame.Games.GameView;

interface ReactionGameView extends GameView {

    void showReady();

    void showStart();

    void showWaiting();

    void showTime(long time);

    void showTooSoon();

    void showGo();

    void updateScreen(long avg, int count);
}
