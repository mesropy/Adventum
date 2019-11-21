package com.example.ourgame.RunningGame;

import android.util.Log;

import com.example.ourgame.RunningGame.EndlessRunnerGame;

public class EndlessRunnerThread extends Thread {

    private EndlessRunnerGame game;
    private static final long FPS = 30;
    private boolean running;

     EndlessRunnerThread(EndlessRunnerGame game){
        this.game = game;
        running = false;
    }

     void setRunning(boolean run){
        running = run;
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        // Game loop
        while (running) {
            long now = System.currentTimeMillis();
            long elapsed = now - lastTime;

            if (elapsed < FPS) {
                game.update();
                game.draw();
            }
            lastTime = now;
        }
    }
}
