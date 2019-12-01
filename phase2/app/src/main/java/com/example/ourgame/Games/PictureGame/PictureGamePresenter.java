package com.example.ourgame.Games.PictureGame;

import android.content.Context;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;

class PictureGamePresenter {

    private PictureGameView gameView;
    private PictureGame game;

    private ScreenLoader screenLoader;

    private long startTime = 0;

    PictureGamePresenter(PictureGameView gameView, PictureGame game){
        this.gameView = gameView;
        this.game = game;

        screenLoader = new ScreenLoader((Context) gameView);

        LanguageTextSetter text = new LanguageTextSetter(
                (new DataWriter((Context)gameView)).getLanguage(), (Context) gameView);
        gameView.setLanguage(text.getTextSetter());

        gameView.setInitial();
        gameView.displayLevel(game.getNumAttemptsText(), game.getCurrentImageResource());

    }

    void onStart(){
        startTime = System.currentTimeMillis();
    }

    void guessEntered(String guess){
        game.incrementNumAttempts();

        if (game.guessCorrect(guess)) {
            onCorrectGuess();
        } else {
            onIncorrectGuess();
        }
        gameView.displayNumAttempts(game.getNumAttemptsText());
    }

    private void onCorrectGuess(){
        game.addPoints();
        gameView.displayCorrectGuess();
    }

    private void onIncorrectGuess(){
        if (game.usedAllAttempts()){
            gameView.displayUsedAllAttempts();
        }
        gameView.displayIncorrectGuess();
    }

    void nextLevel() {
        if (game.reachedLastLevel()) {
            gameOver();
        } else {
            game.nextLevel();
            game.resetNumAttempts();
            gameView.displayLevel(game.getNumAttemptsText(), game.getCurrentImageResource());
        }
    }

    private void gameOver(){
        // go to game over page then stats page
        int playTime = Math.toIntExact((System.currentTimeMillis() - startTime) / 1000);
        game.setPlayTime(playTime);
        game.saveStatistics();
        screenLoader.loadStatisticsAfterGame();
    }
}
