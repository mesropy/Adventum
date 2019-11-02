package com.example.ourgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourgame.login.Login;

/**
 * An activity class for the Statistics screen, which shows the user their statistics for the games
 * they have played
 */
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
        String str = Integer.toString(dataWriter.getPoints(MainActivity.user));
        pointsText.setText(str);
        String str2 = dataWriter.getPlayTime(MainActivity.user) + " secs.";
        playtimeText.setText(str2);
        rankingText.setText(dataWriter.getRanking(MainActivity.user));
    }

    /**
     * Method to bring the user to the next game after they've viewed their stats
     * @param view the button object that was tapped
     */
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
        } else if (nextActivity.equals("main menu")) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra(Login.EXTRA_MESSAGE, MainActivity.user);
        } else {  // game over activity
            intent = new Intent(this, GameOverActivity.class);
        }

        startActivity(intent);

    }
}
