package com.example.ourgame;
//
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TileGame extends AppCompatActivity implements View.OnClickListener {

  private int lives = 3;

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

    private int[] buttons = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,R.id.button6, R.id.button7, R.id.button8, R.id.button9};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tile_game);

    TextView text = findViewById(R.id.title);

    Collections.shuffle(images);

      for (int i = 0; i < buttons.length; i++) {
          Button button = findViewById(buttons[i]);
          button.setTextSize(0.0F);
          button.setOnClickListener(this);

          if (images.get(i) == R.drawable.flipped_wrong){
              button.setText("wrong");

          }
          else{
              button.setText("right");}
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

  @Override
  public void onClick(View view) {
      Button button = (Button)view;
      button.setBackgroundResource(images.get(getButtonIndex(button.getId())));
  }

    public int getButtonIndex(int t) {

        // find length of array
        int len = buttons.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (buttons[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

}
