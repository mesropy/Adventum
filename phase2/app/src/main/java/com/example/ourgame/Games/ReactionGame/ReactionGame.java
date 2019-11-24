package com.example.ourgame.Games.ReactionGame;

import android.content.Context;

import com.example.ourgame.Games.Game;
import com.example.ourgame.R;
import com.example.ourgame.Statistics.DataWriter;

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

    private Context context;

    ReactionGame(Context context){
        this.context = context;
        setData(new DataWriter(context));
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

    /**
     * Send and record the statistics based on the player's performance in the five rounds of
     * the Reaction Time Game
     */
    @Override
    protected void updateStatistics() {
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
        getData().addPoints(getUser(), points);
        getData().addPlayTime(getUser(), (int)(total/1000));
        getData().addLastGame(getUser(), context.getString(R.string.reaction_game));

        if (average <= 200) {
            getData().increaseRanking(getUser());
        }
    }

}
