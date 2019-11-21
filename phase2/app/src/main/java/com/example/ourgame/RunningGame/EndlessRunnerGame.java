package com.example.ourgame.RunningGame;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.ourgame.RunningGame.EndlessRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum State{
    START, RUNNING, GAMEOVER
}

class EndlessRunnerGame {

    private EndlessRunnerView view;
    private Player player;
    private List<Sprite> obstacles;
    private int groundHeight;
    private State gameState;
    private Random randomGenerator;
    private int obstacleDistMax = 300;
    private int obstacleDistMin = 140;

     EndlessRunnerGame(EndlessRunnerView view){
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

    }

    void update(){
         player.update();
         for (Sprite obstacle : obstacles){
             obstacle.update();
             if(Rect.intersects(obstacle.getHitbox(), player.getHitbox())){ loseGame();}
         }
    }

    private void loseGame() {
         gameState = State.GAMEOVER;
         view.loseGame();
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
    }

    void draw(){
        view.draw(player, obstacles);
        checkObstacles();
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

}
