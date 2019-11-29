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
    French(Context context) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.game_instruction_french);

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

    public String getMainPlayButton() {
        return "Jouer";
    }
    public String getMainLeaderBoard() {
        return "Classement";
    }
    public String getMainSettings() {
        return "Paramètres";
    }
    public String getReactionTitle() {
        return "Temps de réaction";
    }
    public String getReactionMessageWait() {
        return "Attendez";
    }
    public String getReactionMessageInstruction() {
        return gameIntros.get(4);
    }
    public String getReactionMessageGo() {
        return "Aller!";
    }
    public String getReactionMessageTooSoon() {
        return "Trop tôt";
    }
    public String getReactionContinueText() {
        return "Appuyez sur pour continuer";
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
        return "DÉBUT!";
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

    public String mainMenu() {
        return "Menu principal";
    }

    public String back(){
        return "Retour";
    }
    public String statistics(){
        return "Statistiques";
    }
    public String statPoints(){
        return "Points totaux: ";
    }
    public String statPlaytime(){
        return "Temps de lecture total: ";
    }
    public String statRank(){
        return "Classement: ";
    }

    public String score(){
        return "Le point: ";
    }

    @Override
    public String leaderboardUser() {
        return "Utilisateur: ";
    }

    @Override
    public String leaderboardYourRank() {
        return "Ton rang: ";
    }

    @Override
    public String typeAnswer() {
        return "tapez votre réponse ici";
    }

    @Override
    public String getWelcomeMessage(String user){
        return "Bienvenu " + user + "!";
    }
}
