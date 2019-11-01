package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    DataWriter dataWriter;

    TextView pointsText;
    TextView playtimeText;
    TextView rankingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pointsText = findViewById(R.id.pointsValue);
        playtimeText = findViewById(R.id.playtimeValue);
        rankingText = findViewById(R.id.rankingValue);


        dataWriter = new DataWriter(this);
        pointsText.setText(Integer.toString(dataWriter.getPoints(MainActivity.user)));
        playtimeText.setText(Integer.toString(dataWriter.getPlayTime(MainActivity.user)));
        rankingText.setText(dataWriter.getRanking(MainActivity.user));
    }

    public void onContinuePressed(View view){
        String nextActivity = getIntent().getStringExtra("next activity");

        if (nextActivity == null) {return;}
        Intent intent;

        if (nextActivity.equals(getString(R.string.reaction_game))){
            intent = new Intent(this, ReactionTime.class);
        } else if (nextActivity.equals(getString(R.string.tile_game))){
            intent = new Intent(this, TileGameInstructions.class);
        } else if (nextActivity.equals(getString(R.string.picture_game))){
            intent = new Intent(this, PictureInstructions.class);
        } else { // game over activity
            intent = new Intent(this, GameOverActivity.class);
        }

        startActivity(intent);

    }
}
