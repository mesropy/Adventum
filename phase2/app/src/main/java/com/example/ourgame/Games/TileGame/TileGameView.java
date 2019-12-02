package com.example.ourgame.Games.TileGame;

import android.widget.Button;

import com.example.ourgame.Games.GameView;

public interface TileGameView extends GameView {
    void clickedOnWrongTileShow();
    void clickedOnARightTileShow();
    void roundLost(int life);
    void roundWon();
    void startRoundShow();
    Button getTile(int id);
}
