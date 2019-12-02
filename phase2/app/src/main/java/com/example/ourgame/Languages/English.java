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

    English(Context context) throws IOException{
        InputStream inputStream = context.getResources().openRawResource(R.raw.game_instruction_english);

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String str;
            while((str = in.readLine()) != null){
                if (!str.equals("")){
                    gameIntros.add(str);
                }
            }
        }
        catch (IOException e) {
            gameIntros.add("");
        } finally {
            in.close();
        }
    }

    public String getPlayButton() { return "Play";
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
        return gameIntros.get(4);
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
    public String getReactionReadyMessage() {
        return "Ready? ...";
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
    public String instruction() {
        return "Instruction";
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

    public String save(){
        return "Save";
    }
}
