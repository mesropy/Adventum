package com.example.ourgame.Games.HangmanGame;

import android.content.Context;

import com.example.ourgame.Games.Game;
import com.example.ourgame.Games.GameName;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Hangman extends Game {

    // ids of images for each number of incorrect guesses
    // TODO: make these images match the theme
    // image at i-th index is the image to display after i incorrect guesses
    private int[] incorrectGuessImages = {R.drawable.hangman0,
            R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3, R.drawable.hangman4,
            R.drawable.hangman5, R.drawable.hangman6, R.drawable.hangman7, R.drawable.hangman8};

    private int incorrectGuesses;
    private int incorrectGuessesAllowed;
    private List<String> correctlyGuessedLetters;

    private List<String> possibleWords;
    private String wordToGuess;
    private String wordBlanks;

    // if both of these are false, the game hasn't ended
    // otherwise, only one should be true and the game has ended
    private boolean gameLost;
    private boolean gameWon;

    private Context context;

    Hangman(Context context) throws IOException {
        super(GameName.HANGMAN, new DataWriter(context));

        this.context = context;
        this.incorrectGuesses = 0;
        this.incorrectGuessesAllowed = incorrectGuessImages.length - 1;
        this.correctlyGuessedLetters = new ArrayList<>();

        possibleWords = getPossibleWords();
        this.wordToGuess = getANewWord();
        updateWordBlanks();

        this.gameLost = false;
        this.gameWon = false;

    }

    // returns possible words to guess in an array list, words from file

    /**
     * Returns a list of Strings which dictate the possible words that can be used
     * in a game of Hangman, to be guessed as answers.
     *
     * @return the possible words allowed to be guessed
     * @throws IOException
     */
    private List<String> getPossibleWords() throws IOException {
        List<String> possibleWords = new ArrayList<>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.hangman_words);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            possibleWords.add(nextLine);
            nextLine = bufferedReader.readLine();
        }
        return possibleWords;
    }


    // return a new word to guess, based on possible words to guess, and
    // words this user has already tried guessing
    // TODO: need some way of storing this ^ and using it here

    /**
     * Return the next word from the list of possible words.
     *
     * @return the next word from the list of possible words
     */
    private String getANewWord() {
        // to do: base this off of user
        int nextWordIndex = (int) ((Math.random()) * possibleWords.size());
        return possibleWords.get(nextWordIndex);
    }

    // update word blanks based on the letters that have been guessed

    /**
     * Updates the blanks  in the word being guessed.
     * If the letter guessed is correct, the blank is replaced.
     */
    private void updateWordBlanks() {
        StringBuilder blanksStringBuilder = new StringBuilder();
        for (int i = 0; i < wordToGuess.length(); i++) {
            String letterInWordToGuess = Character.toString(wordToGuess.charAt(i));

            if (correctlyGuessedLetters.contains(letterInWordToGuess)) {
                blanksStringBuilder.append(letterInWordToGuess);
            } else {
                blanksStringBuilder.append("_");
            }
            blanksStringBuilder.append(" ");
        }

        // get string from string builder and remove extra space from last character
        this.wordBlanks = blanksStringBuilder.toString().trim();
    }

    String getWordBlanks() {
        return wordBlanks;
    }

    int getImageId() {
        return incorrectGuessImages[incorrectGuesses];
    }

    boolean correctGuess(String guess) {
        return wordToGuess.contains(guess);
    }


    // guess - letter guessed
    // update hangman based on the incorrect guess
    void updateGuessIncorrect() {
        incorrectGuesses++;
        updateGameLost();
    }

    // guess - letter guessed
    // update hangman based on the correct guess
    void updateGuessCorrect(String guess) {
        correctlyGuessedLetters.add(guess);
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


    @Override
    public boolean canUpdateRanking() {
        return incorrectGuesses <= 0;
    }
}
