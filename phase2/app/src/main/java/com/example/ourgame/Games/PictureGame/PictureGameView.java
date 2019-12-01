package com.example.ourgame.Games.PictureGame;

import com.example.ourgame.Games.GameView;

interface PictureGameView extends GameView {

    void displayLevel(String numAttempts, int levelImage);

    void displayNumAttempts(String numAttempts);

    void displayUsedAllAttempts();

    void displayCorrectGuess();

    void displayIncorrectGuess();

}
