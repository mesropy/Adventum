package com.example.ourgame.Games;

import com.example.ourgame.Languages.Language;

public interface GameView {

    void showInstructions();

    void showStart();

    void setLang(Language lang);

    void setInitial();
}
