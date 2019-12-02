package com.example.ourgame.Games.HangmanGame;

import android.content.Context;
import android.widget.Button;
import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Themes.ThemeBuilder;
import com.example.ourgame.Utilities.ScreenLoader;

class HangmanPresenter {

    private HangmanView gameView;
    private Hangman game;
    private ScreenLoader screenLoader;

    HangmanPresenter(HangmanView gameView, Hangman game){
        this.gameView = gameView;
        this.game = game;

        LanguageTextSetter text = new LanguageTextSetter(game.getLanguage(), (Context)gameView);
        gameView.setLanguage(text.getTextSetter());
        screenLoader = new ScreenLoader((Context) gameView);

        gameView.setUp(game.getWordBlanks(), game.getImageId());
        setTheme();
        gameView.setInitial();
    }

    void onLetterPressed(Button letterButton){
        // when a letter is pressed, only do something if the game isn't over
        if(!game.isGameOver()) {
            String letter = letterButton.getText().toString().trim().toLowerCase();

            if (game.correctGuess(letter)) {
                onCorrectGuess(letter);
            } else {
                onIncorrectGuess();
            }

            gameView.guessLetter(letterButton);
        }
    }

    void onContinueButtonPressed(){
        screenLoader.loadStatisticsAfterGame();
    }

    private void onCorrectGuess(String letter){
        game.updateGuessCorrect(letter);
        gameView.updateBlanks(game.getWordBlanks());
        if (game.isGameWon()){
            onGameWon();
        }
    }

    private void onIncorrectGuess(){
        game.updateGuessIncorrect();
        gameView.updateImage(game.getImageId());
        if (game.isGameLost()){
            onGameLost();
        }
    }

    private void onGameWon(){
        game.saveStatistics();
        gameView.showGameWon();
    }

    private void onGameLost(){
       game.saveStatistics();
       gameView.showGameLost();
    }

    private void setTheme(){
        gameView.setTheme((new DataWriter((Context)gameView)).getThemeData());
    }
}
