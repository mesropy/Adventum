package com.example.ourgame.Statistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    DataWriter dataWriter;

    TextView pointsText;
    TextView playtimeText;
    TextView rankingText;

    TextView firstText, secondText, thirdText, fourthText, fifthText, personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pointsText = findViewById(R.id.pointsValue);
        playtimeText = findViewById(R.id.playtimeValue);
        rankingText = findViewById(R.id.rankingValue);

        firstText = findViewById(R.id.first);
        secondText = findViewById(R.id.second);
        thirdText = findViewById(R.id.third);
        fourthText = findViewById(R.id.fourth);
        fifthText = findViewById(R.id.fifth);
        personal = findViewById(R.id.personal);

        dataWriter = new DataWriter(this);

        Map<String, ?> pointsData = dataWriter.getPointsData().getAll();

        List<String> userNames = new ArrayList<>();
        List<Integer> points = new ArrayList<>();

        List<Map.Entry<String, ?>> entries = new ArrayList<Map.Entry<String, ?>>(pointsData.entrySet());

        //sort the map containing user names and points by point values
        Collections.sort(
                entries
                , new Comparator<Map.Entry<String, ?>>() {
                    public int compare(Map.Entry<String, ?> a, Map.Entry<String, ?> b) {
                        Object aValue = a.getValue();
                        Object bValue = b.getValue();
                        return Integer.compare((Integer) bValue, (Integer) aValue);
                    }
                }
        );
        //after sorting map, place user names and values into arraylists
        for (Map.Entry<String, ?> e : entries) {
            userNames.add(e.getKey());
            points.add((Integer) e.getValue());
        }

        if (userNames.size() >= 1) {
            firstText.setText("1. " + userNames.get(0) + " Points: " + points.get(0));
        }
        if (userNames.size() >= 2) {
            secondText.setText("2. " + userNames.get(1) + " Points: " + points.get(1));
        }
        if (userNames.size() >= 3) {
            thirdText.setText("3. " + userNames.get(2) + " Points: " + points.get(2));
        }
        if (userNames.size() >= 4) {
            fourthText.setText("4. " + userNames.get(3) + " Points: " + points.get(3));
        }
        if (userNames.size() >= 5) {
            fifthText.setText("5. " + userNames.get(4) + " Points: " + points.get(4));
        }

        personal.setText("Your Rank: " + (userNames.indexOf(MainActivity.user) + 1));

        String str = Integer.toString(dataWriter.getPoints(MainActivity.user));
        pointsText.setText(str);
        String str2 = dataWriter.getPlayTime(MainActivity.user) + " secs.";
        playtimeText.setText(str2);
        rankingText.setText(dataWriter.getRanking(MainActivity.user));

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
            intent.putExtra(Login.EXTRA_MESSAGE, MainActivity.user);
        } else {  // game over activity
            intent = new Intent(this, GameOverActivity.class);
        }

        startActivity(intent);

    }
}
