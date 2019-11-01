package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourgame.login.Login;

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

    public void checkStats(View view){
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("next activity", data.getLastGame(user));
        startActivity(intent);
    }

    public void playReactionGame(View view){
        Intent intent = new Intent(this, ReactionTime.class);
        startActivity(intent);
    }

    private void playTileGame(){
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

    private void playPictureGame(){
        Intent intent = new Intent(this, PictureInstructions.class);
        startActivity(intent);
        finish();
    }

    public void continueGame(View view) {
        String lastGame = data.getLastGame(user);
        if (lastGame.equals(getString(R.string.reaction_game))){
            playTileGame();
        }
        else if (lastGame.equals(getString(R.string.tile_game))){
            playPictureGame();
        }
        else if (lastGame.equals(getString(R.string.picture_game))){
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, "You have reached maximum points!", duration);
            toast.show();
        }

    }
}
