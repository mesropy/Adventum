package com.example.ourgame.Games.TileGame;

class PointCalculator {
    private int tilePoints; // different from the points earned by the player


    void addTilePoint() {
        tilePoints++;
    }

    int getTilePoints() {
        return tilePoints;
    }
}
