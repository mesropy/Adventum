package com.example.ourgame.Games.ReactionGame;

import android.content.Context;
import android.os.Handler;
import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Utilities.WriteData;

import java.util.Random;

class ReactionGamePresenter {

    private ReactionGameView gameView;
    private ReactionGame game;
    private ScreenLoader screenLoader;

    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            go();
        }
    };

    ReactionGamePresenter(ReactionGameView gameView, ReactionGame game, WriteData dataWriter){
        this.gameView = gameView;
        this.game = game;

        LanguageTextSetter text = new LanguageTextSetter(game.getLanguage(), (Context) gameView);
        gameView.setLang(text.getTextSetter());
        gameView.setInitial();
        setInstructions();

        screenLoader = new ScreenLoader((Context) gameView);
    }

    void onScreenTapped(){
        if (game.getCurrentState() == State.INSTRUCTION){
            gameView.showStart();
            waiting();
        }
        else if (game.getCurrentState() == State.GO){
            time(System.currentTimeMillis());
            gameView.updateScreen(game.getAverage(), game.getCount());
        }
        else if (game.getCurrentState() == State.TIME || game.getCurrentState() == State.EARLY){
            waiting();
        }
        else if (game.getCurrentState() == State.DONE){
            nextGame();
        }
        else {
            timerHandler.removeCallbacks(timerRunnable);
            tooSoon();
        }
    }

    /**
     * Display the waiting screen when the player must wait before tapping the screen
     */
    private void waiting(){
        game.setState(State.WAITING);
        game.setStartTime(System.currentTimeMillis());
        gameView.showWaiting();
        timerHandler.postDelayed(timerRunnable, new Random().nextInt(3000) + 1000);
    }

    /**
     * Display the screen telling the user how quickly they tapped the screen when it reached the
     * GO state and shows them their reaction time in milliseconds
     * @param stopTime the time in milliseconds that the player tapped the screen in the GO state
     */
    private void time(long stopTime){
        game.setState(State.TIME);
        long time = game.updateTime(stopTime);
        gameView.showTime(time);

        if (game.getCount() == 5){
            game.setState(State.DONE);
        }
    }

    /**
     * After five rounds of the Reaction Time Game, this method sends the player to the next game,
     * which is the Tile Game
     */
    private void nextGame() {
        // go to stats page then to the tile game
        game.addPointsEarned();
        game.setPlayTime();
        game.saveStatistics();
        screenLoader.loadStatisticsAfterGame();
    }

    /**
     * Display a screen informing the user that they've tapped the screen too soon
     */
    private void tooSoon(){
        game.setState(State.EARLY);
        gameView.showTooSoon();
    }

    /**
     * Display the GO state of the screen where the player must tap the screen as soon as this state
     * is reached
     */
    private void go(){
        game.setState(State.GO);
        game.setStartTime(System.currentTimeMillis());
        gameView.showGo();
    }

    /**
     * Display the instructions for the game on the screen
     */
    private void setInstructions() {
        game.setState(State.INSTRUCTION);
        gameView.showInstructions();
    }
}
