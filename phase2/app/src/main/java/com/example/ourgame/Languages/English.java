package com.example.ourgame.Languages;

import android.content.Context;

import com.example.ourgame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class English implements Language {

    private List<String> gameIntros = new ArrayList<>();

    English(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.game_instruction_english);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String str;
            while ((str = in.readLine()) != null) {
                if (!str.equals("")) {
                    gameIntros.add(str);
                }
            }
        } catch (IOException e) {
            gameIntros.add("");
        }
    }

    @Override
    public String getPlayButton() { return "Play";
    }

    @Override
    public String getMainLeaderBoard() {
        return "Leaderboard";
    }

    @Override
    public String getMainSettings() {
        return "Settings";
    }

    @Override
    public String getReactionTitle() {
        return "Reaction Time";
    }

    @Override
    public String getReactionMessageWait() {
        return "Wait";
    }

    @Override
    public String getReactionMessageInstruction() {
        return gameIntros.get(4);
    }

    @Override
    public String getReactionMessageGo() {
        return "Go!";
    }

    @Override
    public String getReactionMessageTooSoon() {
        return "Too soon";
    }

    @Override
    public String getReactionContinueText() {
        return "Tap to continue";
    }

    @Override
    public String getReactionReadyMessage() {
        return "Ready? ...";
    }

    @Override
    public String getTileTitle() {
        return "Tile Game";
    }

    @Override
    public String getTileLivesRemain() {
        return "Lives remaining: ";
    }

    @Override
    public String getTileResultTextCorrect() {
        return "Correct!";
    }

    @Override
    public String getTileResultTextInCorrect() {
        return "Incorrect!";
    }

    @Override
    public String getTileResultTextLost() {
        return "You lost one live!";
    }

    @Override
    public String getTileResultTextWon() {
        return "You passed this round!";
    }

    @Override
    public String getTileIntroduction1() {
        return gameIntros.get(0);
    }

    @Override
    public String getTileIntroduction2() {
        return gameIntros.get(1);
    }

    @Override
    public String getTileIntroduction3() {
        return gameIntros.get(2);
    }

    @Override
    public String start() {
        return "START!";
    }

    @Override
    public String getPictureInstruction() {
        return gameIntros.get(3);
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

    @Override
    public String getHangmanInstructions() {
        return "You’ve just stumbled upon a strange tree in the middle of nowhere. You’ve been " +
                "told you have the chance to save a mans life, you only need okay a guessing game." +
                " In the following game, you will be provided with a word. All you have to do is " +
                "guess what the word is by picking letters that make up this mystery word. " +
                "However, there’s a catch! You must figure out this word in a limited number" +
                " of tries in order to save the man! Good luck!";
    }

    @Override
    public String getRunnerTitle() {
        return "Running Game";
    }

    @Override
    public String getRunnerInstructions() {
        return "This game is of skill and perseverance. You’re running away from something " +
                "horrible, and the consequences of getting caught are horrible. All you need " +
                "to know is you just get away. But, as you try your best to escape, obstacles " +
                "keep coming up. Tap to jump and avoid these obstacles to get away!";
    }

    @Override
    public String mainMenu() {
        return "Main Menu";
    }

    @Override
    public String back(){
        return "Back";
    }

    @Override
    public String statistics(){
        return "Statistics";
    }

    @Override
    public String statPoints(){
        return "Total Points: ";
    }

    @Override
    public String statPlaytime(){
        return "Total Playtime: ";
    }

    @Override
    public String statRank(){
        return "Ranking: ";
    }

    @Override
    public String points() {
        return "points";
    }

    @Override
    public String ranking() {
        return "ranking";
    }

    @Override
    public String playtime() {
        return "playtime";
    }

    public String score(){
        return "Score: ";
    }

    @Override
    public String getLanguageText() {
        return "Language";
    }

    @Override
    public String getThemeText() {
        return "Theme";
    }

    @Override
    public String getChangeCharacter() {
        return "Change Character";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String leaderBoardUser() {
        return "User: ";
    }

    @Override
    public String leaderBoardYourRank() {
        return "Your Rank: ";
    }

    @Override
    public String rankBy() {
        return "Rank by:";
    }

    @Override
    public String typeAnswer() {
        return "type your answer here";
    }

    @Override
    public String getWelcomeMessage(String user){
        return "Welcome " + user + "!";
    }

    @Override
    public String getInstructionsSubtitle() {
        return "Instructions";
    }
}
