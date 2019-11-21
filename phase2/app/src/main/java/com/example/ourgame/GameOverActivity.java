package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * An activity class for the Game Over screen. Once a player has finished all three games, the user
 * will be brought here and will be able to view their overall results
 */
public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }
}
