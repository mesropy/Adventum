package com.example.ourgame.Games;

import android.content.Intent;

public enum GameName {
    HANGMAN, PICTURE, REACTION, RUNNING, TILE;

    public static String[] names() {
        String[] names = new String[values().length];

        for (int i = 0; i < values().length; i ++) {
            names[i] = values()[i].toString();
        }
        return names;
    }
}
