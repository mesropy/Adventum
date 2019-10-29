package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        TextView text = findViewById(R.id.title);

        Collections.shuffle(images);

        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
            //button.setTextSize(0.0F);
            button.setOnClickListener(this);

            if (images.get(i) == R.drawable.flipped_wrong) {
                button.setText("wrong");

            } else {
                button.setText("right");
            }
        }

        findViewById(R.id.button10)
                .setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), TileGame.class);
                                startActivity(intent);
                                finish();
                            }
                        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        if (button.getText() == "wrong") {

            button.setBackgroundResource(R.drawable.flipped_wrong);
            roundLives--;

            if (roundLives == 0) {
                lives--;
                countLives.setText("Lives Remaining: " + Integer.toString(lives) + "/3");
                roundLives = 2;
                restartGame();
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
                restartGame();
                correctPressed = 0;
            }
        }
    }

    private void restartGame() {

        Collections.shuffle(images);

        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
            //button.setTextSize(0.0F);

            if (images.get(i) == R.drawable.flipped_wrong) {
                button.setText("wrong");
                button.setBackgroundResource(R.drawable.flipped_wrong);

            } else {
                button.setText("right");
                button.setBackgroundResource(R.drawable.flipped_right);
            }
        }

        for (int button : buttons) {
            findViewById(button).setBackgroundResource(R.drawable.unflipped);
        }

    }

}
