package com.example.ourgame.Languages;

import android.content.Context;

import com.example.ourgame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class French implements Language {

    private List<String> gameIntros = new ArrayList<>();
    French(Context context){
        InputStream inputStream = context.getResources().openRawResource(R.raw.game_instruction_french);

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
    public String getPlayButton() {
        return "Jouer";
    }

    @Override
    public String getMainLeaderBoard() {
        return "Classement";
    }

    @Override
    public String getMainSettings() {
        return "Paramètres";
    }

    @Override
    public String getReactionTitle() {
        return "Temps de réaction";
    }

    @Override
    public String getReactionMessageWait() {
        return "Attendez";
    }

    @Override
    public String getReactionMessageInstruction() {
        return gameIntros.get(4);
    }

    @Override
    public String getReactionMessageGo() {
        return "Aller!";
    }

    @Override
    public String getReactionMessageTooSoon() {
        return "Trop tôt";
    }

    @Override
    public String getReactionContinueText() {
        return "Appuyez sur pour continuer";
    }

    @Override
    public String getReactionReadyMessage() {
        return "Prêt? ...";
    }

    @Override
    public String getTileTitle() {
        return "Jeu de tuile";
    }

    @Override
    public String getTileLivesRemain() {
        return "Vies restantes: ";
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
        return "Tu as perdu un live!";
    }

    @Override
    public String getTileResultTextWon() {
        return "Vous avez passé ce tour!";
    }

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
        return "COMMENCE!";
    }

    @Override
    public String getPictureInstruction() {
        return gameIntros.get(3);
    }

    @Override
    public String getPictureTitle() {
        return "Jeu d'images";
    }

    @Override
    public String getPictureNoMoreAttempts() {
        return "Plus de tentatives";
    }

    @Override
    public String getPictureNumAttempts() {
        return "Nombre de Tentatives";
    }

    @Override
    public String getContinue() {
        return "Continuer";
    }

    @Override
    public String getEnter() {
        return "Entrer";
    }

    @Override
    public String getHangmanTitle() {
        return "Jeu du Pendu";
    }

    @Override
    public String mainMenu() {
        return "Menu principal";
    }

    @Override
    public String back(){
        return "Retour";
    }

    @Override
    public String statistics(){
        return "Statistiques";
    }

    @Override
    public String statPoints(){
        return "Points totaux: ";
    }

    @Override
    public String statPlaytime(){
        return "Temps de lecture total: ";
    }

    @Override
    public String statRank(){
        return "Classement: ";
    }

    @Override
    public String points() {
        return "points";
    }

    @Override
    public String ranking() {
        return "classement";
    }

    @Override
    public String playtime() {
        return "temp de lecture";
    }

    @Override
    public String score(){
        return "Le point: ";
    }

    @Override
    public String getLanguageText() {
        return "La langue";
    }

    @Override
    public String getThemeText() {
        return "Thème";
    }

    @Override
    public String getChangeCharacter() {
        return "Changer le personnage";
    }

    @Override
    public String leaderBoardUser() {
        return "Utilisateur: ";
    }

    @Override
    public String leaderBoardYourRank() {
        return "Ton rang: ";
    }

    @Override
    public String rankBy() {
        return "Classement par:";
    }

    @Override
    public String typeAnswer() {
        return "tapez votre réponse ici";
    }

    @Override
    public String getWelcomeMessage(String user){
        return "Bienvenu " + user + "!";
    }

    @Override
    public String getInstructionsSubtitle() {
        return "Instructions";
    }

    @Override
    public String save(){
        return "Conserver";
    }
}
