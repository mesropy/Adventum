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
    EARLY
}

public class ReactionTime extends AppCompatActivity {

    private ConstraintLayout currentLayout;
    private State currentState;
    private TextView message;
    private TextView title;
    private TextView numLevelsText;
    private TextView averageText;
    private long startTime = 0;
    private long totalTime = 0;
    private int numLevels = 5;
    private int currentLevel = 0;
    private Stats stats;

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
        numLevelsText = findViewById(R.id.numLevelsText);
        numLevelsText.setVisibility(View.INVISIBLE);
        averageText = findViewById(R.id.averageText);
        averageText.setVisibility(View.INVISIBLE);
        //DataWriter dataWriter = new DataWriter();
        //stats = new Stats(dataWriter);

        instruction();
    }

    public void screenTapped(View view){
        if (currentState == State.INSTRUCTION){
            start();
            waiting();
        }
        else if (currentState == State.GO){
            time(System.currentTimeMillis());
            if (currentLevel >= numLevels) {
                endOfGame();
            }
        }
        else if (currentState == State.TIME || currentState == State.EARLY){
            waiting();
        }
        else {
            timerHandler.removeCallbacks(timerRunnable);
            tooSoon();
        }
    }

    private void start(){
        message.setTextSize(36);
        title.setVisibility(View.INVISIBLE);
        numLevelsText.setVisibility(View.VISIBLE);
        averageText.setVisibility(View.VISIBLE);
    }

    private void waiting() {
        currentState = State.WAITING;
        message.setText("Wait...");
        currentLayout.setBackgroundResource(R.color.error_red);
        timerHandler.postDelayed(timerRunnable, new Random().nextInt(3000) + 1000);
    }

    private void instruction() {
        currentState = State.INSTRUCTION;
        message.setText("When the screen turns green, tap as quickly as you can.");
    }

    private void time(long stopTime){
        Long time = stopTime - startTime;
        currentLevel += 1;
        totalTime += time;
        currentState = State.TIME;
        message.setText(Long.toString(time) + "ms");
        numLevelsText.setText(Integer.toString(currentLevel) + "/" + numLevels);
        averageText.setText(Long.toString(totalTime / currentLevel) + "ms");
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }

    private void go(){
        currentState = State.GO;
        message.setText("Go!");
        startTime = System.currentTimeMillis();
        currentLayout.setBackgroundResource(R.color.go_green);
    }

    private void tooSoon(){
        currentState = State.EARLY;
        message.setText("Too Soon!");
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }

    private void endOfGame(){

        // TODO: save stats and go to next game

        // go to next game
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
    }
}
