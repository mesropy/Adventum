package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

enum State {
    INSTRUCTION,
    WAITING,
    GO,
    TIME,
    EARLY,
    DONE
}

/**
 * An activity class for the Reaction Time Game
 */
public class ReactionTime extends AppCompatActivity  {

    private ConstraintLayout currentLayout;
    private State currentState;
    private TextView message;
    private TextView title;
    private TextView countText;
    private TextView averageText;
    private long startTime = 0;
    private int count = 0;
    private long total = 0;
    private long average = 0;

    private DataWriter data;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            go();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time);

        currentLayout = findViewById(R.id.main_layout);
        message = findViewById(R.id.instructionText);
        title = findViewById(R.id.titleText);
        countText = findViewById(R.id.numLevelsText);
        countText.setVisibility(View.INVISIBLE);
        averageText = findViewById(R.id.averageText);
        averageText.setVisibility(View.INVISIBLE);
        data = new DataWriter(this);

        instruction();
    }

    /**
     * A method to determine what state the screen will go to when tapped, depending on the
     * previous state
     * @param view the button object that was tapped
     */
    public void screenTapped(View view){
        if (currentState == State.INSTRUCTION){
            start();
            waiting();
        }
        else if (currentState == State.GO){
            time(System.currentTimeMillis());
        }
        else if (currentState == State.TIME || currentState == State.EARLY){
            waiting();
        }
        else if (currentState == State.DONE){
            sendStats();
            nextGame();
        }
        else {
            timerHandler.removeCallbacks(timerRunnable);
            tooSoon();
        }
    }

    /**
     * After five rounds of the Reaction Time Game, this method sends the player to the next game,
     * which is the Tile Game
     */
    private void nextGame() {
        // go to stats page then to the tile game
        final Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("next activity", getString(R.string.tile_game));
        startActivity(intent);

        finish();
    }

    /**
     * Send and record the statistics based on the player's performance in the five rounds of
     * the Reaction Time Game
     */
    private void sendStats() {
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
        data.addPoints(MainActivity.user, points);
        data.addPlayTime(MainActivity.user, (int)(total/1000));
        data.addLastGame(MainActivity.user, getString(R.string.reaction_game));

        if (average <= 200) {
            data.increaseRanking(MainActivity.user);
        }
    }

    /**
     * A method to display the text and titles on screen after the instructions
     */
    private void start(){
        message.setTextSize(36);
        title.setVisibility(View.INVISIBLE);
        countText.setVisibility(View.VISIBLE);
        averageText.setVisibility(View.VISIBLE);
    }

    /**
     * Display the waiting screen when the player must wait before tapping the screen
     */
    private void waiting() {
        currentState = State.WAITING;
        message.setText(R.string.wait);
        currentLayout.setBackgroundResource(R.color.error_red);
        timerHandler.postDelayed(timerRunnable, new Random().nextInt(3000) + 1000);
    }

    /**
     * Display the instructions for the game on the screen
     */
    private void instruction() {
        currentState = State.INSTRUCTION;
        message.setText(R.string.reaction_intro);
    }

    /**
     * Display the screen telling the user how quickly they tapped the screen when it reached the
     * GO state and shows them their reaction time in milliseconds
     * @param stopTime the time in milliseconds that the player tapped the screen in the GO state
     */
    private void time(long stopTime){
        String string;
        Long time = stopTime - startTime;
        count += 1;
        total += time;
        currentState = State.TIME;
        string = time + "ms";
        message.setText(string);
        String string2 = count + "/5";
        countText.setText(string2);
        average = total / count;
        String string3 = average + "ms";
        averageText.setText(string3);
        currentLayout.setBackgroundResource(R.color.menu_blue);

        if (count == 5){
            currentState = State.DONE;
        }
    }

    /**
     * Display the GO state of the screen where the player must tap the screen as soon as this state
     * is reached
     */
    private void go(){
        currentState = State.GO;
        message.setText(R.string.go);
        startTime = System.currentTimeMillis();
        currentLayout.setBackgroundResource(R.color.go_green);
    }

    /**
     * Display a screen informing the user that they've tapped the screen too soon
     */
    private void tooSoon(){
        currentState = State.EARLY;
        message.setText(R.string.too_soon);
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }
}
