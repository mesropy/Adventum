package com.example.ourgame.Statistics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourgame.R;
import com.example.ourgame.ScreenLoader;

/**
 * An activity class for the Statistics screen, which shows the user their statistics for the games
 * they have played
 */
public class StatisticsActivity extends AppCompatActivity {

    WriteData dataWriter;
    ScreenLoader screenLoader;

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

        screenLoader = new ScreenLoader(this);

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
        // if the statistics were shown after a game, show the continue and main menu buttons,
        // otherwise, the statistics were shown after the main menu, so we only need
        // the back button that takes us back there
        boolean afterGame = getIntent().getBooleanExtra("after game", false);

        if (!afterGame) {
            Button continueButton = findViewById(R.id.continueButton);
            Button mainMenuButton = findViewById(R.id.mainMenuButton);
            mainMenuButton.setVisibility(View.GONE);
            continueButton.setVisibility(View.GONE);
        } else {
            Button backButton = findViewById(R.id.backButton);
            backButton.setVisibility(View.GONE);
        }
    }


    /**
     * Method to bring the user to the next game after they've viewed their stats
     *
     * @param view the button object that was tapped
     */
    public void onContinuePressed(View view) {
        screenLoader.loadNextGame();
    }

    public void onMainMenuPressed(View view) {
        screenLoader.loadMainMenu();
    }

    public void onBackPressed(View view) {
        screenLoader.loadMainMenu();
    }
}
