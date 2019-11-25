package com.example.ourgame.Statistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourgame.GameOverActivity;
import com.example.ourgame.MainActivity;
import com.example.ourgame.Games.PictureGame.PictureInstructions;
import com.example.ourgame.R;
import com.example.ourgame.Games.ReactionGame.ReactionGameActivity;
import com.example.ourgame.Games.TileGame.TileGameInstructions;
import com.example.ourgame.login.Login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * An activity class for the Statistics screen, which shows the user their statistics for the games
 * they have played
 */
public class StatisticsActivity extends AppCompatActivity {

    WriteData dataWriter;

    TextView pointsText;
    TextView playtimeText;
    TextView rankingText;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pointsText = findViewById(R.id.pointsValue);
        playtimeText = findViewById(R.id.playtimeValue);
        rankingText = findViewById(R.id.rankingValue);

        dataWriter = new DataWriter(this);
        user = dataWriter.getUser();

        setUpNavigationButtons();
        displayStatistics();
    }

    private void displayStatistics() {
        String str = Integer.toString(dataWriter.getPoints(user));
        pointsText.setText(str);
        String str2 = dataWriter.getPlayTime(user) + " secs.";
        playtimeText.setText(str2);
        rankingText.setText(dataWriter.getRanking(user));
    }

    private void setUpNavigationButtons() {
        // if the next activity is the main menu, make the continue button's text "back"
        // and remove the main menu button
        String nextActivity = getIntent().getStringExtra("next activity");
        if (nextActivity != null && nextActivity.equals("main menu")) {
            Button continueButton = findViewById(R.id.continueButton);
            Button mainMenuButton = findViewById(R.id.mainMenuButton);
            continueButton.setText("back");
            mainMenuButton.setVisibility(View.GONE);
        }
    }


    /**
     * Method to bring the user to the next game after they've viewed their stats
     *
     * @param view the button object that was tapped
     */
    public void onContinuePressed(View view) {
        String nextActivity = getIntent().getStringExtra("next activity");

        if (nextActivity == null) {
            return;
        }
        Intent intent;

        if (nextActivity.equals(getString(R.string.reaction_game))) {
            intent = new Intent(this, ReactionGameActivity.class);
        } else if (nextActivity.equals(getString(R.string.tile_game))) {
            intent = new Intent(this, TileGameInstructions.class);
        } else if (nextActivity.equals(getString(R.string.picture_game))) {
            intent = new Intent(this, PictureInstructions.class);
        } else if (nextActivity.equals("main menu")) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", user);
        } else {  // game over activity
            intent = new Intent(this, GameOverActivity.class);
        }

        startActivity(intent);

    }


    public void onMainMenuPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
