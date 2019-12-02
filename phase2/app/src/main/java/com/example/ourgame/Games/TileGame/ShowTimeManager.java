package com.example.ourgame.Games.TileGame;

class ShowTimeManager {

    private long startTime = 0;

    // time in milliseconds that will show pattern before flipping tiles
    private int patternShowTime;
    // time in milliseconds that will show pattern before moving on to then next level / game
    private int patternEndShowTime;


    ShowTimeManager(){
        patternShowTime = 3000;
        patternEndShowTime = 2000;
    }

    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    long getStartTime() {
        return startTime;
    }

    int getPatternShowTime() {
        int time = patternShowTime;
        if (patternShowTime > 500) {
            patternShowTime = patternShowTime - 200;
        }
        return time;
    }

    void resetShowTime() {
        patternShowTime = 3000;
    }

    int getPatternEndShowTime() {
        return patternEndShowTime;
    }
}
