package com.example.ourgame.Games.HangmanGame;

import com.example.ourgame.R;

import java.util.ArrayList;
import java.util.List;

class Hangman {

    // ids of images for each number of incorrect guesses
    // TODO: make these images match the theme
    // image at i-th index is the image to display after i incorrect guesses
    private int[] incorrectGuessImages = {R.drawable.hangman0,
            R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3, R.drawable.hangman4,
            R.drawable.hangman5, R.drawable.hangman6, R.drawable.hangman7, R.drawable.hangman8};

    private int incorrectGuesses;
    private int incorrectGuessesAllowed;
    private List<String> guessedLetters;

    private List<String> possibleWords;
    private String wordToGuess;
    private String wordBlanks;

    // if both of these are false, the game hasn't ended
    // otherwise, only one should be true and the game has ended
    private boolean gameLost;
    private boolean gameWon;


    Hangman(List<String> possibleWords) {

        this.incorrectGuesses = 0;
        this.incorrectGuessesAllowed = incorrectGuessImages.length - 1;
        this.guessedLetters = new ArrayList<>();

        this.possibleWords = possibleWords;
        this.wordToGuess = getANewWord();
        updateWordBlanks();

        this.gameLost = false;
        this.gameWon = false;

    }


    // return a new word to guess, based on possible words to guess, and
    // words this user has already tried guessing
    // TODO: need some way of storing this ^ and using it here
    private String getANewWord() {
        // to do: base this off of user
        int nextWordIndex = (int) ((Math.random()) * possibleWords.size());
        return possibleWords.get(nextWordIndex);
    }

    // update word blanks based on the letters that have been guessed
    private void updateWordBlanks() {
        StringBuilder blanksStringBuilder = new StringBuilder();
        for (int i = 0; i < wordToGuess.length(); i++) {
            String letterInWordToGuess = Character.toString(wordToGuess.charAt(i));

            if (guessedLetters.contains(letterInWordToGuess)) {
                blanksStringBuilder.append(letterInWordToGuess);
            } else {
                blanksStringBuilder.append("_");
            }
            blanksStringBuilder.append(" ");
        }

        // get string from string builder and remove extra space from last character
        this.wordBlanks = blanksStringBuilder.toString().trim();
    }

    int getImageId() {
        return incorrectGuessImages[incorrectGuesses];
    }

    String getWordBlanks() {
        return wordBlanks;
    }

    boolean correctGuess(String guess) {
        return wordToGuess.contains(guess);
    }


    // guess - letter guessed
    // update hangman based on the incorrect guess
    void updateGuessIncorrect(String guess) {
        incorrectGuesses++;

        updateGameLost();
    }

    // guess - letter guessed
    // update hangman based on the correct guess
    void updateGuessCorrect(String guess) {
        guessedLetters.add(guess);
        updateWordBlanks();

        updateGameWon();
    }

    // updates whether or not game of hangman is lost
    private void updateGameLost() {
        gameLost = incorrectGuesses >= incorrectGuessesAllowed;
    }

    // updates whether or not game of hangman is won
    private void updateGameWon() {
        // game is won if there are no more blanks
        gameWon = !wordBlanks.contains("_");
    }

    boolean isGameLost() {
        return gameLost;
    }

    boolean isGameWon() {
        return gameWon;
    }

    boolean isGameOver() {
        return gameLost || gameWon;
    }

}
