package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ourgame.Statistics.DataWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LeaderBoardActivity extends AppCompatActivity {

    DataWriter dataWriter;
    TextView firstText, secondText, thirdText, fourthText, fifthText, personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        firstText = findViewById(R.id.first);
        secondText = findViewById(R.id.second);
        thirdText = findViewById(R.id.third);
        fourthText = findViewById(R.id.fourth);
        fifthText = findViewById(R.id.fifth);
        personal = findViewById(R.id.personal);

        dataWriter = new DataWriter(this);

        displayLeaderBoard();
    }

    private void displayLeaderBoard() {
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
            firstText.setText(getString(R.string.rank_text, "1. ", userNames.get(0), points.get(0)));
        }
        if (userNames.size() >= 2) {
            secondText.setText(getString(R.string.rank_text, "2. ", userNames.get(1), points.get(1)));
        }
        if (userNames.size() >= 3) {
            thirdText.setText(getString(R.string.rank_text, "3. ", userNames.get(2), points.get(2)));
        }
        if (userNames.size() >= 4) {
            fourthText.setText(getString(R.string.rank_text, "4. ", userNames.get(3), points.get(3)));
        }
        if (userNames.size() >= 5) {
            fifthText.setText(getString(R.string.rank_text, "5. ", userNames.get(4), points.get(4)));
        }

        personal.setText(getString(R.string.personal_rank, userNames.indexOf(MainActivity.user) + 1));

    }
}
