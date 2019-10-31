package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.login.Login;

import java.util.Random;

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

    private DataWriter data = new DataWriter(this);
    private String user;

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

        Intent intent = getIntent();
        user = intent.getStringExtra(Login.EXTRA_MESSAGE);

        instruction();
    }

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

    private void nextGame() {
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

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
        data.addPoints(user, points);
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
        average = total / count;
        averageText.setText(Long.toString(average) + "ms");
        currentLayout.setBackgroundResource(R.color.menu_blue);

        if (count == 5){
            currentState = State.DONE;
        }
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
