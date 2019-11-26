package com.example.ourgame.Games.PictureGame;

import android.content.Context;

import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.Games.Game;
import com.example.ourgame.MainActivity;
import com.example.ourgame.R;

class PictureGame extends Game {

  // level info
  private String[][] wordsToGuess = {
    {"rabbit", "bunny", "hare"}, {"frog", "toad"}, {"cat", "kitten", "kitty", "feline"}
  };
  private int[] imagesToGuess = {R.drawable.rabbit, R.drawable.frog, R.drawable.cat};
  private int numAttempts; // for current level
  private int numAttemptsAllowed;
  private int[] pointsToEarn = {2, 3, 5};
  private int currentLevel;

  // other info
  private String correctGuessMessage;
  private String incorrectGuessMessage;
  private String noMoreAttemptsMessage;

  //private DataWriter data;
  private int playTime = 0;

  PictureGame(Context context) {
    super();
    numAttempts = 0;
    numAttemptsAllowed = 5;
    currentLevel = 0;
    correctGuessMessage = "Correct!";
    incorrectGuessMessage = "Sorry, try again!";
    noMoreAttemptsMessage = "No more attempts!";
    setData(new DataWriter(context));
    //data = new DataWriter(context);
  }

  void incrementNumAttempts() {
    numAttempts++;
  }

  void resetNumAttempts() {
    numAttempts = 0;
  }

  String getNumAttemptsText() {
    return numAttempts + " / " + numAttemptsAllowed;
  }

  String getCorrectGuessMessage() {
    return correctGuessMessage;
  }

  String getIncorrectGuessMessage() {
    return incorrectGuessMessage;
  }

  String getNoMoreAttemptsMessage() {
    return noMoreAttemptsMessage;
  }

  int getCurrentImageResource() {
    return imagesToGuess[currentLevel];
  }

  void setPlayTime(int playTime) {
    this.playTime = playTime;
  }

  /**
   * returns whether or not a given guess is a correct guess
   *
   * @param guess the guess to check whether or not is correct
   * @return whether or not the guess was correct
   */
  boolean guessCorrect(String guess) {
    // ignore whitespace and case
    String cleanedGuess = guess.trim().toLowerCase();
    for (String word : wordsToGuess[currentLevel]) {
      if (cleanedGuess.equals(word)) {
        return true;
      }
    }
    // guess didn't match any of the possible answers
    return false;
  }

  // earn the points for the current level (based on pointsToEarn array)
  void addPoints() {
    addPointsEarned(pointsToEarn[currentLevel]);
  }

  void nextLevel() {
    currentLevel++;
  }

  @Override
  protected void updateStatistics() {
    getData().addPlayTime(getUser(), playTime);
    getData().addPoints(getUser(), getPointsEarned());
    getData().addLastGame(getUser(), "Picture");

    if (increaseRank()) {
      getData().increaseRanking(getUser());
    }
  }

  /**
   * Return whether or not the rank of the user should increase after this game based on
   * the time it took them to complete all levels.
   *
   * @return whether or not the user's rank should increase
   */
  private boolean increaseRank() {
    int totalPointsToEarn = 0;
    for (int points : pointsToEarn) {
      totalPointsToEarn += points;
    }

    // earned all possible points / won all levels
    // and completed them in 20 seconds or less
    return getPointsEarned() >= totalPointsToEarn && playTime <= 20;
  }

  boolean usedAllAttempts() {
    return numAttempts >= numAttemptsAllowed;
  }

  /** @return whether or not we've reached the last level */
  boolean reachedLastLevel() {
    int numLevels = wordsToGuess.length;
    return currentLevel + 1 >= numLevels;
  }
}
