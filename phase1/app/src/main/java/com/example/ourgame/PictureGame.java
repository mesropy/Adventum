package com.example.ourgame;

class PictureGame extends Game {

    // level info
    private String[][] wordsToGuess = {{"rabbit", "bunny", "hare"},
            {"frog", "toad"},
            {"cat", "kitten", "kitty", "feline"}};
    private int[] imagesToGuess = {R.drawable.rabbit, R.drawable.frog, R.drawable.cat};
    private int numAttempts; // for current level
    private int numAttemptsAllowed;
    private int[] pointsToEarn = {2, 3, 5};

    // other info
    private String correctGuessMessage;
    private String incorrectGuessMessage;
    private String instructions;
    // TODO: add attribute to keep track of time? (needed?)


    PictureGame() {
        super();
        numAttempts = 0;
        numAttemptsAllowed = 5;
        correctGuessMessage = "Correct!";
        incorrectGuessMessage = "Sorry, try again!";
        instructions = "Figure out what image is hidden in an abstract design.";
        initializeNumLevels();
        initializeInstructions();
    }

    @Override
    void initializeNumLevels() {
        setNumLevels(imagesToGuess.length);
    }

    @Override
    void initializeInstructions() {
        setInstructions(instructions);
    }

    void incrementNumTries() {
        numAttempts++;
    }

    void resetNumTries() {
        numAttempts = 0;
    }

    String getCorrectGuessMessage() {
        return correctGuessMessage;
    }

    String getIncorrectGuessMessage() {
        return incorrectGuessMessage;
    }

    int getCurrentImageResource() {
        return imagesToGuess[getCurrentLevel()];
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
        for (String word : wordsToGuess[getCurrentLevel()]) {
            if (cleanedGuess.equals(word)) {
                return true;
            }
        }
        // guess didn't match any of the possible answers
        return false;
    }

    @Override
    void updateStatistics() {
        // TODO: add time stat(s)

        // add points only if the level was solved within the given amount of attempts
        if (!levelFailed()) {
            addPointsEarned(pointsToEarn[getCurrentLevel()]);
        }
    }

    boolean levelFailed() {
        return numAttempts >= numAttemptsAllowed;
    }
}

