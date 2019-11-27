package com.example.ourgame.Games.TileGame;

import android.widget.Button;

/**
 * A class for a tile in TileGame.
 */
class Tile {

    private Button button;
    private boolean isCorrect;
    private boolean flipped;
    private int imageId;


    Tile(Button button, boolean isCorrect, int imageId){

        this.button = button;
        this.isCorrect = isCorrect;
        this.imageId = imageId;
        flipped = false;

    }

    void flipTile(){
        flipped = !flipped;
    }






}
