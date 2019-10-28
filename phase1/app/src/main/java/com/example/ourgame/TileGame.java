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

public class TileGame extends AppCompatActivity {

  private int lives = 3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tile_game);

    TextView text = findViewById(R.id.title);

    ArrayList<Integer> images =
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

    Button[] buttons =
        new Button[] {
          findViewById(R.id.button1),
          findViewById(R.id.button2),
          findViewById(R.id.button3),
          findViewById(R.id.button4),
          findViewById(R.id.button5),
          findViewById(R.id.button6),
          findViewById(R.id.button7),
          findViewById(R.id.button8),
          findViewById(R.id.button9)
        };

    Collections.shuffle(images);

    for (int i = 0; i < 9; i++) {
      buttons[i].setTextSize(0.0F);

      if (images.get(i) == R.drawable.flipped_wrong) {

        buttons[i].setOnClickListener(
            new View.OnClickListener() {
              public void onClick(View v) {
                v.setBackgroundResource(R.drawable.flipped_wrong);
                lives--;
              }
            });
      } else {
        buttons[i].setOnClickListener(
            new View.OnClickListener() {
              public void onClick(View v) {
                v.setBackgroundResource(R.drawable.flipped_right);
              }
            });
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
  protected void onStart() {
    super.onStart();
  }
}
