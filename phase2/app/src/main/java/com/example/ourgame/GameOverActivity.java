package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    /**
     * When the main menu button is pressed, return the user back to the main menu
     * @param view the button that was tapped by the user
     */
    public void returnToMain(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
