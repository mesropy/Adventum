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
    private TextView countText;
    private TextView averageText;
    private long startTime = 0;
    private int count = 0;
    private long total = 0;
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
        countText = findViewById(R.id.countText);
        countText.setVisibility(View.INVISIBLE);
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

            if (count == 5){
                //need to store stats before moving onto next game

                //move onto the instructions for Tilegame
                Intent intent = new Intent(this, TileGameInstructions.class);
                startActivity(intent);
                finish();
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
        countText.setVisibility(View.VISIBLE);
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
        count += 1;
        total += time;
        currentState = State.TIME;
        message.setText(Long.toString(time) + "ms");
        countText.setText(Integer.toString(count) + "/5");
        averageText.setText(Long.toString(total/count) + "ms");
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
}
