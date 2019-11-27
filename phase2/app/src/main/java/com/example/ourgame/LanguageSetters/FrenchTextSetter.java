package com.example.ourgame.LanguageSetters;

public class FrenchTextSetter implements TextSetter {

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
        return "Lorsque l'écran devient vert, tapez le plus rapidement possible";
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
        return "Chaque tour, un ensemble de carreaux apparaît en vert avant de virer au bleu " + "\n" +
                "Votre travail consiste à mémoriser les emplacements des tuiles vertes et à taper sur " +
                "les carreaux verts tout en évitant les carreaux bleus originaux.";
    }

    @Override
    public String getTileIntroduction2() {
        return "Si vous choisissez une tuile incorrecte, elle deviendra rouge." + "\n" +
                "Si vous choisissez une bonne tuile, elle deviendra verte.";
    }

    @Override
    public String getTileIntroduction3() {
        return "Pour continuer au tour suivant, cliquez sur toutes les bonnes tuiles tout en évitant " +
                "celles qui sont incorrectes. " + "\n" + "Si vous manquez 2 tuiles sur un tour, vous perdez une vie" +
                "\n" + "Perdre trois vies mettra fin au jeu!" + "\n" +
                "Appuyez sur autant de carreaux verts et continuez aussi longtemps que possible.";
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
        return "Ici, vous recevrez une série de photos parmi lesquelles se cachent diverses " +
                "créatures des bois. Il est de votre devoir de déterminer correctement quelle" +
                " créature se trouve dans les lignes. Vous aurez cinq hypothèses pour déterminer " +
                "correctement l'animal donné. Chaque niveau consécutif a des images de plus en " +
                "plus difficiles à deviner. Bonne chance!";
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
        return null;
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
}
