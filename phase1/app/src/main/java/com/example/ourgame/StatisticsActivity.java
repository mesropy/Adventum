package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void onContinuePressed(View view){
        String nextActivity = getIntent().getStringExtra("next activity");

        if (nextActivity == null) {return;}
        Intent intent;

        if (nextActivity.equals("reaction game")){
            intent = new Intent(this, ReactionTime.class);
        } else if (nextActivity.equals("tile game")){
            intent = new Intent(this, TileGameInstructions.class);
        } else if (nextActivity.equals("picture game")){
            intent = new Intent(this, PictureInstructions.class);
        } else { // game over activity
            intent = new Intent(this, GameOverActivity.class);
        }

        startActivity(intent);

    }
}
