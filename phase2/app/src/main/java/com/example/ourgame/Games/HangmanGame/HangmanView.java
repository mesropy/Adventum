package com.example.ourgame.Games.HangmanGame;

import android.widget.Button;
import com.example.ourgame.Games.GameView;

public interface HangmanView extends GameView {

    void setUp(String wordBlanks, int imageId);

    void setBackground(int themeImageId);

    void guessLetter(Button letterButton);

    void updateBlanks(String wordBlacks);

    void updateImage(int imageId);

    void showGameWon();

    void showGameLost();
}
