package com.example.ourgame.Games.ReactionGame;

import android.content.Context;

import com.example.ourgame.Games.Game;
import com.example.ourgame.Games.GameName;
import com.example.ourgame.Utilities.DataWriter;

enum State {
    INSTRUCTION,
    WAITING,
    GO,
    TIME,
    EARLY,
    DONE
}

public class ReactionGame extends Game {

    private int count = 0;
    private long total = 0;
    private long average = 0;
    private long startTime = 0;
    private State currentState;

    ReactionGame(Context context){
        super(GameName.REACTION, new DataWriter(context));
    }

    int getCount() {
        return count;
    }

    long getAverage(){
        average = total / count;
        return average;
    }

    State getCurrentState() {
        return currentState;
    }

    void setState(State state){
        currentState = state;
    }

    long updateTime(long stopTime){
        Long time = stopTime - startTime;
        count += 1;
        total += time;
        return time;
    }

    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean canUpdateRanking() {
        return average <= 200;
    }

    void addPointsEarned() {
        int points;
        if (average <= 250){
            points = 10;
        }
        else if (average <= 300){
            points = 7;
        }
        else if (average <= 350){
            points = 4;
        }
        else if (average <= 400){
            points = 3;
        }
        else{
            points = 1;
        }
        addPointsEarned(points);
    }

    void setPlayTime() {
        setPlayTime((int) (total / 1000));
    }

}
