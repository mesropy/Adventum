package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.login.Login;

/**
 * An activity class for the main homepage the user is brought to once they register or sign in
 */
public class MainActivity extends AppCompatActivity {

    static String user;
    private DataWriter data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user = intent.getStringExtra(Login.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.welcome);
        textView.setText("Welcome " + user + "!");

        data = new DataWriter(this);
    }

    /**
     * Sends the user to the Reaction Time Game
     * @param view the button object that was tapped
     */
    public void playReactionGame(View view){
        Intent intent = new Intent(this, ReactionTime.class);
        startActivity(intent);
    }

    /**
     * Sends the user to the Tile Game
     */
    private void playTileGame(){
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Picture Guessing Game
     */
    private void playPictureGame(){
        Intent intent = new Intent(this, PictureInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Method to send the user to resume and play their last played game
     * @param view the button object that was tapped
     */
    public void continueGame(View view) {
        String lastGame = data.getLastGame(user);
        Log.d("FILE", lastGame);
        if (lastGame.equals(getString(R.string.reaction_game))){
            playReactionGame(view);
        }
        else if (lastGame.equals(getString(R.string.tile_game))){
            Log.d("FILE", "PLAY GAME");
            playTileGame();
        }
        else if (lastGame.equals(getString(R.string.picture_game))){
            playPictureGame();
        }

    }
}
