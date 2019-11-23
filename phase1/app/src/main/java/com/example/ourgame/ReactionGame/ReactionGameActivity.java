package com.example.ourgame.ReactionGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.MainActivity;
import com.example.ourgame.R;
import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.Statistics.StatisticsActivity;

import java.util.Random;

/**
 * An activity class for the Reaction Time Game
 */
public class ReactionGameActivity extends AppCompatActivity  {

    private ReactionGame game;

    private ConstraintLayout currentLayout;
    private TextView message;
    private TextView title;
    private TextView countText;
    private TextView averageText;

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

        game = new ReactionGame(this);

        instruction();
    }

    /**
     * A method to determine what state the screen will go to when tapped, depending on the
     * previous state
     * @param view the button object that was tapped
     */
    public void screenTapped(View view){
        if (game.getCurrentState() == State.INSTRUCTION){
            start();
            waiting();
        }
        else if (game.getCurrentState() == State.GO){
            time(System.currentTimeMillis());
        }
        else if (game.getCurrentState() == State.TIME || game.getCurrentState() == State.EARLY){
            waiting();
        }
        else if (game.getCurrentState() == State.DONE){
            game.updateStatistics();
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
        game.setState(State.WAITING);
        message.setText(R.string.wait);
        currentLayout.setBackgroundResource(R.color.error_red);
        timerHandler.postDelayed(timerRunnable, new Random().nextInt(3000) + 1000);
    }

    /**
     * Display the instructions for the game on the screen
     */
    private void instruction() {
        game.setState(State.INSTRUCTION);
        message.setText(R.string.reaction_intro);
    }

    /**
     * Display the screen telling the user how quickly they tapped the screen when it reached the
     * GO state and shows them their reaction time in milliseconds
     * @param stopTime the time in milliseconds that the player tapped the screen in the GO state
     */
    private void time(long stopTime){
        String timeString;
        long time = game.updateTime(stopTime);
        game.setState(State.TIME);
        timeString = time + "ms";
        message.setText(timeString);
        String countString = game.getCount() + "/5";
        countText.setText(countString);
        String averageString = game.getAverage() + "ms";
        averageText.setText(averageString);
        currentLayout.setBackgroundResource(R.color.menu_blue);

        if (game.getCount() == 5){
            game.setState(State.DONE);
        }
    }

    /**
     * Display the GO state of the screen where the player must tap the screen as soon as this state
     * is reached
     */
    private void go(){
        game.setState(State.GO);
        message.setText(R.string.go);
        game.setStartTime(System.currentTimeMillis());
        currentLayout.setBackgroundResource(R.color.go_green);
    }

    /**
     * Display a screen informing the user that they've tapped the screen too soon
     */
    private void tooSoon(){
        game.setState(State.EARLY);
        message.setText(R.string.too_soon);
        currentLayout.setBackgroundResource(R.color.menu_blue);
    }
}
