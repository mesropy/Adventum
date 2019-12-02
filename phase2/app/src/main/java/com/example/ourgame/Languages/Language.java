package com.example.ourgame.Languages;

public interface Language {
   // main menu
    String getPlayButton();
    String getMainLeaderBoard();
    String getMainSettings();
    String getWelcomeMessage(String user);

    // instructions
    String getInstructionsSubtitle();

    // reaction game
    String getReactionTitle();
    String getReactionMessageWait();
    String getReactionMessageGo();
    String getReactionMessageTooSoon();
    String getReactionMessageInstruction();
    String getReactionContinueText();
    String getReactionReadyMessage();

    // tile game
    String getTileTitle();
    String getTileLivesRemain();
    String getTileResultTextCorrect();
    String getTileResultTextInCorrect();
    String getTileResultTextLost();
    String getTileResultTextWon();
    String getTileIntroduction1();
    String getTileIntroduction2();
    String getTileIntroduction3();

    // picture game
    String getPictureInstruction();
    String getPictureTitle();
    String getPictureNoMoreAttempts();
    String getPictureNumAttempts();
    String typeAnswer();

    // hangman game
    String getHangmanTitle();

    // statistics
    String statistics();
    String statPoints();
    String statPlaytime();
    String statRank();

    String score();

    // settings
    String getLanguageText();
    String getThemeText();
    String getChangeCharacter();
    String save();

    // leaderBoard
    String leaderBoardUser();
    String leaderBoardYourRank();
    String rankBy();
    String points();
    String ranking();
    String playtime();

    // other / general
    String instruction();
    String start();
    String getContinue();
    String getEnter();
    String mainMenu();
    String back();
}
