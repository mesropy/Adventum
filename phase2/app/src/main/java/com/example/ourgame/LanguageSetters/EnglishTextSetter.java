package com.example.ourgame.LanguageSetters;

public class EnglishTextSetter implements TextSetter {

    public String getMainPlayButton() {
        return "Play";
    }
    public String getMainLeaderBoard() {
        return "Leaderboard";
    }
    public String getMainSettings() {
        return "Settings";
    }
    public String getReactionTitle() {
        return "Reaction Time";
    }
    public String getReactionMessageWait() {
        return "Wait";
    }
    public String getReactionMessageInstruction() {
        return "When the screen turns green, tap as quickly as possible...";
    }
    public String getReactionMessageGo() {
        return "Go!";
    }
    public String getReactionMessageTooSoon() {
        return "Too soon";
    }
    public String getReactionContinueText() {
        return "Tap to continue";
    }

    @Override
    public String getTileTitle() {
        return "Tile Game";
    }

    public String getTileLivesRemain() {
        return "Lives remaining: ";
    }

    public String getTileResultTextCorrect() {
        return "Correct!";
    }

    public String getTileResultTextInCorrect() {
        return "Incorrect!";
    }

    public String getTileResultTextLost() {
        return "You lost one live!";
    }

    public String getTileResultTextWon() {
        return "You passed this round!";
    }

    @Override
    public String getTileIntroduction1() {
        return "Every round, a set of tiles will appear green before turning blue " + "\n" +
                "Your job is to memorize the locations of the green tiles and tap " +
                "the green tiles while avoiding the original blue tiles.";
    }

    @Override
    public String getTileIntroduction2() {
        return "If you pick an incorrect tile, it will turn red." + "\n" +
                "If you pick a correct tile, it will turn green.";
    }

    @Override
    public String getTileIntroduction3() {
        return "To continue to the next round, click all the correct tiles while avoiding the " +
                "incorrect ones." + "\n" + "If you miss 2 tiles on one round you lose a life" +
                "\n" + "Losing three lives will end the game!" + "\n" + "Tap as many green tiles "
                + "and continue as long as possible.";
    }

    @Override
    public String instruction() {
        return "Instruction";
    }

    @Override
    public String start() {
        return "START!";
    }

    @Override
    public String getPictureInstruction() {
        return "Here you will be given a series of pictures among which a variety of woodland " +
                "creatures are hiding. It is your job to deem correctly what creature lies within " +
                "the lines. You will have five guesses to correctly determine the given animal. " +
                "Each consecutive level has increasingly difficult images for you to guess. " +
                "Good luck!";
    }

    @Override
    public String getPictureTitle() {
        return "Picture Game";
    }

    @Override
    public String getPictureNoMoreAttempts() {
        return "No more attempts!";
    }

    @Override
    public String getPictureNumAttempts() {
        return "Number of Attempts:";
    }

    @Override
    public String getContinue() {
        return "Continue";
    }

    @Override
    public String getEnter() {
        return "Enter";
    }

    @Override
    public String getHangmanTitle() {
        return "Hangman";
    }

    public String mainMenu() {
        return "Main Menu";
    }

    public String back(){
        return "Back";
    }
    public String statistics(){
        return "Statistics";
    }
    public String statPoints(){
        return "Total Points: ";
    }
    public String statPlaytime(){
        return "Total Playtime: ";
    }
    public String statRank(){
        return "Ranking: ";
    }

}
