package com.example.ourgame.RunningGame;

import android.graphics.Rect;

import com.example.ourgame.RunningGame.EndlessRunner;

import java.util.ArrayList;
import java.util.List;

public class EndlessRunnerGame {

    private EndlessRunnerView view;
    private List<Player> players = new ArrayList<>();

    public EndlessRunnerGame(EndlessRunnerView view){
        this.view = view;
        start();
    }

    private void start() {
       Player player = new Player(null, 0, 0, 50, 50, view.getScreen());
       players.add(player);
    }

    public void update(){
        for (Player player : players){
            player.update();
        }
    }

    public void draw(){
        for (Player player : players){
            view.drawPlayer(player);
        }
    }

}
