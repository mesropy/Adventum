package com.example.ourgame.Games.TileGame;

import android.view.View;
import android.widget.Button;

import com.example.ourgame.R;

/**
 * A class for a tile in TileGame.
 */
class Tile {

    private Button button;
    private boolean isCorrect;
    private boolean flipped;
    private int flippedImageId;

    Tile(Button button, boolean isCorrect) {

        this.button = button;
        this.isCorrect = isCorrect;

        if (isCorrect) {
            this.flippedImageId = R.drawable.flipped_right;
        } else {
            this.flippedImageId = R.drawable.flipped_wrong;
        }
        flipped = false;
    }

    /**
     * Set this tile object to an incorrect one, setting its image to red and isCorrect false.
     */
    void setWrongTile() {
        isCorrect = false;
        flippedImageId = R.drawable.flipped_wrong;
    }

    /**
     * Set this tile object to a correct one, setting its image to green and isCorrect true.
     */
    void setRightTile() {
        isCorrect = true;
        flippedImageId = R.drawable.flipped_right;
    }

    /**
     * Flip this tile and display its hidden image.
     */
    void flipTile() {
        flipped = true;
        button.setBackgroundResource(flippedImageId);

    }

    /**
     * Unflip this tile and display the default tile image.
     */
    void unFlipTile() {
        flipped = false;
        button.setBackgroundResource(R.drawable.unflipped);
    }

    /**
     * Return the button that this tile object contains.
     *
     * @return the button of the Tile object
     */
    Button getButton() {
        return button;
    }

    boolean getIsCorrect() {
        return isCorrect;
    }

    void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    /**
     * Set the onClickListener method of this Tile's button object.
     *
     * @param activity the TileGameActivity this button is present in
     */
    void setOnClickListener(TileGameActivity activity) {
        button.setOnClickListener(activity);
    }

    /**
     * Set the ability of this tile's button object to be clicked.
     *
     * @param clickable a boolean representing if the button can be clicked or not
     */
    void setClickable(boolean clickable) {

        button.setClickable(clickable);
    }

    /**
     * Set the visibility of this tile's button object.
     *
     * @param visible a boolean representing if the button is visible or not
     */
    void setVisibility(boolean visible) {

        if (visible) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }

    }
}
