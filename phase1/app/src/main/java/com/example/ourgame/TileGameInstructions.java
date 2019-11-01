package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * An Activity class for the Instructions of the Tile Game
 */
public class TileGameInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game_instructions);

    }

    /**
     * Proceeds to the actual Tile Game once the user reads the instructions and taps the play
     * button
     * @param view the button that was tapped by the user
     */
    public void playTileGameView(View view) {
        Intent intent = new Intent(this, TileGameActivity.class);
        startActivity(intent);
        finish();
    }
}
