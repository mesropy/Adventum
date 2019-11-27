package com.example.ourgame.Games.RunningGame;

import android.content.Context;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.example.ourgame.Games.Game;
import com.example.ourgame.Statistics.DataWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum State{
    START, RUNNING, GAMEOVER
}

class EndlessRunnerGame extends Game {

    private EndlessRunnerView view;
    private int score;
    private Player player;
    private List<Sprite> obstacles;
    private int groundHeight;
    private State gameState;
    private long totalTime;
    private Random randomGenerator;
    private int obstacleDistMax = 300;
    private int obstacleDistMin = 140;
    private long gameStartTime;

    EndlessRunnerGame(EndlessRunnerView view) {
        super("Endless Runner", new DataWriter((Context) view));
//        super("Endless Runner", new DataWriter(((EndlessRunner)view).getContext()));
        
        this.view = view;
        groundHeight = view.getScreen().height() - view.getScreen().width() / 10;
        gameState = State.RUNNING;
        randomGenerator = new Random();
        start();
    }

    private void start() {
        player = new Player(null, view.getScreen().width()/4, groundHeight - 50, 50, 50, groundHeight);
        obstacles = new ArrayList<>();
        obstacles.add(new Bull(null, view.getScreen().right, groundHeight - 50, groundHeight));
        obstacles.add(new Bull(null, view.getScreen().right+obstacleDistMax, groundHeight - 50, groundHeight));
        score = 0;
        gameStartTime = System.currentTimeMillis();
    }

    void update(){
        if (gameState == State.RUNNING) {
            player.update();
            score = (int)((System.currentTimeMillis() - gameStartTime)/1000);
            for (Sprite obstacle : obstacles) {
                obstacle.update();
                if (Rect.intersects(obstacle.getHitbox(), player.getHitbox())) {
                    loseGame();
                }
            }
        }
    }

    private void loseGame() {
        gameState = State.GAMEOVER;
        updateStatistics();
        totalTime += score;
        view.loseGame(score);
    }

    private void generateObstacles() {
        int distance = randomGenerator.nextInt(obstacleDistMax);;
        if (obstacles.size() > 0){
            int distanceToObstacle = view.getScreen().right - obstacles.get(0).getHitbox().right;
            if (distanceToObstacle < obstacleDistMin) {
                distance += obstacleDistMin - distanceToObstacle;
            }
        }
        obstacles.add(new Bull(null, view.getScreen().right+distance, groundHeight - 50, groundHeight));
    }

    void onTapEvent(){
        if (gameState == State.RUNNING){
            player.jump();
        }
        else if (gameState == State.GAMEOVER){
            view.nextGame();
        }
    }

    void draw(){
        if (gameState == State.RUNNING) {
            view.draw(player, obstacles, score);
            checkObstacles();
        }
    }

    private void checkObstacles(){
        List<Sprite> found = new ArrayList<>();
        for (Sprite obstacle : obstacles){
            if (obstacle.getX() < view.getScreen().left){
                found.add(obstacle);
            }
        }
        obstacles.removeAll(found);
        if (found.size() > 0){
            generateObstacles();
        }
    }


    // call these from the activity when updating statistics

    public void addPoints() {
        addPointsEarned(score);
    }

    public void setPlayTime() {
        setPlayTime((int) (totalTime / 1000));
    }

    public boolean canUpdateRanking() {
        // TODO: implement this
        return false;
    }
}
