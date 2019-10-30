package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourgame.R;
import com.example.ourgame.TileGame;

public class TileGameInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game_instructions);

    }

    public void playTileGameView(View view) {
        Intent intent = new Intent(this, TileGame.class);
        startActivity(intent);
    }
}
