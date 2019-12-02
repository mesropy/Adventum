package com.example.ourgame.Games.TileGame;

import android.widget.Button;

import java.util.ArrayList;


class TileManager {
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Removes the previous set of game tiles and adds in new tiles received by an ArrayList
     *
     * @param tiles an ArrayList containing the new set of tiles
     */
    void addTiles(ArrayList<Tile> tiles) {
        this.tiles.clear();
        this.tiles.addAll(tiles);

    }

    /**
     * Return the tile object with the associated button object in this game's ArrayList of tiles.
     * Return null if the tile is not found.
     *
     * @param button the button of the tile object
     * @return the tile that contains the button object that was passed in
     */
    Tile getTileByButton(Button button) {

        for (Tile tile : tiles) {
            if (tile.getButton() == button) {
                return tile;
            }
        }
        return null;
    }

    /**
     * Sets the onClickListener methods for the tiles in this game.
     *
     * @param activity the TileGameActivity that the buttons are present in
     */
    void setOnClickListeners(TileGameActivity activity) {
        for (Tile tile : tiles) {
            tile.setOnClickListener(activity);
        }
    }

    /**
     * Return an ArrayList containing the tiles of this game.
     *
     * @return an ArrayList containing tile objects
     */
    ArrayList<Tile> getTiles() {
        return tiles;
    }

}
