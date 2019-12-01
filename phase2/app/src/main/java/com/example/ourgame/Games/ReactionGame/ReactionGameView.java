package com.example.ourgame.Games.ReactionGame;

import com.example.ourgame.Games.GameView;

interface ReactionGameView extends GameView {

    void showInstructions();

    void showStart();

    void showWaiting();

    void showTime(long time);

    void showTooSoon();

    void showGo();

    void updateScreen(long avg, int count);
}
