package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The main Activity class for a Memory Tile Game
 */
public class TileGame extends AppCompatActivity implements View.OnClickListener {

    private int lives = 3;
    private int roundLives = 2;
    private int points = 0;
    private int correctPressed = 0;
    private ArrayList<Integer> buttonClicked = new ArrayList<>();

    private TextView countLives;

    private ArrayList<Integer> images =
            new ArrayList<>(
                    Arrays.asList(
                            R.drawable.flipped_wrong,
                            R.drawable.flipped_wrong,
                            R.drawable.flipped_wrong,
                            R.drawable.flipped_wrong,
                            R.drawable.flipped_wrong,
                            R.drawable.flipped_right,
                            R.drawable.flipped_right,
                            R.drawable.flipped_right,
                            R.drawable.flipped_right));


    private int[] buttons = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game);
        countLives = findViewById(R.id.lives);

        Collections.shuffle(images);

        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
            button.setTextSize(0.0F);
            button.setOnClickListener(this);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Collections.shuffle(images);

        display();
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;


        if (!buttonClicked.contains(button.getId())) {
            buttonClicked.add(button.getId());
            if (button.getText() == "wrong") {

                button.setBackgroundResource(R.drawable.flipped_wrong);

                roundLives--;

                if (roundLives == 0) {
                    Toast.makeText(getBaseContext(), "You lost one live!" , Toast.LENGTH_SHORT ).show();
                    lives--;
                    correctPressed = 0;
                    countLives.setText("Lives Remaining: " + Integer.toString(lives) + "/3");
                    roundLives = 2;
                    showResult();
                } else{
                    Toast.makeText(getBaseContext(), "INCORRECT!" , Toast.LENGTH_SHORT ).show();
                }

                if (lives == 0) {
                    //end activity
                }

            } else {
                button.setBackgroundResource(R.drawable.flipped_right);

                points++;
                correctPressed++;

                if (correctPressed == 4) {
                    roundLives = 2;
                    correctPressed = 0;
                    showResult();
                    Toast.makeText(getBaseContext(), "You passed this pattern!" , Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText(getBaseContext(), "CORRECT!" , Toast.LENGTH_SHORT ).show();
                }
            }
        }
    }

    private void restartGame() {

        Collections.shuffle(images);
        buttonClicked.clear();

        display();
    }

    private void showResult() {
        for (int i = 0; i < buttons.length; i++) {
            final Button button = findViewById(buttons[i]);
            button.setTextSize(0.0F);
            button.setClickable(false);


            if (images.get(i) == R.drawable.flipped_wrong) {
                button.setBackgroundResource(R.drawable.flipped_wrong);

            } else if (images.get(i) == R.drawable.flipped_right){
                button.setBackgroundResource(R.drawable.flipped_right);
            } else{
                button.setBackgroundResource(R.drawable.unflipped);
            }

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    restartGame();
                }
            }, 2000);
        }
    }

    private void display() {
        for (int i = 0; i < buttons.length; i++) {
            final Button button = findViewById(buttons[i]);
            button.setTextSize(0.0F);
            button.setClickable(false);

            if (images.get(i) == R.drawable.flipped_right){
                button.setText("right");
                button.setBackgroundResource(R.drawable.flipped_right);
            }
            else{
                button.setText("wrong");
                button.setBackgroundResource(R.drawable.unflipped);
            }

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    button.setBackgroundResource(R.drawable.unflipped);
                    button.setClickable(true);
                }
            }, 3000);
        }
    }

}
