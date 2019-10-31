package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TileGameInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game_instructions);

    }

    public void playTileGameView(View view) {
        Intent intent = new Intent(this, TileGameActivity.class);
        startActivity(intent);
    }
}
