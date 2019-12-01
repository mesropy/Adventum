package com.example.ourgame.Games.ReactionGame;

import com.example.ourgame.Games.GameView;
import com.example.ourgame.Languages.Language;

interface ReactionGameView extends GameView {

    void showWaiting();

    void showTime(long time);

    void showTooSoon();

    void showGo();

    void updateScreen(long avg, int count);
}
